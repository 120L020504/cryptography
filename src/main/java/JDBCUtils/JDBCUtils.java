package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;//创建数据库资源
    static{
        Properties pro = new Properties();
        FileReader fr = null;
        try{
            fr = new FileReader("C:\\Users\\w\\IdeaProjects\\cryptography\\src\\main\\java\\JDBCUtils\\druid.properties");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            pro.load(fr);
        }catch(IOException e){
            e.printStackTrace();
        }
        try {
            ds = DruidDataSourceFactory.createDataSource(pro);//获取连接池对象
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();//创建与数据源的连接
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){//关闭结果集，statemrnt，connection
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt, Connection conn){//只关闭statement和connection
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static DataSource getDataSource(){
        return ds;
    }

}
