create table person (
    id serial primary key,
    "name" varchar not null,
    age int check (age > 0),
    email varchar unique
);
--    id int generated by default as identity primary key,

insert into person (name,age,email) values
    ('John', 20, 'john@gmail.com'),
    ('Jane', 21, 'jane@gmail.com'),
    ('Jack', 22, 'jack@gmail.com'),
    ('Jill', 23, 'jill@gmail.com'),
    ('Jim', 24, 'jim@gmail.com')

select * from person

drop table person

create sequence first_sequence

select nextval('first_sequence')

drop sequence first_sequence

commit
