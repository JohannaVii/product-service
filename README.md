# Product Service

## Om tjänsten

Product-Service hanterar produkter i webbshoppen, till exempel att
hämta produkter. Varje produkt har information som namn, beskrivning,
pris, kategori, bild och lagersaldo. Som ADMIN kan man lägga till och
ta bort produkter. Det finns även funktioner för att minska och återställa
lagersaldot.

## Teknik

- Java 17
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- H2 - för lokal db
- PostgreSQL - för db i produktion
- Maven

## Köra lokalt

### Krav

**För att köra Product-Service lokalt behövs:**

- Java 17
- Maven / Maven Wrapper
- Auth Server (för skyddade endpoints)

### Miljövariabler

| Variabel                 | Beskrivning                             |
|--------------------------|-----------------------------------------|
| `DB_PASSWORD`            | Lösenord till PostgreSQL-databasen      |
| `DB_URL`                 | URL till PostgreSQL-databasen           |
| `DB_USERNAME`            | Användarnamn till PostgreSQL-databasen  |
| `INTERNAL_SERVICE_TOKEN` | Token för interna anrop mellan tjänster |
| `JWT_PUBLIC_URL`         | URL till publika JWT-nycklar            |

### Starta tjänsten

**Starta Product-Service med den lokala profilen:**
`./mvnw spring-boot:run -Dspring-boot.run.profiles=local`

**I Windows kan tjänsten startas med:**
`mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local`

När tjänsten är igång körs den på port 8082.
Den lokala profilen använder H2 som databas.

## API / Endpoints

Product-Service har endpoints för produkter, lager och status.

### Produkter

`GET /products` – visar alla produkter

`GET /products/{id}` - visar en produkt med id

`POST /products` – lägger till en ny produkt

`DELETE /products/{id}` - tar bort en produkt

Alla kan hämta listan med produkter. För att hämta en produkt med
id behöver användaren vara inloggad.
Lägga till / ta bort produkter är endast tillgängligt för ADMIN.

### Lager

`POST /products/stock/decrease` - minskar lagersaldot

`POST /products/stock/restore` - återställer lagersaldot

### Status

`GET /` - säkerställer att Product-Service är igång

## Övrigt

Produktlistan kan hämtas utan att användaren är inloggad.

För att lägga till eller ta bort en produkt behöver användaren ha rollen ADMIN.

Product-Service använder H2 lokalt och PostgreSQL på Render.

**Product-Service kan testas lokalt i Swagger:**
`http://localhost:8082/swagger-ui/index.html`

## Driftsatt miljö (Render)

**Product-Service är driftsatt på Render:**
`https://product-service-1-a55s.onrender.com/`