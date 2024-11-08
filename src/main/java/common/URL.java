package common;

public class URL {
    public static final String BASE_URL = "http://localhost:8081/api";
    public static final String GET_EMPLOYEE = "/employees/%d";
    public static final String EMPLOYEES = "/employees";
    public static final String GET_EMPLOYEE_BY_FIRST_NAME = "/employees/findByFirstName?firstName=%s";
    public static final String GET_EMPLOYEE_BY_LAST_NAME = "/employees/findByLastName?lastName=%s";
    public static final String GET_EMPLOYEE_BY_FIRST_AND_LAST_NAME = "employees/findByFullName?firstName=%s&lastName=%s";
    public static final String EMPLOYEES_EXPORT_EXCEL = "/employees/export/excel";
}
