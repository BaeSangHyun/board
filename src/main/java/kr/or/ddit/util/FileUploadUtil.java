package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUploadUtil {

    public static String getFileName(String contentDisposition) {
        /**
         * Method : getFileName
         * 작성자 : PC-20
         * 변경이력 :
         * @param [contentDisposition]
         * @return java.lang.String
         * Method 설명 : Content-disposition 헤더 문자열로부터 파일이름 추출
         */
        // 메소드 인자 : "form-data; name=\"file\"; filename=\"brown.png\""
        String[] attrs = contentDisposition.split(";");
        for(String attr : attrs) {
            if(attr.trim().startsWith("filename")) {
                return attr.substring(attr.indexOf("\"")+1, attr.lastIndexOf("\"")).trim();
            }
        }

        return "";
    }

    public static String getFileExtension(String contentDisposition) {
        String fileName = getFileName(contentDisposition);
        if(fileName.trim().contains(".")) {
//            return attr.substring(fileName.lastIndexOf("."), fileName.length()).trim();
            return fileName.substring(fileName.lastIndexOf(".")).trim();
        }

        return "";
    }

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
