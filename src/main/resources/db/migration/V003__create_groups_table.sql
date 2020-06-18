CREATE TABLE IF NOT EXISTS EXAM_GROUP (
    ID bigserial primary key,
    GROUP_NAME varchar(20) NOT NULL,
    OWNER bigint not null references APP_USER(ID)
);

CREATE TABLE IF NOT EXISTS EXAM_GROUPS_USERS (
    ID bigserial primary key,
    EXAM_GROUP_ID bigint not null references EXAM_GROUP(id),
    APP_USER_ID bigint not null references APP_USER(id)
);