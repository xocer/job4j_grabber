CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company values (1, 'Кока-кола');
insert into company values (2, 'Volkswagen');
insert into company values (3, 'Слоистые полимеры');

insert into person values (1, 'Иван', 1);
insert into person values (2, 'Денис', 1);
insert into person values (3, 'Марина', 2);
insert into person values (4, 'Анжела', 2);
insert into person values (5, 'Владимир');
insert into person values (6, 'Павел', 3);
insert into person values (7, 'Алексей', 3);
insert into person values (8, 'Полина');
insert into person values (9, 'Маргарита');
insert into person values (10, 'Антон', 3);

select p.name, c.name from person as p left join company c on p.company_id = c.id
where p.company_id !=5 or p.company_id is null;

select c.name Компания, count(p.company_id) Число_сотрудников from person as p left join company c on p.company_id = c.id
where c.name is not null
group by c.name
order by c.name desc
limit 1;