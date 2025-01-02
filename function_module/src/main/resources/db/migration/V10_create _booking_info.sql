create table if not exists booking_info
(
    id      int8 primary key,
    user_id int,
    bookingDays int,
    sum     double precision

);
create sequence user_info_sequence start 3 increment 1;

insert into  booking_info (id, user_id, bookingDays, sum)
values (1,1,14, 25000),
       (2,2,2,10000)