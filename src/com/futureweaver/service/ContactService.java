package com.futureweaver.service;

import com.futureweaver.dao.ContactDAO;
import com.futureweaver.domain.ContactInfo;
import com.futureweaver.domain.User;

import javax.jnlp.ClipboardService;
import java.util.List;

public class ContactService {
    private ContactDAO dao=new ContactDAO();
    public List<ContactInfo> queryAll() {
        List<ContactInfo> result = dao.queryAll();
        return result;
    }

    public boolean delete(String delId) {
        int count=dao.delete(delId);
        return count==1;
    }

    public boolean add(ContactInfo contact) {
        int count =dao.add(contact);
        return count==1;
    }

    public ContactInfo queryById(String updateId) {
        ContactInfo result=dao.queryById(updateId);
        return result;
    }

    public boolean update(ContactInfo contact) {
        int count=dao.update(contact);
        return count==1;
    }
    public int queryCount() {
        return dao.queryCount();
    }
    public List<ContactInfo> queryAll(int currentPage, int pageSize) {
        //从0开始
        //currentPage=1,pageSize=10。LIMIT第一个关键字：0，第二个关键字：10
        //currentPage=2,pageSize=15。LIMIT第一个关键字：15，第二个关键字：15
        //currentPage=3,pageSize=5。LIMIT第一个关键字：10，第二个关键字：5
        //LIMIT第一个关键字：(currentPage-1) * pageSize
        int pageOffset=(currentPage-1)*pageSize;
        return dao.queryAll(pageOffset,pageSize);
    }

    public boolean query(User user) {
        int c=dao.query(user);
        return c==1;
    }
}
