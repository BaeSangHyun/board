package kr.or.ddit.post.controller;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delCom")
public class DeleteComController extends HttpServlet {
    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String comId = request.getParameter("comId");

        if(comId == null) return;
        else {
            int delCom = postService.delCom(Integer.parseInt(comId));
            if(delCom == 1) {
                JSONObject jObj = new JSONObject();
                jObj.put("res", "삭제 성공");
                response.getWriter().print(jObj);
            }
        }
    }

}
