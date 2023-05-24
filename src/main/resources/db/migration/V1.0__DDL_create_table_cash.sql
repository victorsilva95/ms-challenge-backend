create table cash
(
    id                     serial           not null primary key,
    client_name            varchar(255)     not null,
    type                   varchar(50)      not null,
    value                  decimal(10,2)    not null,
    date                   timestamp        not null
);