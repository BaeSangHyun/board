package kr.or.ddit.post.controller;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/delPost")
public class DeletePostController extends HttpServlet {
    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User chkUser = (User) session.getAttribute("user");

        if(chkUser == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {
            String boardId = request.getParameter("boardId");
            int postId = Integer.parseInt(request.getParameter("postId"));

            Map data = new HashMap();
            data.put("postId", postId);
            data.put("userId", chkUser.getUserid());

            int cnt = postService.delPost(data);
            response.sendRedirect(request.getContextPath() + "/post?boardId=" + boardId);
        }
    }
}
