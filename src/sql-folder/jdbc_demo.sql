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