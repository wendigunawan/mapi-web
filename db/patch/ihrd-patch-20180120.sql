alter table trx_overtime_request add (
	created_date datetime,
	created_by varchar(36),
	updated_date datetime,
	updated_by varchar(36),
	last_action varchar(100),
	deleted int(1) not null default 0
);