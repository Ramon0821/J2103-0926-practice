package com.futureweaver.web;

import com.futureweaver.domain.ContactInfo;
import com.futureweaver.service.ContactService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/query_contact")
public class QueryContactServlet extends HttpServlet {
    private ContactService service=new ContactService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //处理数据
        List<ContactInfo> contacts=service.queryAll();
        request.setAttribute("contacts",contacts);
        //响应数据
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
