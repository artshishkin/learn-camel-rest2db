CREATE TABLE rest_countries
(
    id          SERIAL PRIMARY KEY,
    name        text,
    capital     text,
    create_date timestamp without time zone DEFAULT now()
)


