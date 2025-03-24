create table if not exists integration_info
(
    id int8 primary key,
    url varchar,
    name varchar,
    key_info varchar

);

create sequence integration_info_sequence start 6 increment 1;

insert into integration_info(id, url, name, key_info)
values(1,'http://localhost:8888/verify_auth?marker=verify','integration_verification',null),
      (2,'https://api.opencagedata.com/geocode/v1/json?q=%s+%s&language=rus&key=%s','geo','bf73e976304443c2b7594c0827d3a6a9'),
      (3,' http://localhost:8888/verify?login=%s&code=%s','rent-sender_url_verificaion',null),
      (4,'http://localhost:8888/verify_auth?marker=booking_notation','integration_booking',null),
      (5,'http://localhost:8888/verify_auth?marker=reject','rejectedBooking',null),
      (6,'http://localhost:8888/responce_discount','discountResponce',null)


