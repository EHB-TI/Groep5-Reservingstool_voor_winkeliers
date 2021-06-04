# Reservatietool voor winkeliers

De bedoeling van dit project is om ervoor te zorgen dat winkeliers hun eigen profiel kunnen maken en hun winkel kunnen adverteren op deze manier kunnen ze zo ook een breder publiek kunnen bereiken. Hier kunnen ze foto's uploaden, informatie bewerken, tickets aanmaken...

### Gebruikte libraries
    - io.requery : requery
    - io.requery : requery-processor
    - javax.annotation : javax.annotation-api
    - org.projectlombok : com.zaxxer
    - com.vaadin : Vaadin
    - com.zaxxer : HikariCP
    - com.fasterxml.jackson.dataformat : jackson-dataformat-yaml
    - org.vaadin.stefan : fullcalendar2

### Installatie

- Na het pullen/clonen van het project moet er een Maven compilatie gebeuren.

### Gebruik
Het project gebruikt openjdk-15.
#### Manier 1
    - Het project gebruikt SpringBoot en de klasse om de applicatie tu kunnen lanceren is Application.java via Intellij.

#### Manier 2
    - Download de launch.zip en exctract het in de gewenste folder
    - Start launch.sh script file (pas op! De script gebruikt de systeem variabel "java". Als java op uw computer te oud is, zal het niet  lanceren. Verander de script variabel JAVA_EXECUTOR naar de juiste path van de laatste java versie)
#### Na het starten
- Indien de server gestart is, moet u dan op http://localhost:8080 gaan
- Een fake account is op voorhand gemaakt om de statistieken, kalender, ... pagina's te kunnen démonstreren. De email is `1@gmail.com` en de passwoord is `123456`
- Een andere account is beschikbaar om de admin gedeelte te kunnen zien. De email is `admin@gmail.com` en de passwoord `123456`

### Mogelijke probleem

![Vaadin Subscription Error](https://imgur.com/download/Wqst9wP/)

Als u deze bekomt, is er een manier om die weg te maken. Aangezien we sommige componenten gebruiken die enkel in Vaadin Permium werken. Moet met oftewel betalen of zoals in onze geval de student plan kiezen. Hier is een link om de student plan te kunnen kiezen: https://vaadin.com/student-program.

### Wie heeft wat gedaan ?
* Demir Tugçe: kalender per maand en per dag.
* Lamsakam Zakaria: company ticket pagina, support pagina en profielbewerken pagina, frontend edit pagina.
* Zoetardt Craig: login pagina, registreer en backend edit pagina.
* Faille Arnaud: edit, dashboard, commonLayout, database interactie en entiteiten.
* De Vogel Ryan: Admin ticket pagina, choose plan pagina, payment pagina.
