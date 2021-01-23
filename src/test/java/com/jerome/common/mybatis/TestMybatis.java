package com.jerome.common.mybatis;

import com.jerome.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMybatis {

    @Test
    public void selectUsersTest() {

        SqlSession sqlSession = MyBatisConn.getSqlSessionFactory().openSession(true);
        List<User> users = null;
        try {
            users = sqlSession.selectList("UserMapper.selectUsers");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            sqlSession.close();
        }

        users.forEach(value -> System.out.println(value));
    }


}
