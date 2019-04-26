INSERT INTO usr_user(id, email, password, creation_date, modification_date) VALUES (1, 'user1@mgavino.com', 'password', now(), now());
INSERT INTO usr_user(id, email, password, creation_date, modification_date) VALUES (2, 'user2@mgavino.com', 'password', now(), now());
INSERT INTO usr_user(id, email, password, creation_date, modification_date) VALUES (3, 'user3@mgavino.com', 'password', now(), now());
INSERT INTO usr_user(id, email, password, creation_date, modification_date) VALUES (4, 'user4@mgavino.com', 'password', now(), now());
INSERT INTO usr_user(id, email, password, creation_date, modification_date) VALUES (5, 'user5@mgavino.com', 'password', now(), now());

INSERT INTO bac_bank_account(id, usr_id, name, balance, creation_date, modification_date) VALUES (1, 1, "Account 1", 0, now(), now());
INSERT INTO bac_bank_account(id, usr_id, name, balance, creation_date, modification_date) VALUES (2, 1, "Account 2", 20, now(), now());
INSERT INTO bac_bank_account(id, usr_id, name, balance, creation_date, modification_date) VALUES (3, 1, "Account 3", 40, now(), now());

INSERT INTO bmv_bank_movement(id, bac_id, amount, concept, date) VALUES (1, 3, -40, 'Withdraw', now());
INSERT INTO bmv_bank_movement(id, bac_id, amount, concept, date) VALUES (2, 3, 10, 'Deposit', now());