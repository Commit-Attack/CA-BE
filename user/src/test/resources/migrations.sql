create schema account;

create table account."Users"
(
    id                   varchar(36)  not null
        primary key,
    "name"                 varchar(255) not null,
    "profileImageUrl"    varchar(255),
    "initialCommitCount" integer,
    "githubId"           varchar(255) not null,
    "createdAt"            timestamp(6) not null,
    "deletedAt"           timestamp(6),
    "updatedAt"           timestamp(6)
);

