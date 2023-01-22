/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/12/24 17:08:57                          */
/*==============================================================*/


drop trigger arecord_after_delete;

drop trigger arecord_after_insert;

drop trigger frecord_after_delete;

drop trigger frecord_after_insert;

drop
    table if exists catinfoview;

drop index cat_name_index on cat;

drop index index_category_name on category;

/*==============================================================*/
/* Table: appearance_record                                     */
/*==============================================================*/
create table appearance_record
(
    record_id   int auto_increment,
    cat_id      int         not null,
    username    varchar(16) not null,
    location_id int         not null,
    record_time char(16)    not null,
    last_update datetime,
    primary key (record_id)
);

/*==============================================================*/
/* Table: appearance_record_tmp                                 */
/*==============================================================*/
create table appearance_record_tmp
(
    record_id   int auto_increment
        primary key,
    cat_id      int         not null,
    username    varchar(16) not null,
    location_id int         not null,
    record_time char(16)    not null,
    last_update datetime    null,
    former_id   int         null,
    constraint FK_a_record_tmp_location
        foreign key (location_id) references campus_cat_feeding.location (location_id),
    constraint FK_cat_a_record_tmp
        foreign key (cat_id) references campus_cat_feeding.cat (cat_id),
    constraint FK_user_a_record_tmp
        foreign key (username) references campus_cat_feeding.user_info (username)
);


/*==============================================================*/
/* Table: cat                                                   */
/*==============================================================*/
create table cat
(
    cat_id       int auto_increment,
    cat_name     varchar(15) not null,
    category_id  int         not null,
    color_id     int         not null,
    character_id int         not null,
    location_id  int,
    record_count int,
    primary key (cat_id)
);

/*==============================================================*/
/* Index: cat_name_index                                        */
/*==============================================================*/
create unique index cat_name_index on cat
    (
     cat_name
        );

/*==============================================================*/
/* Index: cat_tmp                                               */
/*==============================================================*/
create table cat_tmp
(
    cat_id       int auto_increment
        primary key,
    cat_name     varchar(15) not null,
    category_id  int         not null,
    color_id     int         not null,
    character_id int         not null,
    location_id  int         null,
    record_count int         null,
    username     varchar(16) not null,
    constraint FK_catTmp_category
        foreign key (category_id) references campus_cat_feeding.category (category_id),
    constraint FK_catTmp_character
        foreign key (character_id) references campus_cat_feeding.cat_character (character_id),
    constraint FK_catTmp_color
        foreign key (color_id) references campus_cat_feeding.color (color_id),
    constraint FK_catTmp_location
        foreign key (location_id) references campus_cat_feeding.location (location_id),
    constraint FK_catTmp_user
        foreign key (username) references campus_cat_feeding.user_info (username)
);


/*==============================================================*/
/* Table: cat_character                                         */
/*==============================================================*/
create table cat_character
(
    character_id int auto_increment,
    `character`  varchar(15) not null,
    primary key (character_id)
);

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
    category_id int auto_increment,
    category    varchar(15) not null,
    primary key (category_id)
);

/*==============================================================*/
/* Index: index_category_name                                   */
/*==============================================================*/
create unique index index_category_name on category
    (
     category
        );

/*==============================================================*/
/* Table: color                                                 */
/*==============================================================*/
create table color
(
    color_id int auto_increment,
    color    varchar(15) not null,
    primary key (color_id)
);

/*==============================================================*/
/* Table: feeding_record                                        */
/*==============================================================*/
create table feeding_record
(
    record_id   int auto_increment,
    cat_id      int         not null,
    username    varchar(16) not null,
    location_id int         not null,
    record_time char(16)    not null,
    remarks     varchar(50),
    last_update datetime,
    primary key (record_id)
);

/*==============================================================*/
/* Table: feeding_record_tmp                                    */
/*==============================================================*/
create table feeding_record_tmp
(
    record_id   int auto_increment
        primary key,
    cat_id      int         not null,
    username    varchar(16) not null,
    location_id int         not null,
    record_time char(16)    not null,
    remarks     varchar(50) null,
    last_update datetime    null,
    former_id   int         null,
    constraint FK_cat_f_record_tmp
        foreign key (cat_id) references campus_cat_feeding.cat (cat_id),
    constraint FK_f_record_tmp_location
        foreign key (location_id) references campus_cat_feeding.location (location_id),
    constraint FK_user_f_record_tmp
        foreign key (username) references campus_cat_feeding.user_info (username)
);


/*==============================================================*/
/* Table: location                                              */
/*==============================================================*/
create table location
(
    location_id int auto_increment,
    location    varchar(20) not null,
    primary key (location_id)
);

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
    username varchar(16) not null,
    password varchar(16) not null,
    status   smallint    not null,
    primary key (username)
);

/*==============================================================*/
/* View: catinfoview                                            */
/*==============================================================*/
create VIEW catinfoview
as
select cat.cat_id,
       cat.cat_name,
       cat.category_id,
       category.category,
       cat.color_id,
       color.color,
       cat.character_id,
       cat_character.`character`,
       cat.location_id,
       location.location,
       cat.record_count
from cat,
     category,
     color,
     cat_character,
     location
where cat.category_id = category.category_id
  and cat.color_id = color.color_id
  and cat.character_id = cat_character.character_id
  and cat.location_id = location.location_id;

CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`localhost` SQL SECURITY DEFINER VIEW `appearance_view`
AS
select `appearance_record`.`record_id`   AS `record_id`,
       `appearance_record`.`cat_id`      AS `cat_id`,
       `cat`.`cat_name`                  AS `cat_name`,
       `appearance_record`.`username`    AS `username`,
       `appearance_record`.`location_id` AS `location_id`,
       `location`.`location`             AS `location`,
       `appearance_record`.`record_time` AS `record_time`,
       `appearance_record`.`last_update` AS `last_update`
