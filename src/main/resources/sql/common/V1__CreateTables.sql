create sequence hibernate_sequence start with 1 increment by 1;

create table account
(
  id bigint not null default nextval('hibernate_sequence'),
  created timestamp without time zone,
  email character varying(255),
  password character varying(255),
  role character varying(255),
  constraint account_pkey primary key (id),
  constraint uk_account_email unique (email)
);

create table currency
(
  id bigint not null default nextval('hibernate_sequence'),
  name character varying(255),
  symbol character varying(255),
  constraint currency_pkey primary key (id),
  constraint uk_currency_symbol unique (symbol)
);

create table history
(
  id bigint not null default nextval('hibernate_sequence'),
  historiclookup boolean not null,
  rate numeric(19,4),
  ratedate timestamp without time zone,
  requesttime timestamp without time zone,
  sourcecurrency character varying(255),
  targetcurrency character varying(255),
  account_id bigint,
  constraint history_pkey primary key (id),
  constraint fk_account_id foreign key (account_id)
      references account (id) match simple
      on update no action on delete no action
);

create table country
(
  id bigint not null default nextval('hibernate_sequence'),
  isoCode character varying(255),
  name character varying (255)
);

