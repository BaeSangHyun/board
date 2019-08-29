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
import java.util.List;
import java.util.Map;

@WebServlet("/post")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = -3223564643921151418L;

    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {
            String boardId = request.getParameter("boardId");

            if(boardId == null || boardId.equals("")) boardId = (String) session.getAttribute("boardId");

            session.setAttribute("boardId", boardId);

            String pageStr = request.getParameter("page");
            String pageSizeStr = request.getParameter("pageSize");

            int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
            int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);

            // 총 개시글 개수
            int totalCnt = postService.getCntPost(Integer.parseInt(boardId));

            request.setAttribute("pageNum", page);
            request.setAttribute("paginationSize", (int)Math.ceil((double) totalCnt / pageSize));

            Map data = new HashMap();
            data.put("boardId", boardId);
            data.put("page", page);
            data.put("pageSize", pageSize);

            List<Map> allPost = postService.getAllPost(data);

            request.setAttribute("allPost", allPost);
            request.getRequestDispatcher("/jsp/post/postList.jsp").forward(request, response);
        }
    }
}
