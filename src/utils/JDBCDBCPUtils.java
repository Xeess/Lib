package utils;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class JDBCDBCPUtils {
	private static  String drivername;
	private static  String url;
	private static  String user;
	private static  String password;
	//�������ӳض���
	public static BasicDataSource datasource=new BasicDataSource();

	static {
		try {
			//ʹ��Properties������ �����ļ�
			Properties props=new Properties();
			Reader reader=new FileReader("jdbc.properties");
			props.load(reader);
			//����key��ȡ��Ҫ��ֵ
			drivername=props.getProperty("drivername");
			url=props.getProperty("url");
			user=props.getProperty("user");
			password=props.getProperty("password");
			
			//�����ӳض�����л���������
			datasource.setDriverClassName(drivername);
			datasource.setUrl(url);
			datasource.setUsername(user);
			datasource.setPassword(password);
			//���������
			datasource.setMaxActive(10);
			//��С��������
			datasource.setMinIdle(2);
			//����������
			datasource.setMaxIdle(5);
			//��ʼ������
			datasource.setInitialSize(2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�������ӳض���
	public static  BasicDataSource getDataSource(){
		return datasource;
	}

}
