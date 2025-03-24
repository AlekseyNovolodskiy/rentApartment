create table if not exists booking_info
(
    id                   int8 primary key,
    booking_days         int,
    sum                  double precision,
    time_of_registration timestamp,
    time_of_start_rent   timestamp,
    apartment_id         int8 references apartment_info (id),
    user_id              int8


);
create sequence booking_info_sequence start 3 increment 1;

insert into booking_info (id, booking_days, sum, time_of_registration, time_of_start_rent, apartment_id, user_id)
values (1, 7, 50000, '2025-02-05T11:59:11.332Z', '2025-02-12T11:59:11.332Z', 1,1),
       (2, 10, 60000, '2025-02-05T11:59:11.332Z', '2025-02-15T11:59:11.332Z', 2,2)