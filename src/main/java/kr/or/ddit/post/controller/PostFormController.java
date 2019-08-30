package kr.or.ddit.post.controller;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/postForm")
@MultipartConfig(maxFileSize = 1024*1024*5*5, maxRequestSize = 1024*1024*5*5*5)
public class PostFormController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PostFormController.class);
    private static final long serialVersionUID = 2146161324572190927L;

    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 수정 시
        String postId = request.getParameter("postId");
        String boardId = request.getParameter("boardId");
        String title = request.getParameter("title");
        String cont = request.getParameter("cont");
        // 부모아이디 (답글 시)
        String pa_postId = request.getParameter("pa_postId");

        Map data = new HashMap();
        data.put("userId", user.getUserid());
        data.put("boardId", chkNull(boardId));
        data.put("title", title);
        data.put("cont", cont);

        data.put("postId", chkNull(postId));
        data.put("pa_postId", chkNull(pa_postId));
        if(chkNull(pa_postId) != null) {
            data.put("postGn", postService.getPost(Integer.parseInt(pa_postId)).get("POSTGN"));
        }

       if(postId == null || postId.equals("")) {
//            data.put("postGn", data.get("postId"));
            postService.createForm(data);
        } else {
            postService.updatePost(data);
        }

        List<Part> files = (List<Part>) request.getParts();

        String fileName = "";
        String path = "";

        if (files.size() > 0) {
            for (Part part : files) {
                if (part.getName().equalsIgnoreCase("files")) {
                    fileName = FileUploadUtil.getFileName(part.getHeader("Content-Disposition"));
                    if(!fileName.equals("")) {
                        String realFileName = UUID.randomUUID().toString();
                        String ext = FileUploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
                        path = FileUploadUtil.getPath() + realFileName + ext;

                        part.write(path);

                        Map fileMap = new HashMap();
                        fileMap.put("fileName", fileName);
                        fileMap.put("path", path);
                        fileMap.put("postId", data.get("postId"));

                        postService.setFile(fileMap);
                    }
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/postDetail?boardId=" + data.get("boardId") + "&postId=" + data.get("postId"));
    }

    private String chkNull(String val) {
        if(val.equals("")) return null;
        else return val;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User chkUser = (User) session.getAttribute("user");

        if(chkUser == null) {
            request.getRequestDispatcher("/jsp/login/login.jsp").forward(request, response);
        } else {
            String postId = request.getParameter("postId");
            if (postId != null && !postId.equals("")) {
                Map post = postService.getPost(Integer.parseInt(postId));
                List<Map> postFile = postService.getPostFile(Integer.parseInt(postId));

                request.setAttribute("post", post);
                request.setAttribute("postFile", postFile);

            } else {
                String pa_postId = request.getParameter("parentId");

                request.setAttribute("pa_postId", pa_postId);
            }

            request.getRequestDispatcher("/jsp/post/postForm.jsp").forward(request, response);
        }
    }
}
