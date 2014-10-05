# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table s3file (
  id                        varchar(40) not null,
  bucket                    varchar(255),
  name                      varchar(255),
  constraint pk_s3file primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table s3file;

SET FOREIGN_KEY_CHECKS=1;

