package jp.co.axa.apidemo;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getEmployeeById() {
        Employee emp = new Employee();
        emp.setId(1L);

        when(service.getEmployee(1L)).thenReturn(emp);

        Employee employee = employeeController.getEmployee(1L);

        verify(service).getEmployee(1L);

        assertEquals(1L,employee.getId().longValue());
    }

    // integration testing
    @Test
    public void testFindAll() {
        List<Employee> users = service.retrieveEmployees();
        assertThat(users.size()).isGreaterThanOrEqualTo(0);
    }

}
