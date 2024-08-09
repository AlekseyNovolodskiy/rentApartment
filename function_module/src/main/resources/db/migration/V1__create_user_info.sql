create table if not exists user_info
(
    id int8 primary key,
    login varchar,
    nick_name varchar,
    password varchar,
    token varchar,
    date_registration timestamp
);

create sequence user_info_sequence start 3 increment 1;

insert into user_info(id,login,nick_name,password,token,date_registration)
values(1,'login1','nick1','password1','83f887bb-eda8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900','2034-07-10T12:48:11.796983900'),
    (2,'login2','nick2','password2','83f887bb-eda8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900','2034-07-10T12:48:11.796983900')

