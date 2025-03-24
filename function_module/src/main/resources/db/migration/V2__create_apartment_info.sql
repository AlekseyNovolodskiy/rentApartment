create table if not exists apartment_info
(
    id                   int8 primary key,
    area                 double precision,
    cost                 double precision,
    count_of_people      int,
    description          varchar
);

create sequence apartment_info_sequence start 3 increment 1;

insert into apartment_info(id, area, cost, count_of_people, description)
values (1, 10, 10000, 3,  'asa'),
       (2, 20, 20000, 4,  'aja');

-- это функция скрипта логгирования
create or replace function log_info_insert ()

    returns trigger as $$

begin

insert into log_info (message)
values( 'добввлены новые аппартаменты район: '|| NEW.area);

return new;
 end;
$$ language plpgsql;

-- тригер для таблицы апартмент_инфо
create trigger log_info_create_trigger
    after insert on apartment_info
    for each row execute function log_info_insert();