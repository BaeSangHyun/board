package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

public interface IBoardService {
    public int insertBoard(Map map);

    public List<Map> allBoard();

    public int updateBoard(Map map);
}
