package com.example.practical_csw.model;



import com.example.practical_csw.entity.Employee;
import com.example.practical_csw.utils.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    private static Connection conn;

    public EmployeeModel() {
        conn = ConnectionHelper.getConnection();
    }

    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from employees where status = ?");
        stmt.setInt(1, 1);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double salary = rs.getInt("salary");
            int status = rs.getInt("status");
            employees.add(new Employee(id, name, salary, status));
        }
        return employees;
    }

    public static Employee findById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from employees where status = ? and id = ?");
        stmt.setInt(1, 1);
        stmt.setInt(2, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            double salary = rs.getInt("salary");
            int status = rs.getInt("status");
            return new Employee(id, name, salary, status);
        }
        return null;
    }

    public static Employee save(Employee employee) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("insert into employees (name, salary, status) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, employee.getName());
        stmt.setDouble(2, employee.getSalary());
        stmt.setInt(3, employee.getStatus());
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet resultSetGeneratedKeys = stmt.getGeneratedKeys();
            if (resultSetGeneratedKeys.next()) {
                int id = resultSetGeneratedKeys.getInt(1);
                employee.setId(id);
                return employee;
            }
        }
        return null;
    }

    public static Employee update(int id, Employee updateEmployee) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("update employees set name = ?, salary = ?, status = ? where id = ?");
        stmt.setString(1, updateEmployee.getName());
        stmt.setDouble(2, updateEmployee.getSalary());
        stmt.setInt(3, updateEmployee.getStatus());
        stmt.setInt(4, id);
        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            updateEmployee.setId(id);
            return updateEmployee;
        }
        return null;
    }

    public static boolean delete(int id) throws SQLException {
        PreparedStatement stmtDelete = conn.prepareStatement("delete from employees where id = ?");
        stmtDelete.setInt(1, id);
        int affectedRows = stmtDelete.executeUpdate();
        if (affectedRows > 0) {
            return true;
        }
        return false;
    }
}
