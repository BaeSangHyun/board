package kr.or.ddit.board.dao;

import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BoardDaoTest {
    private IBoardDao boardDao;
    private SqlSession session;
    private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);

    @Before
    public void setup() {
        boardDao = BoardDao.getInstance();
        session = MybatisUtil.getSession();
    }

    @Test
    public void insertBoard() {
    }

    @Test
    public void allBoard() {
    }

    @Test
    public void updateBoard() {
        /***Given***/
        Map map = new HashMap();
        map.put("able", "F");
        map.put("boardId", 5);

        /***When***/
        int cnt = boardDao.updateBoard(session, map);

        /***Then***/
        assertEquals(1, cnt);

    }
}