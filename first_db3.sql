--learn actions on delete

create table person2(
    user_id serial primary key,
    "name" varchar(100) not null,
    age int
)

create table "order"(
    order_id serial primary key,
--    user_id int references person2(user_id) on delete cascade,
--    user_id int references person2(user_id) on delete restrict, --default
    user_id int references person2(user_id) on delete set null,
    item_name varchar(100)
)

insert into person2(name, age) values
('Jim', 20)

insert into "order"(user_id, item_name) values
(1, 'apple')

drop table if exists person2
drop table if exists "order"

commit

delete from person2 where user_id = 1

select * from person2
select * from "order"