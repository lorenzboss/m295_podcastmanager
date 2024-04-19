package ch.bossgroup.podcastmanager.artist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestControllerTest {
    private String accessToken;

    @Autowired
    private MockMvc api;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    void setup() {
        this.accessToken = obtainAccessToken();
    }

    @Test
    void getAllArtist() throws Exception {
        final String firstName = "Marques";
        final String lastName = "Brownlee";
        final Artist artist = new Artist(firstName, lastName);

        artistRepository.save(artist);

        api.perform(get("/api/artist")
                        .header("Authorization", "Bearer " + this.accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(firstName)))
                .andExpect(content().string(containsString(lastName)));
    }

    @Test
    void getOneArtist() throws Exception {
        final String firstName = "Steven";
        final String lastName = "Bartlett";
        final Artist artist = new Artist(firstName, lastName);
        final String body = new ObjectMapper().writeValueAsString(artist);

        Long id = getIdFromArtist(body);

        api.perform(get("/api/artist/" + id)
                        .header("Authorization", "Bearer " + this.accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(firstName)))
                .andExpect(content().string(containsString(lastName)));
    }

    @Test
    void createArtist() throws Exception {
        final String firstName = "Andrew";
        final String lastName = "Manganelli";
        final Artist artist = new Artist(firstName, lastName);
        final String body = new ObjectMapper().writeValueAsString(artist);

        api.perform(post("/api/artist")
                        .header("Authorization", "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString(firstName)))
                .andExpect(content().string(containsString(lastName)));
    }

    @Test
    void editArtist() throws Exception {
        final String firstName = "David_xyz";
        final String lastName = "Imel";
        final Artist artist = new Artist(firstName, lastName);
        final String body = new ObjectMapper().writeValueAsString(artist);

        final String newFirstName = "David";
        final Artist newArtist = new Artist(newFirstName, lastName);
        final String newBody = new ObjectMapper().writeValueAsString(newArtist);

       Long id = getIdFromArtist(body);

        api.perform(put("/api/artist/" + id)
                        .header("Authorization", "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newBody)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(newFirstName)))
                .andExpect(content().string(containsString(lastName)));
    }

    @Test
    void deleteArtist() throws Exception {
        final String firstName = "Ellis";
        final String lastName = "Rovin";
        final Artist artist = new Artist(firstName, lastName);
        final String body = new ObjectMapper().writeValueAsString(artist);

       Long id = getIdFromArtist(body);

        api.perform(delete("/api/artist/" + id)
                        .header("Authorization", "Bearer " + this.accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk());
    }

    private Long getIdFromArtist(String body) throws Exception {
        ResultActions resultActions = api.perform(post("/api/artist")
                        .header("Authorization", "Bearer " + this.accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isCreated());

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(responseBody);

        int colonIndex = responseBody.indexOf(":");
        int commaIndex = responseBody.indexOf(",");
        String idString = responseBody.substring(colonIndex + 1, commaIndex);
        return Long.parseLong(idString.trim());
    }

    private String obtainAccessToken() {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=podcastmanager&" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=test_admin&" +
                "password=password";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/ILV/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}
