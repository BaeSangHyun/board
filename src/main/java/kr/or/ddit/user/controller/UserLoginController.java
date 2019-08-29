package kr.or.ddit.user.controller;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/login"}, loadOnStartup = 1)
public class UserLoginController extends HttpServlet {
    private static final long serialVersionUID = -1530874465394133616L;

    private IUserService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = UserService.getInstance();
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute("cp", servletContext.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String pass = request.getParameter("pass");
        String rememberMe = request.getParameter("rememberMe");

        Map map = new HashMap();
        map.put("userId", userId);
        map.put("pass", pass);

        User user = service.getUser(map);

        if(user == null) doGet(request, response);
        else {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            IBoardService boardService = BoardService.getInstance();
            List<Map> boarList = boardService.allBoard();
//            request.setAttribute("boardList", boarList);
            session.setAttribute("boardList", boarList);
            response.sendRedirect(request.getContextPath() + "/jsp/main.jsp");
//            request.getRequestDispatcher("/jsp/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
    }
}
