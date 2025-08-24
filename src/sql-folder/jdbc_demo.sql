create
    database jdbc_demo;
use jdbc_demo;
create table users
(
    id      int primary key,
    `name`  varchar(255),
    `email` varchar(255)
);

create table orders
(
    id      int primary key auto_increment,
    user_id int,
    amount  int,
    foreign key (user_id) references users (id)
);

alter table orders
    add column timestamp timestamp;

insert into users (id, name, email)
values (1, 'John Doe', 'john@example.com');
-- Insert additional user records
INSERT INTO users (id, name, email)
VALUES (2, 'Jane Smith', 'jane@example.com');

INSERT INTO users (id, name, email)
VALUES (3, 'Mike Johnson', 'mike@example.com');

INSERT INTO users (id, name, email)
VALUES (4, 'Sarah Williams', 'sarah@example.com');

INSERT INTO users (id, name, email)
VALUES (5, 'David Brown', 'david@example.com');

INSERT INTO users (id, name, email)
VALUES (6, 'Emma Wilson', 'emma@example.com'),
       (7, 'James Taylor', 'james@example.com'),
       (8, 'Lisa Garcia', 'lisa@example.com');

SELECT *
FROM users;
insert into orders (user_id, amount)
values (3, 100);


select *
from users
         inner join orders
                    on users.id = orders.user_id;

select *
from users;

show create table orders;