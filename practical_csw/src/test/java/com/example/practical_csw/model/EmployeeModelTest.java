package com.example.practical_csw.model;

import com.example.practical_csw.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.List;

class EmployeeModelTest {

    private EmployeeModel employeeModel;

    @BeforeEach
    void setUp() {
        employeeModel = new EmployeeModel();
    }

    @Test
    void save() throws SQLException {
        Employee employee = new Employee("Employee 1", 10000, 1);
        Employee insertedEmployee = EmployeeModel.save(employee);
        assertThat(insertedEmployee.getId()).isNotEqualTo(0);
    }

    @Test
    void findAll() throws SQLException {
        Employee employee = new Employee("Employee 1", 10000, 1);
        EmployeeModel.save(employee);
        List<Employee> employeeList = employeeModel.findAll();
        assertThat(employeeList.size()).isGreaterThan(0);
    }

    @Test
    void findById() throws SQLException {
        Employee employee = new Employee("Employee 1", 10000, 1);
        Employee insertedEmployee = EmployeeModel.save(employee);
        Employee foundEmployee = EmployeeModel.findById(insertedEmployee.getId());
        assertThat(foundEmployee).isNotNull();
    }

    @Test
    void update() throws SQLException {
        Employee employee = new Employee("Employee 1", 10000, 1);
        Employee insertedEmployee = EmployeeModel.save(employee);
        Employee updateEmployee = new Employee("Employee 2", 10000, 0);
        Employee updatedEmployee = EmployeeModel.update(insertedEmployee.getId(), updateEmployee);
        assertThat(updatedEmployee).isEqualToComparingFieldByFieldRecursively(updateEmployee);
//        assertThat(updatedEmployee.getName()).isEqualTo(updateEmployee.getName());
//        assertThat(updatedEmployee.getPrice()).isEqualTo(updateEmployee.getPrice());
//        assertThat(updatedEmployee.getStatus()).isEqualTo(updateEmployee.getStatus());
    }

    @Test
    void delete() throws SQLException {
        Employee employee = new Employee("Employee 1", 10000, 1);
        Employee insertedEmployee = EmployeeModel.save(employee);
        boolean result = EmployeeModel.delete(insertedEmployee.getId());
        assertThat(result).isEqualTo(true);
    }
}