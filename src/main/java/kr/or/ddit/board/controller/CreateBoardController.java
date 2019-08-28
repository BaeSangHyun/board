package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/createBoard")
public class CreateBoardController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CreateBoardController.class);

    private IBoardService service;

    @Override
    public void init() throws ServletException {
        service = BoardService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardNm = request.getParameter("boardNm");
        String useable = request.getParameter("useable");
        User user = (User) request.getSession().getAttribute("user");
        logger.debug("boardNm : {}, {}, {}", boardNm, useable, user.getUserid());

        Map map = new HashMap();
        map.put("boardNm", boardNm);
        map.put("useable", useable);
        map.put("userId", user.getUserid());

        service.insertBoard(map);

//        request.getRequestDispatcher("/jsp/board/boardManage.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/boardManage");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
