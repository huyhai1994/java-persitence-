create
    database jdbc_demo;
use jdbc_demo;
create table users
(
    id      int primary key,
    `name`  varchar(255),
    `email` varchar(255)
);

insert into users (id, name, email)
values (1, 'John Doe', 'john@example.com');

select *
from users;