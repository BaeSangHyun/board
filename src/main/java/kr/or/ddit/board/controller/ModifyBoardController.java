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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/modifyBoard")
public class ModifyBoardController extends HttpServlet {
    private static final long serialVersionUID = 2696623177902732860L;
    private static final Logger logger = LoggerFactory.getLogger(ModifyBoardController.class);

    private IBoardService boardService;

    @Override
    public void init() throws ServletException {
        boardService = BoardService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User chkUser = (User) session.getAttribute("user");

        if(chkUser == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {

            String useable = request.getParameter("useable");
            String boardId = request.getParameter("boardId");

            logger.debug("value : {}, {}", useable, boardId);

            Map map = new HashMap();
            map.put("useable", useable);
            map.put("boardId", boardId);

            int cnt = boardService.updateBoard(map);

            response.sendRedirect(request.getContextPath() + "/boardManage");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
