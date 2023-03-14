drop table if exists color;
drop table if exists category;
drop table if exists cat_character;
drop table if exists cat;
drop table if exists appearance_record;
drop table if exists feeding_record;
drop table if exists location;
drop table if exists user;

create table appearance_record
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint                             not null,
    username    varchar(16)                        not null,
    location_id bigint                             not null,
    record_time char(16)                           not null,
    last_update datetime default CURRENT_TIMESTAMP not null,
    deleted     tinyint  default 0                 not null,
    need_check  tinyint  default 0                 not null
);

create definer = root@localhost trigger a_record_after_delete
    after delete
    on appearance_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
end;

create definer = root@localhost trigger a_record_after_insert
    after insert
    on appearance_record
    for each row
begin
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create definer = root@localhost trigger a_record_after_update
    after update
    on appearance_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create table cat
(
    cat_id       bigint auto_increment
        primary key,
    cat_name     varchar(15)       not null,
    category_id  bigint            not null,
    color_id     bigint            not null,
    character_id bigint            not null,
    location_id  bigint            not null,
    record_count int     default 0 not null,
    deleted      tinyint default 0 not null,
    need_check   tinyint default 0 not null,
    constraint cat_name
        unique (cat_name)
);

create table cat_character
(
    character_id  bigint auto_increment
        primary key,
    cat_character varchar(15) not null,
    constraint cat_character
        unique (cat_character)
);

insert into cat_character
values (1, '友好'),
       (2, '安静'),
       (5, '活泼'),
       (3, '粘人'),
       (4, '胆小');

create table category
(
    category_id bigint auto_increment
        primary key,
    category    varchar(15) not null,
    constraint category
        unique (category)
);

insert into category
values (3, '加菲'),
       (1, '美短'),
       (2, '英短');

create table color
(
    color_id bigint auto_increment
        primary key,
    color    varchar(15) not null,
    constraint color
        unique (color)
);

insert into color
values (5, '橘'),
       (2, '白'),
       (3, '蓝'),
       (4, '黄'),
       (1, '黑');

create table feeding_record
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint                             not null,
    username    varchar(16)                        not null,
    location_id bigint                             not null,
    record_time char(16)                           not null,
    remarks     varchar(50)                        null,
    last_update datetime default CURRENT_TIMESTAMP not null,
    deleted     tinyint  default 0                 not null,
    need_check  tinyint  default 0                 not null
);

create definer = root@localhost trigger f_record_after_delete
    after delete
    on feeding_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
end;

create definer = root@localhost trigger f_record_after_insert
    after insert
    on feeding_record
    for each row
begin
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create definer = root@localhost trigger f_record_after_update
    after update
    on feeding_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create table location
(
    location_id bigint auto_increment
        primary key,
    location    varchar(20) not null,
    constraint location
        unique (location)
);

insert into location
values (2, '七栋下'),
       (1, '六栋下'),
       (3, '台阶内');

create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(16) not null,
    password varchar(16) not null,
    status   tinyint     not null,
    constraint username
        unique (username)
);