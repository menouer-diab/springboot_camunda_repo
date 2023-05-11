create sequence hibernate_sequence start with 1 increment by 1;
create table credit_request (id bigint not null, amount_in_euros double, customer_name varchar(255), score integer, primary key (id));
