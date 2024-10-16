create table if not exists integration_info
(
    id int8 primary key,
    url varchar,
    name varchar,
    key_info varchar

);

create sequence integration_info_sequence start 4 increment 1;

insert into integration_info(id, url, name, key_info)
values(1,'http://localhost:9097/version','version',null),
      (2,'http://localhost:9097/testproduct?name=name&phoneNumber=number','nameandphone',null),
      (3,'https://api.opencagedata.com/geocode/v1/json?q=%s+%s&language=rus&key=%s','geo','bf73e976304443c2b7594c0827d3a6a9')


