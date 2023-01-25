drop table if exists color;
drop table if exists category;
drop table if exists cat_character;
drop table if exists cat;
drop table if exists cat_tmp;
drop table if exists appearance_record;
drop table if exists appearance_record_tmp;
drop table if exists feeding_record;
drop table if exists feeding_record_tmp;
drop table if exists location;
drop table if exists user;

create table color
(
    color_id bigint auto_increment
        primary key,
    color    varchar(15) not null unique
);
create table cat_character
(
    character_id  bigint auto_increment
        primary key,
    cat_character varchar(15) not null unique
);
create table category
(
    category_id bigint auto_increment
        primary key,
    category    varchar(15) not null unique
);
create table location
(
    location_id bigint auto_increment
        primary key,
    location    varchar(20) not null unique
);
create table cat
(
    cat_id       bigint auto_increment
        primary key,
    cat_name     varchar(15) unique not null,
    category_id  bigint                not null,
    color_id     bigint                not null,
    character_id bigint                not null,
    location_id  bigint                not null,
    record_count int                null default 0
);
create table cat_tmp
(
    cat_id       bigint auto_increment
        primary key,
    cat_name     varchar(15) unique not null,
    category_id  bigint                not null,
    color_id     bigint                not null,
    character_id bigint                not null,
    location_id  bigint                not null,
    record_count int                not null,
    username     varchar(16)        not null,
    deleted      tinyint default 0  null,
    version      int     default 0  null
);
create table appearance_record
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint         not null,
    username    varchar(16) not null,
    location_id bigint         not null,
    record_time char(16)    not null,
    last_update datetime    null default now()
);

create trigger a_record_after_delete
    after delete
    on appearance_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
end;

create trigger a_record_after_insert
    after insert
    on appearance_record
    for each row
begin
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create trigger a_record_after_update
    after update
    on appearance_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;
create table appearance_record_tmp
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint           not null,
    username    varchar(16)   not null,
    location_id bigint           not null,
    record_time char(16)      not null,
    last_update datetime      null default now(),
    former_id   bigint           null,
    version     int default 0 null,
    deleted     int default 0 null
);
create table feeding_record
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint         not null,
    username    varchar(16) not null,
    location_id bigint         not null,
    record_time char(16)    not null,
    remarks     varchar(50) null,
    last_update datetime    null default now()
);

create trigger f_record_after_delete
    after delete
    on feeding_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
end;

create trigger f_record_after_insert
    after insert
    on feeding_record
    for each row
begin
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;

create trigger f_record_after_update
    after update
    on feeding_record
    for each row
begin
    update cat set record_count = record_count - 1 where cat.cat_id = OLD.cat_id;
    update cat set record_count = record_count + 1 where cat.cat_id = NEW.cat_id;
end;
create table feeding_record_tmp
(
    record_id   bigint auto_increment
        primary key,
    cat_id      bigint           not null,
    username    varchar(16)   not null,
    location_id bigint           not null,
    record_time char(16)      not null,
    remarks     varchar(50)   null,
    last_update datetime      null,
    former_id   bigint           null,
    version     int default 0 null,
    deleted     int default 0 null
);
create table user
(
    id       bigint      not null
        primary key auto_increment,
    username varchar(16) not null unique,
    password varchar(16) not null,
    status   tinyint     not null
);