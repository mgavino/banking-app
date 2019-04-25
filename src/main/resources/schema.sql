CREATE TABLE usr_user (
    id serial PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    creation_date TIMESTAMP,
    modification_date TIMESTAMP
);

CREATE TABLE bac_bank_account (
    id serial PRIMARY KEY,
    usr_id INTEGER,
    balance DOUBLE PRECISION,
    creation_date TIMESTAMP,
    modification_date TIMESTAMP,
    CONSTRAINT bac_usr_fk FOREIGN KEY (usr_id)
          REFERENCES usr_user(id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE bmv_bank_movement (
    id serial PRIMARY KEY,
    bac_id INTEGER,
    amount DOUBLE PRECISION,
    concept VARCHAR(255),
    creation_date TIMESTAMP,
    modification_date TIMESTAMP,
    CONSTRAINT bmv_bac_fk FOREIGN KEY (bac_id)
              REFERENCES bac_bank_account(id) MATCH SIMPLE
              ON UPDATE NO ACTION ON DELETE NO ACTION
);


