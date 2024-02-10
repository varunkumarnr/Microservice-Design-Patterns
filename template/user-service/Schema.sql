create table users (
    id serial primary key,
    name varchar(50),
    balance int,
	phoneNumber varchar(20)
);

create table if not exists transactions (
    id serial primary key,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    constraint fk_user_id 
			foreign key (user_id) 
			references users(id) 
			on delete cascade
);

insert into users (name, balance)
values
    ('Varun', 1000, "1234567890"),
		('Random Name', 2000, "1234569870"),
    ('Harsh', 3000, "1232569870"),
    ('Arpan', 4000, "1232569879");

drop table if exists transactions;
drop table if exists users;
