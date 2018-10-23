
--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product where type_id = 1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like 'мороженное';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where expierd_date between '2018-11-01' and '2018-11-30';

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price = (select max(price) from product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from product where type_id = 2;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select count(*) from product where type_id = 1 or type_id = 3;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select type.name, sum(product.number) from product join type on type.id = product.type_id group by type.name having sum(product.number) < 10;

--8. Вывести все продукты и их тип.
select product.name as product_name, type.name as type_name from product, type where product.type_id = type.id;

