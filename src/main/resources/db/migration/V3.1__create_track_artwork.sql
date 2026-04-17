CREATE SEQUENCE IF NOT EXISTS baseapp.track_artwork_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS baseapp.track_artwork
(
    id           INT NOT NULL DEFAULT nextval('baseapp.track_artwork_id_seq') PRIMARY KEY,
    db_version   INT NOT NULL DEFAULT 1,

    track_id     INT NOT NULL REFERENCES baseapp.track (id) ON DELETE CASCADE,
    db_order     INT NOT NULL,
    mime_type    TEXT,
    description  TEXT,
    height       INT,
    width        INT,
    linked       BOOLEAN,
    image_url    TEXT,
    picture_type INT,
    binary_data  BYTEA,

    UNIQUE (track_id, db_order)
);