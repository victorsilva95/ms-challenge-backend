create table cash
(
    id                                      int primary key auto_increment not null ,
    client_name            varchar(255)     not null,
    type                   varchar(50)      not null,
    value                  decimal(10,2)    not null,
    date                   timestamp        not null
);