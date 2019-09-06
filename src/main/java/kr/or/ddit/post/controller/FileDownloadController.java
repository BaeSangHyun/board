package kr.or.ddit.post.controller;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download")
public class FileDownloadController extends HttpServlet {
    private IPostService postService;

    @Override
    public void init() throws ServletException {
        postService = PostService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileId = request.getParameter("fileId");
        if (fileId != null) {
            String filePath = postService.getFilePath(Integer.parseInt(fileId));
            File imgFile = new File(filePath);

            String mimeType = getServletContext().getMimeType(imgFile.toString());
            if(mimeType == null)
            {
                response.setContentType("application/octet-stream");
            }

            FileInputStream fileInputStream = new FileInputStream(imgFile);
            ServletOutputStream servletOutputStream = response.getOutputStream();

            byte b [] = new byte[1024];
            int data = 0;

            while((data=(fileInputStream.read(b, 0, b.length))) != -1)
            {
                servletOutputStream.write(b, 0, data);
            }

            servletOutputStream.flush();
            servletOutputStream.close();
            fileInputStream.close();
        }
    }

}
