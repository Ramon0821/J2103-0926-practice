package com.futureweaver.web;

import com.futureweaver.utils.DataSourceManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/data_source_init", loadOnStartup = 2)
public class DataSourceInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 当DataSource初始化时(它的初始化是比较早的，因为设置了loadOnStartup了)
        // 就让DataSourceManager初始化
        ServletContext application = getServletContext();
        DataSourceManager.init(application);
    }

    // 因为只有当用户的浏览器访问doGet或doPost时，才会执行以下代码
    // 这样的话，并不能提早地向DataSourceManaager发送ServletContext
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        // 1. 接收数据

        // 2. 处理数据

        // 3. 响应数据
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
