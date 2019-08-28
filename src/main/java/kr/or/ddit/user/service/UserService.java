package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class UserService implements IUserService {
    private static IUserService instance;
    private IUserDao dao;

    public static IUserService getInstance() {
        if(instance == null) instance = new UserService();
        return instance;
    }
    private UserService() {
        dao = UserDao.getInstance();
    }

    @Override
    public User getUser(Map map) {
        SqlSession session = MybatisUtil.getSession();
        User user = dao.getUser(session, map);
        session.close();
        return user;
    }
}
