--Хранилище машин [#1733]

--Создать SQL запросы:

--1. Вывести список всех машин и все привязанные к ним детали.
select cars.brand, carcase.type as carcase, engine.volume as engine, transmission.type as transmission from cars
join carcase on carcase.id = cars.carcase_id
join engine on engine.id = cars.engine_id
join transmission on transmission.id = cars.transmission_id;


--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

--Кузова:
select carcase.type as carcase_not_used_in_honda_stream from carcase
join cars on cars.carcase_id <> carcase.id
where cars.brand = 'honda stream';

--Двигатели:
select engine.volume as engines_not_used_in_honda_stream from engine
join cars on cars.engine_id <> engine.id
where cars.brand = 'honda stream';

--Коробки передач
select transmission.type as transmissions_not_used_in_honda_stream from transmission
join cars on cars.transmission_id <> transmission.id
where cars.brand = 'honda stream';