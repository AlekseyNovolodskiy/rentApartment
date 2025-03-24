create table if not exists user_info
(
    id int8 primary key,
    login varchar,
    nick_name varchar,
    email varchar,
    password varchar,
    token varchar,
    date_registration timestamp,
    verification varchar

);

create sequence user_info_sequence start 3 increment 1;

insert into user_info(id,login,nick_name,email,password,token,date_registration,verification)
values(1,'login1','nick1','greathybi4@gmail.com','cGFzc3dvcmQ=','83f887bb-eda8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900','2034-07-10T12:48:11.796983900', 'verified'),
    (2,'login2','nick2','greathybi3@gmail.com','cGFzc3dvcmQ=','83f887bb-eta8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900','2034-07-10T12:48:11.796983900', '45663')
