package com.futureweaver.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceManager {
    private static DataSource ds;

    // 为什么不用静态代码块:
    // 原因: 把配置文件放到了web目录中，所以想要读这个配置文件，只能使用ServletContext
    // DataSourceManager没有能力在静态代码块中，就能获取ServletContext

    // 所以需要委托别的代码，把ServletContext传进来
    // 才能初始化

    // 应该用Servlet获取ServletContext
    // 也应该尽量早地让Servlet准备好
    // 让Servlet把获取到的ServletContext传过来
    public static void init(ServletContext application) {
        try {
            InputStream is = application.getResourceAsStream("WEB-INF/druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
