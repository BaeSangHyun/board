package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDao implements IBoardDao {
    private static IBoardDao ourInstance;

    public static IBoardDao getInstance() {
        if(ourInstance == null) ourInstance = new BoardDao();
        return ourInstance;
    }

    private BoardDao() {
    }

    @Override
    public int insertBoard(SqlSession session, Map map) {
        int cnt = session.insert("board.createBoard", map);
        session.commit();
        return cnt;
    }

    @Override
    public List<Map> allBoard(SqlSession session) {
        return session.selectList("board.allBoard");
    }
}
