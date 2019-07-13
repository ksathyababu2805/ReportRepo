package com.aap.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.JdkSSLOptions;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DBUtils {
	
	String result = "";
	InputStream inputStream;
	Properties prop = new Properties();
	
		
	public String[] setOracleConnection(String maid) throws IOException{
		
		PreparedStatement stmt;
		String orgID = null;
		String propFileName = "config.properties";
		inputStream = new FileInputStream(propFileName);
		String[] response = new String[200];
		
		if (inputStream != null) {
			prop.load(inputStream);
			System.out.println("Got the property file");
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String connectionString = "jdbc:oracle:thin:@"+prop.getProperty("qa_db_host")+":"+prop.getProperty("qa_schema_name");
			Connection con=DriverManager.getConnection(  
					connectionString,prop.getProperty("qa_db_username"),prop.getProperty("qa_db_password"));
			System.out.println("**Opened the DB Connection***");
			stmt=con.prepareStatement("select * from PCS_USER where user_id like ?");
			stmt.setString(1,maid);
			System.out.println("Query used is "+stmt);
			ResultSet rs=stmt.executeQuery();  
			int i=0;
			while(rs.next()){
				response[i]=rs.getString(1);
				System.out.println("OrgID Selected is "+response[i]);
				i++;
			}		
			con.close();
			System.out.println("**Closed the DB Connection***");
			return response;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}	
	
	
	public static void main(String[] args) throws IOException {
		DBUtils dbCalls = new DBUtils();
	}
	
}
