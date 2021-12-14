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
	//创建连接池对象
	public static BasicDataSource datasource=new BasicDataSource();

	static {
		try {
			//使用Properties处理流 加载文件
			Properties props=new Properties();
			Reader reader=new FileReader("jdbc.properties");
			props.load(reader);
			//根据key读取需要的值
			drivername=props.getProperty("drivername");
			url=props.getProperty("url");
			user=props.getProperty("user");
			password=props.getProperty("password");
			
			//对连接池对象进行基本的配置
			datasource.setDriverClassName(drivername);
			datasource.setUrl(url);
			datasource.setUsername(user);
			datasource.setPassword(password);
			//最大连接数
			datasource.setMaxActive(10);
			//最小空闲连接
			datasource.setMinIdle(2);
			//最大空闲连接
			datasource.setMaxIdle(5);
			//初始化连接
			datasource.setInitialSize(2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回连接池对象
	public static  BasicDataSource getDataSource(){
		return datasource;
	}

}
