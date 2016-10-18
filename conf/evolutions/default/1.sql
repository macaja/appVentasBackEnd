# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table articulo (
  id                            integer not null,
  nombre                        varchar(255),
  constraint pk_articulo primary key (id)
);
create sequence articulo_seq;


# --- !Downs

drop table if exists articulo;
drop sequence if exists articulo_seq;

