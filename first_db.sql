create table person (
    id int,
    "name" varchar,
    age int,
    email varchar
)

insert into person values
    (1, 'John', 20, 'john@gmail.com'),
    (2, 'Jane', 21, 'jane@gmail.com'),
    (3, 'Jack', 22, 'jack@gmail.com'),
    (4, 'Jill', 23, 'jill@gmail.com'),
    (5, 'Jim', 24, 'jim@gmail.com')

select * from person

drop table person

commit
