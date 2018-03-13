CREATE TABLE mst_document_type (
  id          VARCHAR(36) PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(255)
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
