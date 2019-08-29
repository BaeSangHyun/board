package kr.or.ddit.post.controller;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/regCom")
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = -1078202285801775407L;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String userId = request.getParameter("userId");
        int postId = Integer.parseInt(request.getParameter("postId"));
        String cont = request.getParameter("cont");

        Map data = new HashMap();
        data.put("userId", userId);
        data.put("postId", postId);
        data.put("cont", cont);

        int i = postService.regCom(data);
        if(i == 0) return;

//        logger.debug("data : {}, {}, {}", userId, postId, cont);
        List<Map> postCom = postService.getPostCom(postId);

        JSONArray jArr = new JSONArray();

        JSONObject jObj;
        for(Map map : postCom) {
            jObj = new JSONObject();
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String val = String.valueOf(map.get(key));
                jObj.put(key, val);
            }
            jArr.add(jObj);
        }
        response.getWriter().print(jArr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
