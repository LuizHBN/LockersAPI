CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE locker (
                       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       address VARCHAR(250) NOT NULL,
                       is_free BOOLEAN NOT NULL
);