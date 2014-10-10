# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table s3file (
  id                        varchar(40) not null,
  bucket                    varchar(255),
  name                      varchar(255),
  email_id                  varchar(255),
  constraint pk_s3file primary key (id))
;

create table users (
  email                     varchar(255) not null,
  password                  varchar(255),
  constraint pk_users primary key (email))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table s3file;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

