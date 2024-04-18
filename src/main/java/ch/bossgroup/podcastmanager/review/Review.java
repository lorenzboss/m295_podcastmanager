package ch.bossgroup.podcastmanager.review;

import ch.bossgroup.podcastmanager.podcast.Podcast;
import ch.bossgroup.podcastmanager.writer.Writer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 100)
    @NotEmpty
    private String title;

    @Column()
    @Size(min = 5, max = 250)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "writer_id", nullable = false)
    private Writer writer;

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;

}
