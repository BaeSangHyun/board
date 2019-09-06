package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.User;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserDaoTest {
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void setup() {
        session = MybatisUtil.getSession();
        userDao = UserDao.getInstance();
    }

    @Test
    public void getUser() {
        /***Given***/
        Map map = new HashMap();
        map.put("userId", "bshn123");
        map.put("pass", "123123");

        /***When***/
        User user = userDao.getUser(session, map);

        /***Then***/
        System.out.println(user.getUserid());
        System.out.println(user.getPass());

    }
}