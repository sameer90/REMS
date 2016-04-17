package com.rems.connection;

/**
 * @name: PoolBean
 * @author: Team Amrita Virdi
 * @description: This is a bean class for Connection Pooling which contains static pool connection Objects.
 **/
import java.io.*;

public class PoolBean {

	public static ConnectionPool con = null;
	public static ConnectionPool con_a = null;

	public PoolBean() throws IOException {

	}

	public static ConnectionPool getAdminPoolObject() {
		return con_a;
	}

	public void setAdminPoolObject(ConnectionPool con_a) {
		this.con_a = con_a;
	}

	public static ConnectionPool getPoolObject() {
		return con;
	}

	public void setPoolObject(ConnectionPool con) {
		this.con = con;
	}
}