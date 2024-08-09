create table if not exists rate_apartment
(
    id int8 primary key,
    apartment_id int8 references apartment_info(id),
    comments varchar(1000),
    rating int4
);

create sequence rate_apartment_sequence start 3 increment 1;

insert into rate_apartment (id, apartment_id, comments, rating)
values(1,1,'aaa',5),
      (2,2,'bbb',6);
