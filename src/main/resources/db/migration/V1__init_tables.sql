create table message
(
    id       uuid primary key,
    username varchar(36) not null,
    time     timestamp,
    message  text,
    chat_id  varchar(36) not null
);