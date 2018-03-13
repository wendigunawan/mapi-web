package com.mapi.ihrd.config;

public class Constant {


    //auth
    public static final String TABLE_NAME_USER = "sys_user";
    public static final String TABLE_NAME_USER_TOKEN = "sys_user_token";
    public static final String TABLE_NAME_ROLE = "sys_role";
    public static final String TABLE_NAME_USER_ROLE = "sys_user_role";
    public static final String TABLE_NAME_PERMISSION = "sys_permission";
    public static final String TABLE_NAME_ROLE_PERMISSION = "sys_role_permission";

    //employee
    public static final String TABLE_DOCUMENT_TYPE = "mst_document_type";
    public static final String TABLE_EDUCATION = "mst_education";
    public static final String TABLE_RELIGION = "mst_religion";
    public static final String TABLE_NAME_JOB = "mst_job";
    public static final String TABLE_NAME_PERFORM_COMPONENT = "mst_performance_component";
    public static final String TABLE_NAME_DEPARTMENT = "mst_department";
    public static final String TABLE_NAME_EMPLOYEE = "mst_employee";
    public static final String TABLE_NAME_JOB_HISTORY = "trx_job_history";
    public static final String TABLE_NAME_ATTENDANCE = "trx_attendance";
    public static final String TABLE_NAME_PAYCHECK = "trx_paycheck";
    public static final String TABLE_NAME_PERFORMANCE = "trx_performance";
    public static final String TABLE_NAME_EMPLOYMENT_STATUS = "mst_employment_status";


    //leave
    public static final String TABLE_NAME_LEAVE_REQUEST = "trx_leave_request";

    public static final String TABLE_NAME_OVERTIME_REQUEST = "trx_overtime_request";
    public static final String TABLE_NAME_DELAY_REQUEST = "trx_delay_request";


    public static final String TABLE_NAME_HOSPITAL = "mst_hospital";
    public static final String TABLE_NAME_DOCTOR = "mst_doctor";
    public static final String TABLE_NAME_BOOK = "cms_book";

    //news
    public static final String TABLE_NAME_NEWS = "cms_news";

    //view
    public static final String VIEW_NAME_USER = "v_user";
    public static final String VIEW_NAME_LEAVE_REQUEST = "v_leave_request";
    public static final String VIEW_NAME_OVERTIME_REQUEST = "v_overtime_request";
    public static final String VIEW_NAME_ATTENDANCE = "v_attendance";
    public static final String VIEW_NAME_PERFORMANCE = "v_performance";

    public static final int ERROR_CODE_300 = 300;
    public static final int ERROR_CODE_301 = 301;
    public static final int ERROR_CODE_302 = 302;
    public static final int ERROR_CODE_303 = 303;
    public static final int ERROR_CODE_304 = 304;

    public static int DISPLAY_LENGTH = 10;

    public static final String ROLE_CODE_STAFF = "staff";


}
