create table if not exists address_info
(
    id int8 primary key,
    apartment_id int8 references apartment_info(id),
    building_number varchar,
    city varchar,
    street varchar
);

create sequence address_info_sequence start 3 increment 1;

insert into address_info(id, apartment_id, building_number, city, street)
values(1,1,45,'Москва','green_street'),
      (2,2,23,'Санкт Петербург','red_street');

