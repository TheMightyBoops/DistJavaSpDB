package com.nolting.second;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenreConnection {
    private static final String DB_URL = "jdbc:derby:/home/lucas/School/DistJava/second/sql/GenreDB;create=true";

    private static Connection connection = null;

    public synchronized static Connection getConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL);
            } catch (SQLException sqle) {
                System.out.println("Failed at: dbConnect\n" + sqle.getMessage());
            }
        }
        return connection;
    }
}
