#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER flyway_densha WITH PASSWORD '${DENSHA_FLYWAY_DB_PASSWORD}';
    CREATE USER app_densha WITH PASSWORD '${DENSHA_APP_DB_PASSWORD}';

    GRANT ALL ON SCHEMA public TO flyway_densha;
    ALTER SCHEMA public OWNER TO flyway_densha;

    GRANT CONNECT ON DATABASE ${POSTGRES_DB} TO app_densha;
    GRANT USAGE ON SCHEMA public TO app_ov_ongdesk;

    ALTER DEFAULT PRIVILEGES FOR ROLE flyway_densha IN SCHEMA public
        GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO app_densha;

    ALTER DEFAULT PRIVILEGES FOR ROLE flyway_densha IN SCHEMA public
        GRANT USAGE, SELECT ON SEQUENCES TO app_densha;
EOSQL