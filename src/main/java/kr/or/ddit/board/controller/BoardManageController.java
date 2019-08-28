package kr.or.ddit.board.controller;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/boardManage")
public class BoardManageController extends HttpServlet {
    private IBoardService service;

    @Override
    public void init() throws ServletException {
        service = BoardService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {
            List<Map> boarList = service.allBoard();
            request.setAttribute("boardList", boarList);
            request.getRequestDispatcher("/jsp/board/boardManage.jsp").forward(request, response);
        }
    }
}
