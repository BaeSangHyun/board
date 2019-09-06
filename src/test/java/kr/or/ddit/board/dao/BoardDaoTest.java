package kr.or.ddit.board.dao;

import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
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
        /***Given***/
        Map map = new HashMap();
        map.put("boardNm", "다오테스트");
        map.put("userId", "bshn123");
        map.put("useable", "T");

        /***When***/
        int i = boardDao.insertBoard(session, map);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void allBoard() {
        /***Given***/


        /***When***/
        List<Map> maps = boardDao.allBoard(session);

        /***Then***/
        assertEquals(9, maps.size());

    }

    @Test
    public void updateBoard() {
        /***Given***/
        Map map = new HashMap();
        map.put("useable", "F");
        map.put("boardId", 5);

        /***When***/
        int cnt = boardDao.updateBoard(session, map);

        /***Then***/
        assertEquals(1, cnt);

    }
}