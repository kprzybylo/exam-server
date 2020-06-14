CREATE TABLE IF NOT EXISTS ROLE (
    ID bigserial not null primary key,
    ROLE_NAME varchar(20) not null
);

CREATE TABLE IF NOT EXISTS APP_USER (
    ID bigserial not null primary key,
    FIRST_NAME varchar(30) not null,
    LAST_NAME varchar(30) not null,
    PASSWORD varchar(100) not null,
    EMAIL varchar(50) not null,
    ROLE_ID bigint not null references ROLE(id),
    EXPIRATION_DATE date default (current_date + interval '1 year'),
    PASSWORD_EXPIRATION_DATE date default (current_date + interval '1 year'),
    IS_NON_LOCKED bool default true,
    IS_ENABLED bool default true
);