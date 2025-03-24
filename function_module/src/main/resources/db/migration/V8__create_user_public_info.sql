create table if not exists user_public_info
(
    id            int8 primary key,
    user_id       int8 references user_info (id),
    birth_day      date,
    bussines_trip boolean,
    large_family  boolean,
    on_vacation   boolean,
    student       boolean

);
create sequence user_public_info_sequence start 9 increment 1;

insert into user_public_info (id, user_id, birth_day, bussines_trip, large_family, on_vacation, student)
values (1, 1, '1985-12-12', true, true, false, false),
       (2, 2, '1983-12-12', false, false, true, false);


