create table mst_education (
  id varchar(36) primary key,
  name varchar(100) not null,
  description varchar(255)
) ENGINE=InnoDB;

create table mst_religion (
  id varchar(36) primary key,
  name varchar(100) not null,
  description varchar(255)
) ENGINE=InnoDB;


alter table mst_education add (
  created_date datetime,
  created_by varchar(36),
  updated_date datetime,
  updated_by varchar(36),
  last_action varchar(100),
  deleted int(1) not null default 0
  );

alter table mst_religion add (
  created_date datetime,
  created_by varchar(36),
  updated_date datetime,
  updated_by varchar(36),
  last_action varchar(100),
  deleted int(1) not null default 0
  );
