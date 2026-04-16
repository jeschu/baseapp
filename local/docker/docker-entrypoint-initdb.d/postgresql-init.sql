CREATE EXTENSION IF NOT EXISTS dblink;

DO
$$
BEGIN
        PERFORM dblink_exec(
                'user=keycloak dbname=keycloak',
                'CREATE DATABASE baseapp'
                );
EXCEPTION
        WHEN duplicate_database THEN RAISE NOTICE '%, skipping', SQLERRM USING ERRCODE = SQLSTATE;
END
$$;

DO
$$
BEGIN
        IF EXISTS (SELECT
                   FROM pg_catalog.pg_roles
                   WHERE rolname = 'baseapp')
        THEN
            RAISE NOTICE 'Role baseapp already exists, skipping';
ELSE
CREATE ROLE "baseapp" LOGIN PASSWORD 'baseapp';
END IF;
END
$$;

ALTER DATABASE "baseapp" OWNER TO "baseapp";
