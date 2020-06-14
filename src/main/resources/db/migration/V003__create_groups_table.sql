CREATE TABLE IF NOT EXISTS EXAM_GROUP (
    ID bigserial NOT NULL primary key,
    GROUP_NAME varchar(20) NOT NULL,
    OWNER bigint references APP_USER(ID)
);

CREATE TABLE IF NOT EXISTS EXAM_GROUPS_USERS (
    ID bigserial NOT NULL primary key,
    EXAM_GROUP_ID bigint references EXAM_GROUP(id),
    APP_USER_ID bigint references APP_USER(id)
);