from ((`appearance_record` join `cat`) join `location`)
where ((`appearance_record`.`cat_id` = `cat`.`cat_id`) and
       (`appearance_record`.`location_id` = `location`.`location_id`));

CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`localhost` SQL SECURITY DEFINER VIEW `appearance_tmp_view` AS
select `appearance_record_tmp`.`record_id`   AS `record_id`,
       `appearance_record_tmp`.`cat_id`      AS `cat_id`,
       `cat`.`cat_name`                      AS `cat_name`,
       `appearance_record_tmp`.`username`    AS `username`,
       `appearance_record_tmp`.`location_id` AS `location_id`,
       `location`.`location`                 AS `location`,
       `appearance_record_tmp`.`record_time` AS `record_time`,
       `appearance_record_tmp`.`last_update` AS `last_update`,
       `appearance_record_tmp`.`former_id`   AS `former_id`
from ((`appearance_record_tmp` join `cat`) join `location`)
where ((`appearance_record_tmp`.`cat_id` = `cat`.`cat_id`) and
       (`appearance_record_tmp`.`location_id` = `location`.`location_id`));

CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`localhost` SQL SECURITY DEFINER VIEW `cattmpinfoview` AS
select `cat_tmp`.`cat_id`          AS `cat_id`,
       `cat_tmp`.`cat_name`        AS `cat_name`,
       `cat_tmp`.`category_id`     AS `category_id`,
       `category`.`category`       AS `category`,
       `cat_tmp`.`color_id`        AS `color_id`,
       `color`.`color`             AS `color`,
       `cat_tmp`.`character_id`    AS `character_id`,
       `cat_character`.`character` AS `character`,
       `cat_tmp`.`location_id`     AS `location_id`,
       `location`.`location`       AS `location`,
       `cat_tmp`.`record_count`    AS `record_count`,
       `cat_tmp`.`username`        AS `username`
from ((((`cat_tmp` join `category`) join `color`) join `cat_character`) join `location`)
where ((`cat_tmp`.`category_id` = `category`.`category_id`) and (`cat_tmp`.`color_id` = `color`.`color_id`) and
       (`cat_tmp`.`character_id` = `cat_character`.`character_id`) and
       (`cat_tmp`.`location_id` = `location`.`location_id`));

CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`localhost` SQL SECURITY DEFINER VIEW `feeding_tmp_view` AS
select `feeding_record_tmp`.`record_id`   AS `record_id`,
       `feeding_record_tmp`.`cat_id`      AS `cat_id`,
       `cat`.`cat_name`                   AS `cat_name`,
       `feeding_record_tmp`.`username`    AS `username`,
       `feeding_record_tmp`.`location_id` AS `location_id`,
       `location`.`location`              AS `location`,
       `feeding_record_tmp`.`record_time` AS `record_time`,
       `feeding_record_tmp`.`remarks`     AS `remarks`,
       `feeding_record_tmp`.`last_update` AS `last_update`,
       `feeding_record_tmp`.`former_id`   AS `former_id`
from ((`feeding_record_tmp` join `cat`) join `location`)
where ((`feeding_record_tmp`.`cat_id` = `cat`.`cat_id`) and
       (`feeding_record_tmp`.`location_id` = `location`.`location_id`));

CREATE ALGORITHM = UNDEFINED DEFINER =`root`@`localhost` SQL SECURITY DEFINER VIEW `feeding_view` AS
select `feeding_record`.`record_id`   AS `record_id`,
       `feeding_record`.`cat_id`      AS `cat_id`,
       `cat`.`cat_name`               AS `cat_name`,
       `feeding_record`.`username`    AS `username`,
       `feeding_record`.`location_id` AS `location_id`,
       `location`.`location`          AS `location`,
       `feeding_record`.`record_time` AS `record_time`,
       `feeding_record`.`remarks`     AS `remarks`,
       `feeding_record`.`last_update` AS `last_update`
from ((`feeding_record` join `cat`) join `location`)
where ((`feeding_record`.`cat_id` = `cat`.`cat_id`) and (`feeding_record`.`location_id` = `location`.`location_id`));

alter table appearance_record
    add constraint FK_a_record_location foreign key (location_id)
        references location (location_id) on delete restrict on update restrict;

alter table appearance_record
    add constraint FK_cat_a_record foreign key (cat_id)
        references cat (cat_id) on delete restrict on update restrict;

alter table appearance_record
    add constraint FK_user_a_record foreign key (username)
        references user_info (username) on delete restrict on update restrict;

alter table cat
    add constraint FK_cat_category foreign key (category_id)
        references category (category_id) on delete restrict on update restrict;

alter table cat
    add constraint FK_cat_character foreign key (character_id)
        references cat_character (character_id) on delete restrict on update restrict;

alter table cat
    add constraint FK_cat_color foreign key (color_id)
        references color (color_id) on delete restrict on update restrict;

alter table cat
    add constraint FK_cat_location foreign key (location_id)
        references location (location_id) on delete restrict on update restrict;

alter table feeding_record
    add constraint FK_cat_f_record foreign key (cat_id)
        references cat (cat_id) on delete restrict on update restrict;

alter table feeding_record
    add constraint FK_f_record_location foreign key (location_id)
        references location (location_id) on delete restrict on update restrict;

alter table feeding_record
    add constraint FK_user_f_record foreign key (username)
        references user_info (username) on delete restrict on update restrict;


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

