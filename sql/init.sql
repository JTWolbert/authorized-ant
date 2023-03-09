CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE FUNCTION trigger_set_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at
= NOW();
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TABLE users
(
    user_pk       SERIAL                                             NOT NULL PRIMARY KEY,
    user_id       UUID                                               NOT NULL DEFAULT uuid_generate_v4(),
    user_name     character varying(255)                             NOT NULL UNIQUE,
    user_email    character varying(255) UNIQUE,
    user_password character varying(255)                             NOT NULL,
    created_at    timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at    timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE feature_types
(
    feature_type_pk   SERIAL                                             NOT NULL PRIMARY KEY,
    feature_type_id   UUID                                               NOT NULL DEFAULT uuid_generate_v4(),
    feature_type_name character varying(255)                             NOT NULL UNIQUE,
    created_at        timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at        timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE features
(
    feature_pk   SERIAL                                             NOT NULL PRIMARY KEY,
    feature_id   UUID                                               NOT NULL DEFAULT uuid_generate_v4(),
    feature_name character varying(255)                             NOT NULL UNIQUE,
    feature_type integer                                            NOT NULL,
    created_at   timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at   timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_feature_type
        FOREIGN KEY (feature_type)
            REFERENCES feature_types (feature_type_pk)
);

INSERT INTO feature_types
values (default, default, 'loot', default, default);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON users
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON features
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON feature_types
    FOR EACH ROW
    EXECUTE PROCEDURE trigger_set_timestamp();