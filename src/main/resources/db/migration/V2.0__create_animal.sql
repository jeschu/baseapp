CREATE SEQUENCE IF NOT EXISTS baseapp.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS baseapp.animal
(
    id      INT  NOT NULL DEFAULT nextval('baseapp.animal_id_seq') PRIMARY KEY,
    db_version INT  NOT NULL DEFAULT 1,

    type    TEXT NOT NULL,
    name    TEXT NOT NULL
);