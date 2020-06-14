INSERT INTO ROLE (ID, ROLE_NAME)
VALUES (1, 'STUDENT'),
    (2, 'ADMIN'),
    (3, 'TEACHER');

INSERT INTO APP_USER (ID, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, ROLE_ID)
VALUES (1, 'Jan', 'Kowalski', '$2a$10$On9RQJ5H0pIH2SUn17H1tuhe/ywJ1lPejzMgozxGaf2nQfRpg2fJa', 'jkowalski@test.pl', 1),
    (2, 'Stefan', 'Admin', '$2a$10$OU9Sru.12DFolzomFzwQi.QcyzVnnItwS1micIlnGPqS/bKVWUA3G', 'sadmin@test.pl', 2);