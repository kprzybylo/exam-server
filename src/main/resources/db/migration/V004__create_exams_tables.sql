CREATE TABLE IF NOT EXISTS EXAM (
    ID bigserial primary key,
    IS_ACTIVE bool default false,
    CREATOR_ID bigint not null references APP_USER(ID),
    EXAM_NAME varchar(50) not null,
    AVAILABLE_TIME bigint not null,
    VALID_FROM date,
    VALID_TO date
);

CREATE TABLE IF NOT EXISTS EXAMS_USERS (
    ID bigserial primary key,
    USER_ID bigint not null references APP_USER(ID),
    EXAM_ID bigint not null references EXAM(ID),
    BEGIN_TIMESTAMP timestamp,
    END_TIMESTAMP timestamp,
    PERCENTAGE_SCORE decimal
);

CREATE TABLE IF NOT EXISTS QUESTION (
    ID bigserial primary key,
    EXAM_ID bigint not null references EXAM(ID),
    QUESTION varchar(256) not null,
    IS_OPEN_QUESTION bool default true
);

CREATE TABLE IF NOT EXISTS ANSWER (
    ID bigserial primary key,
    QUESTION_ID bigint not null references QUESTION(ID),
    ANSWER_TEXT varchar(256) not null,
    IS_CORRECT_ANSWER bool default false
);

CREATE TABLE IF NOT EXISTS USERS_ANSWERS (
    ID bigserial primary key,
    QUESTION_ID bigint not null references QUESTION(ID),
    USER_ID bigint not null references APP_USER(ID),
    ANSWER_TIMESTAMP date not null,
    USER_ANSWER varchar(512),
    ANSWER_ID bigint references ANSWER(ID)
);

CREATE TABLE IF NOT EXISTS ATTACHMENT (
    ID bigserial primary key,
    QUESTION_ID bigint not null references QUESTION(ID),
    ATTACHMENT_LOCATION varchar(256) not null
);