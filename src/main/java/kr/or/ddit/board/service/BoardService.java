package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardService implements IBoardService {
    private static IBoardService ourInstance;
    private IBoardDao dao;

    public static IBoardService getInstance() {
        if(ourInstance == null) ourInstance = new BoardService();
        return ourInstance;
    }

    private BoardService() {
        dao = BoardDao.getInstance();
    }

    @Override
    public int insertBoard(Map map) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.insertBoard(session, map);
        session.close();
        return cnt;
    }

    @Override
    public List<Map> allBoard() {
        SqlSession session = MybatisUtil.getSession();
        List<Map> boardList = dao.allBoard(session);
        session.close();
        return boardList;
    }
}
