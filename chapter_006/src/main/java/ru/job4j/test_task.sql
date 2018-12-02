-- Тестовое задание
-- Есть таблица. В ней два столбца id, name. В name есть повторяющиеся значения.
-- Нужно удалить все дубликаты из таблицы

CREATE table items (
id serial primary key,
name varchar(50)
);

insert into items (name)
values 
('Ivan'),
('Semen'),
('Ivan'),
('Petr'),
('Semen'),
('Ivan');

delete from items where items.id not in
(select id from (select distinct on (name) * from items) as foo);