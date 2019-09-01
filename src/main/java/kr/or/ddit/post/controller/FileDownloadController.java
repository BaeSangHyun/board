package kr.or.ddit.post.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/download")
public class FileDownloadController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/octet-stream");

        String filename = request.getParameter("filename");
        String realPath = request.getParameter("path");

        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        StringBuffer sb = new StringBuffer(realPath);
        InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
        ServletOutputStream out = response.getOutputStream();

        byte[] outputByte = new byte[4096];
//copy binary contect to output stream
        while(in.read(outputByte, 0, 4096) != -1)
        {
            out.write(outputByte, 0, 4096);
        }
        in.close();
        out.flush();
        out.close();

    }
}
