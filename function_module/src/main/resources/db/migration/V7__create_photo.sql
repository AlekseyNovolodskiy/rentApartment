create table  if not exists photo(
    id int8 primary key,
    photo  bytea,
    apartment_id int8   references apartment_info(id)
);


create sequence photo_sequence;

