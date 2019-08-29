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
import java.util.List;
import java.util.Map;

@WebServlet("/postDetail")
public class PostDetailController extends HttpServlet {
    private static final long serialVersionUID = -7775799044026429698L;

    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {
            String postId = request.getParameter("postId");

            if(postId == null) return;

            Map map = postService.getPost(Integer.parseInt(postId));
            request.setAttribute("post", map);
            List<Map> postCom = postService.getPostCom(Integer.parseInt(postId));
            request.setAttribute("postCom", postCom);
            List<Map> postFile = postService.getPostFile(Integer.parseInt(postId));
            request.setAttribute("postFile", postFile);

            request.getRequestDispatcher("/jsp/post/postDetail.jsp").forward(request, response);
        }

    }
}
