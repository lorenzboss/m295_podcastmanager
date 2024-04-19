# Technische Konfigurationen

## Datenbankverbindung

- PostGreSQL Datenbank
- Name: ``podcastmanager``
- Username: ``postgres``
- Passwort: ``windows``

## Einstellungen Keycloak

- Realm: ``ILV``
- Client: ``podcastmanager``
- Client-Rollen:
    - ``ROLE_admin``
    - ``ROLE_staff``
    - ``ROLE_user``
- Benutzer:
    - Username: ``test_admin`` ; Passwort: `windows`
    - Username: ``test_staff`` ; Passwort: `windows`
    - Username: ``test_user`` ; Passwort: `windows`

## Netzwerkeinstellungen

- Port des Backends: ``9090``
- Port von Keycloak: ``8080``

## Weitere Informationen

- Swagger UI kann man über http://localhost:9090/ erreichen
- Beispieldaten können auf folgender [Notion-Seite](https://lorenzboss.notion.site/Podcastmanager-7c5220e47616473f8b084ff88ea6fcc2) gefunden werden
- Wenn man Daten hinzufügen möchte, solle man sich an folgende Reihenfolge halten:
    - Artist Daten hinzufügen
    - Topic Daten hinzufügen
    - Podcast Daten hinzufügen
    - Writer Daten hinzufügen
    - Review Daten hinzufügen
