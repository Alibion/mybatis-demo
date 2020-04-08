package com.test.dao;

import com.test.dao.impl.UserDaoImpl;
import com.test.dto.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {
    public UserDao userDao;
    public SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {
        // mybatis-config.xml
        String resource = "mybatis-config.xml";
        // 读取配置文件
        InputStream is = Resources.getResourceAsStream(resource);
        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
        //this.userDao = new UserDaoImpl(sqlSession);
        //动态代理写法（不使用接口的实现类）
        this.userDao = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void queryUserById() {
        System.out.println(this.userDao.queryUserById("1"));
    }

    @Test
    public void queryUserAll() {
        List<User> userList = this.userDao.queryUserAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId("4");
        user.setUserName("lisi");
        user.setPassword("123456");
        user.setAge(16);
        user.setBirthday(new Date("1990/09/02"));
        this.userDao.insertUser(user);
        this.sqlSession.commit();
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId("4");
        user.setUserName("lisi");
        user.setPassword("654321");
        user.setAge(23);
        user.setBirthday(new Date("1990/09/02"));
        this.userDao.updateUser(user);
        this.sqlSession.commit();
    }

    @Test
    public void deleteUser() {
        this.userDao.deleteUser("4");
        this.sqlSession.commit();
    }
}