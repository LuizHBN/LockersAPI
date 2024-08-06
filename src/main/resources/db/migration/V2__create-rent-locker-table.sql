CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE rent_locker (
                                           id SERIAL PRIMARY KEY,
                                           locker_id UUID NOT NULL,
                                           user_id INTEGER NOT NULL,
                                           rent_start_date TIMESTAMP NOT NULL,
                                           rent_finish_date TIMESTAMP
);
