package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

// An interface with methods to access and modify Employee data in EmployeeRepository
public interface EmployeeService {

    // get all employees from EmployeeRepository and store them in a List
    List<Employee> retrieveEmployees();

    // get a particular Employee from EmployeeRepository using employeeId
    Employee getEmployee(Long employeeId);

    // save an employee to EmployeeRepository
    void saveEmployee(Employee employee);

    // delete an Employee by employeeId from EmployeeRepository
    void deleteEmployee(Long employeeId);

    // update an employee in EmployeeRepository
    void updateEmployee(Employee employee);
}
