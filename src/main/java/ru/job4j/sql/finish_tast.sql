create table meeting
(
    id serial primary key,
    name varchar(100)
);

create table users
(
    id   serial primary key,
    name varchar(100)
);

create table users_meeting
(
    meeting_id int references meeting (id),
    users_id   int references users (id),
    status_id  int references status (id)
);

create table status
(
    id serial primary key,
    name varchar(100)
);

drop table users_meeting;
drop table meeting;

insert into meeting (name) values ('Встреча выпускников');
insert into meeting (name) values ('Рабочая встреча');
insert into meeting (name) values ('Групповое собеседование');
insert into meeting (name) values ('Встреча любителей животных');
insert into meeting (name) values ('Презентация продукта');

insert into users (name) values ('Паша');
insert into users (name) values ('Саша');
insert into users (name) values ('Маша');
insert into users (name) values ('Даша');
insert into users (name) values ('Наташа');
insert into users (name) values ('Глаша');

insert into status (name) values ('Участие подтвержденно');
insert into status (name) values ('Участие отклонено');

insert into users_meeting (meeting_id, users_id) values (1, 1);
insert into users_meeting (meeting_id, users_id) values (1, 2);
insert into users_meeting (meeting_id, users_id, status_id) values (1, 3, 1);
insert into users_meeting (meeting_id, users_id, status_id) values (1, 4, 1);
insert into users_meeting (meeting_id, users_id, status_id) values (1, 5, 2);
insert into users_meeting (meeting_id, users_id, status_id) values (2, 3, 1);
insert into users_meeting (meeting_id, users_id, status_id) values (3, 6, 2);

select m.name, count(um.status_id)
from meeting m
         join users_meeting um on m.id = um.meeting_id
where status_id = 1
group by m.name;

select m.name
from meeting m
         left join users_meeting um on m.id = um.meeting_id
where um.meeting_id is null;