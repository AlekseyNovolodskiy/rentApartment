create table if not exists apartment_info
(
    id int8 primary key,
    area double precision,
    cost double precision,
    count_of_people double precision,
    date_of_registration timestamp,
    time_of_rent timestamp,
    description varchar
);

create sequence apartment_info_sequence start 3 increment 1;

insert into apartment_info(id, area, cost, count_of_people, date_of_registration, time_of_rent, description)
values(1,10,10000,3,'2034-07-10','2034-07-10','asa'),
      (2,20,20000,4,'2034-07-10','2034-07-10','aja')

