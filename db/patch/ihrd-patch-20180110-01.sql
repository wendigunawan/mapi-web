ALTER TABLE mst_department
  ADD parent_department_id VARCHAR(36);


CREATE TABLE mst_skill (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_skill
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_certificate (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_certificate
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_employement_status (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_employement_status
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );

CREATE TABLE mst_termination_reason (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_termination_reason
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_bank (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_bank
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_employee_document (
  id               VARCHAR(36)  NOT NULL PRIMARY KEY,
  employee_id      VARCHAR(36)  NOT NULL,
  document_type_id VARCHAR(36)  NOT NULL,
  file_name        VARCHAR(255) NOT NULL,
  file_name_origin VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;


ALTER TABLE mst_employee_document
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_employee_education (
  id               VARCHAR(36)  NOT NULL PRIMARY KEY,
  employee_id      VARCHAR(36)  NOT NULL,
  education_id     VARCHAR(36)  NOT NULL,
  start_date       DATE,
  end_date         DATE,
  description      VARCHAR(255),
  file_name        VARCHAR(255) NOT NULL,
  file_name_origin VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_employee_education
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_employee_skill (
  id          VARCHAR(36) NOT NULL PRIMARY KEY,
  employee_id VARCHAR(36) NOT NULL,
  skill_id    VARCHAR(36) NOT NULL
)
  ENGINE = InnoDB;


ALTER TABLE mst_employee_skill
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_document_type (
  id   VARCHAR(36)  NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

ALTER TABLE mst_document_type
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );


CREATE TABLE mst_employee_certificate (
  id               VARCHAR(36) NOT NULL PRIMARY KEY,
  employee_id      VARCHAR(36) NOT NULL,
  certificate_id   VARCHAR(36) NOT NULL,
  certificate_date DATE,
  description      VARCHAR(255),
  file_name        VARCHAR(255),
  file_name_origin VARCHAR(255)
)
  ENGINE = InnoDB;


ALTER TABLE mst_employee_certificate
  ADD (
  created_date DATETIME,
  created_by VARCHAR(36),
  updated_date DATETIME,
  updated_by VARCHAR(36),
  last_action VARCHAR(100),
  deleted INT(1) NOT NULL DEFAULT 0
  );
