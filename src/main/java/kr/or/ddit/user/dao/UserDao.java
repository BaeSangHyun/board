package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public class UserDao implements IUserDao {
    private static IUserDao dao;

    private UserDao() {}

    public static IUserDao getInstance() {
        if (dao == null) dao = new UserDao();
        return dao;
    }

    @Override
    public User getUser(SqlSession session, Map map) {
        return session.selectOne("user.getUser", map);
    }
}
