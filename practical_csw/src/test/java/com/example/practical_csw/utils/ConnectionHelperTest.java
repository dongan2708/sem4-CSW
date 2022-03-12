package com.example.practical_csw.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionHelperTest {

    @Test
    void getConnection() throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("insert into employee (name, salary) values ('Ngan', 1000)");
        preparedStatement.execute();
    }
}