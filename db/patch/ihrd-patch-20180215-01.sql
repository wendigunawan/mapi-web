CREATE TRIGGER trg_sys_user_role_before_insert
  BEFORE INSERT
  ON ihrd_dev.sys_user_role
  FOR EACH ROW
  BEGIN
    SET NEW.id = UUID();
  END;