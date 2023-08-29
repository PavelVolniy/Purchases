create if not exists table branches (
    id bigint not null,
    name varchar(255),
    primary key (id)) engine=InnoDB;
create if not exists table contracts (
id bigint not null,
    additional_agreement varchar(255),
    date_of_change varchar(255),
    date_of_contract varchar(255),
    date_of_execution_contract varchar(255),
    f_i_o varchar(255), name_of_contract varchar(255),
    name_user_to_changed varchar(255),
    number_of_contract varchar(255),
    number_of_registry_entry varchar(255),
    okdp2 varchar(255), pp_poz_ep varchar(255),
    sum double precision,
    branch_id bigint,
    supplier_inn bigint,
    type_of_company_id bigint,
    type_of_purchase_id bigint,
    user_id bigint,
    primary key (id)) engine=InnoDB;
create if not exists table hibernate_sequence (
next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );
create if not exists table purchases (
    id bigint not null,
    application_admitted integer not null,
    application_submitted integer not null,
    condition_of_purchase bit not null,
    date_of_end varchar(255),
    date_of_placement varchar(255),
    date_of_review varchar(255),
    difference_values double precision not null,
    economy double precision not null,
    name_purchase varchar(255),
    number_of_contract varchar(255),
    number_of_procedure_oneis integer not null,
    price_application_one double precision not null,
    price_application_two double precision not null,
    price_of_contract double precision not null,
    start_price double precision,
    branch_id bigint,
    type_of_purchase_id bigint,
    user_id bigint,
    primary key (id)) engine=InnoDB;
create if not exists table suppliers (
    id bigint not null,
    inn varchar(255),
    name_supplier varchar(255),
    primary key (id)) engine=InnoDB;
create if not exists table type_of_company (
    id bigint not null,
    name_type_company varchar(255),
    primary key (id)) engine=InnoDB;
create if not exists table types_of_purchase (
    id bigint not null,
    name_type_of_purchase varchar(255),
    primary key (id)) engine=InnoDB;
create if not exists table user_role (
    user_id bigint not null,
    roles varchar(255))
    engine=InnoDB;
create if not exists table usr (
    id bigint not null,
    active bit not null,
    is_block bit not null,
    password varchar(255),
    username varchar(255),
    branch_id bigint,
    primary key (id)) engine=InnoDB;
alter table contracts add constraint FK78avcunk4iv8mti7oqk3q8cpi foreign key (branch_id) references branches (id);
alter table contracts add constraint FKm318p2juli1p9h4mfht0gdb0e foreign key (supplier_inn) references suppliers (id);
alter table contracts add constraint FK18dbxv2t5kxe20r9xlly6d5wr foreign key (type_of_company_id) references type_of_company (id);
alter table contracts add constraint FKnkyx158j9wxthmsex1u48fpw5 foreign key (type_of_purchase_id) references types_of_purchase (id);
alter table contracts add constraint FK85g3rg0o1lhy3b25ds4jpq9v8 foreign key (user_id) references usr (id);
alter table purchases add constraint FK1p0d6jl6jgvwmid7pssnseu74 foreign key (branch_id) references branches (id);
alter table purchases add constraint FKeneqjbwxcu35nn2ny0ri8htee foreign key (type_of_purchase_id) references types_of_purchase (id);
alter table purchases add constraint FKbk5re2nwl8wfffb4r83r2mp9k foreign key (user_id) references usr (id);
alter table user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr (id);
alter table usr add constraint FKnax85fnimhl2o1fwhxlnigt6e foreign key (branch_id) references branches (id);
