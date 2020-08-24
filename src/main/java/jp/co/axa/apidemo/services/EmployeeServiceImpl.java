package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Implementing constructor injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // retrieveEmployees and put them in
    @Override
    public List<Employee> retrieveEmployees() {
        final List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    // Get Employee info from employeeRepository -- implementation of the interface method
    // returns an Optional. If the method returns returns Null throw an exception
    @Override
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.orElseThrow(NotFoundException::new);
    }

    // Save Employee info to employeeRepository -- implementation of the interface method
    @Override
    public void saveEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    // Delete Employee info from employeeRepository -- implementation of the interface method
    @Override
    public void deleteEmployee(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    // Update Employee info from employeeRepository -- implementation of the interface method
    @Override
    public void updateEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
    }

    // Exception-to-be-thrown is an Employee doesn't exits
    // The exception is directly mapped to 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException {
    }

}
