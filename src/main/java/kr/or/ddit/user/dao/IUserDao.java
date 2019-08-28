package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.User;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

public interface IUserDao {
    public User getUser(SqlSession session, Map map);
}
