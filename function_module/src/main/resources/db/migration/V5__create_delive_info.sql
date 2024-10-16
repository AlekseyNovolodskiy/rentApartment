create table IF not exists Delive_info
(
    id                int8 primary key,

    fruits            varchar,
    fruits_amount     double precision,
    fruits_price      double precision,

    vegetables        varchar,
    vegetables_amount double precision,
    vegetables_price  double precision,

    meat              varchar,
    meat_amount       double precision,
    meat_price        double precision,

    juices            varchar,
    juices_amount     double precision,
    juices_price      double precision
);

create sequence Delive_info_sequence start 3 increment 1;

insert into Delive_info(id, fruits, fruits_amount, fruits_price, vegetables, vegetables_amount, vegetables_price, meat,
                        meat_amount, meat_price, juices, juices_amount, juices_price)
values
    (1, 'oranges', 10, 150, 'potato', 100, 50, 'chicken', 25, 250, 'grapes', 5, 150),
       (2, 'apples', 11, 170, 'cucumber', 12, 60, 'beef', 30, 350, 'multifruits', 5, 150);