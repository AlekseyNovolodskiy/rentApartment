create table if not exists product_info
(
    id            int8 primary key,
    name_discount varchar,
    discount      double precision
);
create sequence product_info_sequence start 13 increment 1;

insert into product_info (id, name_discount, discount)
values (1, 'NY_discount', 0.25),
       (2, 'large_family', 0.1),
       (3, 'holiday_l1', 0.2),
       (4, 'student', 0.3),
       (5, 'business_trip', 0.1),
       (6, 'other', 0.15),
       (7, 'administrator_value', 0.1),
       (8, 'issue_l1', 0.3),
       (9, 'vacation_discount', 0.2),
       (10, 'total_issue', 1),
       (11, 'early_full_payment', 0.1),
       (12, 'early_part_payment', 0.05),
       (13,'bithday_discount', 0.1);



