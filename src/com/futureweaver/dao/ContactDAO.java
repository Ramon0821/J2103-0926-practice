package com.futureweaver.dao;

import com.futureweaver.domain.ContactInfo;
import com.futureweaver.domain.User;
import com.futureweaver.utils.DataSourceManager;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.List;

public class ContactDAO {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceManager.getDataSource());

    public List<ContactInfo> queryAll() {
        return jdbcTemplate.query("select * from contact_info where del=0", new BeanPropertyRowMapper<>(ContactInfo.class));
    }

    public int delete(String delId) {
        int result = jdbcTemplate.update("update contact_info set del=1 where id=?", delId);
        return result;
    }

    public int add(ContactInfo contact) {
        int update = jdbcTemplate.update(
                "INSERT into contact_info(name,gender,birthday,birthplace,mobile,email)values(?,?,?,?,?,?)",
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail()
        );
        return update;
    }

    public ContactInfo queryById(String updateId) {
        ContactInfo result;
        try {
            result = jdbcTemplate.queryForObject("select * from contact_info where id=? and del=0"
                    , new BeanPropertyRowMapper<>(ContactInfo.class), updateId);
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }
        return result;
    }

    public int update(ContactInfo contact) {
        int result = jdbcTemplate.update("update contact_info set name=?,gender=?,birthday=?,birthplace=?,mobile=?,email=? where id=? and del=0",
                contact.getName(),
                contact.getGender(),
                contact.getBirthday(),
                contact.getBirthplace(),
                contact.getMobile(),
                contact.getEmail(),
                contact.getId());
        return result;
    }

    public int queryCount() {
        return jdbcTemplate.queryForObject("select count(*) from contact_info where del=0",
                Integer.class);
    }

    public List<ContactInfo> queryAll(int pageOffset, int pageSize) {
        return jdbcTemplate.query("select * from contact_info where del=0 LIMIT ?,?",
                new BeanPropertyRowMapper<>(ContactInfo.class), pageOffset, pageSize);
    }


    public int query(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Integer integer = jdbcTemplate.queryForObject("select count(*) from user where username=? and password=?", Integer.class, username, password);
        return integer;
    }
}
