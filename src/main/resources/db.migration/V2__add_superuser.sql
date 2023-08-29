update hibernate_sequence set next_val= 1 where next_val=1;
insert into branches (id, name) values(1,  'Центральный') engine=InnoDB;

insert into usr (active, branch_id, is_block, password, username, id) values(1,true,false,'w','w',1) engine=InnoDB;

insert into user_role (user_id, roles) values(1,'SUPERUSER') engine=InnoDB;