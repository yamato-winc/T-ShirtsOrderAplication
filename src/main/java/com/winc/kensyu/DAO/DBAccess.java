package com.winc.kensyu.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
	private static final String URL = "jdbc:db2://192.168.8.200:25000/sogo3";
    private static final String USER = "team1user";
    private static final String PASS = "password";

    public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        return DriverManager.getConnection(URL, USER, PASS);
    }
}