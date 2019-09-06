package kr.or.ddit.board.service;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BoardServiceTest {
    private IBoardService boardService;

    @Before
    public void setUp() throws Exception {
        boardService = BoardService.getInstance();
    }

    @Test
    public void insertBoard() {
        /***Given***/
        Map map = new HashMap();
        map.put("boardNm", "서비스테스트");
        map.put("userId", "bshn123");
        map.put("useable", "T");

        /***When***/
        int i = boardService.insertBoard(map);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void allBoard() {
        /***Given***/


        /***When***/
        List<Map> maps = boardService.allBoard();

        /***Then***/
        assertEquals(12, maps.size());
    }

    @Test
    public void updateBoard() {
        /***Given***/
        Map map = new HashMap();
        map.put("useable", "F");
        map.put("boardId", 13);

        /***When***/
        int cnt = boardService.updateBoard(map);

        /***Then***/
        assertEquals(1, cnt);
    }
}