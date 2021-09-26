package com.futureweaver.web;

import com.futureweaver.domain.ContactInfo;
import com.futureweaver.service.ContactService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Currency;
import java.util.List;

@WebServlet("/query_contact_page")
public class QueryContactPageServlet extends HttpServlet {
    private ContactService service = new ContactService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //1、接收数据
        //要看第几页
        //默认第一页
        int currentPage=1;
        //获取前端发送过来的要看第几页
        String currentPageParam=request.getParameter("currentPage");
        //如果真的发了，那么将字符串转换成int类型
        //如果没发，保持currentPage为1即可
        if (currentPageParam!=null){
            currentPage=Integer.parseInt(currentPageParam);
        }
        //处理一页几条
        //默认一页10条
        int pageSize=10;
        //获取前端发送过来的请求的一页要看几条
        String pageSizeParam = request.getParameter("pageSize");
        //如果真的发了，那么将字符串转换成int类型
        //如果没发，那么pageSize保持默认值10即可
        if (pageSizeParam!=null){
            pageSize=Integer.parseInt(pageSizeParam);
        }
        //2、处理数据
        int recordCount=service.queryCount();
        int pageCount=(int)Math.ceil(recordCount/(double)pageSize);
        List<ContactInfo> contacts = service.queryAll(currentPage, pageSize);
        //3、响应数据
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("recordCount",recordCount);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("contacts",contacts);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
