package kr.or.ddit.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadFile {
    public static List<String> uploadFile(String UPLOAD_DIR, HttpServletRequest request) {
        List<String> fileNames = new ArrayList<>();
        try {
            List<Part> parts = (List<Part>) request.getParts();
            for(Part part : parts) {
                if(part.getName().equals("files")) {
                    String fileName = getFileName(part);
                    fileNames.add(fileName);
                    String applicationPath = request.getServletContext().getRealPath("");
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    // 파일명 얻기
    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 1).trim().replace("\"","");
            }
        }
        return "";
    }

    // 파일 저장 폴더
    public static String getPath() {
        /**
         * Method : getPath
         * 작성자 : PC-20
         * 변경이력 :
         * @param []
         * @return java.lang.String
         * Method 설명 : 파일을 업로드할 경로를 조회한다
         */
        String basicPath = "d:\\dev\\upload";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String yyyyMM = sdf.format(new Date());
        String yyyy = yyyyMM.substring(0,4);
        String mm = yyyyMM.substring(4, 6);

        File yyyyDirectory = new File(basicPath + "\\" + yyyy + "\\" + mm);
        if(!yyyyDirectory.exists()) { yyyyDirectory.mkdirs(); }

        return basicPath + "\\" + yyyy + "\\" + mm + "\\";
    }

}
