package kr.or.ddit.post.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public interface IPostDao {
    // 모든 게시글 가져오기
    public List<Map> getAllPost(SqlSession session, Map data);

    // 게시글 숫자
    public int getCntPost(SqlSession session, int boardId);

    public Map getPost(SqlSession session, int postId);

    public List<Map> getPostCom(SqlSession session, int postId);

    public List<Map> getPostFile(SqlSession session, int postId);

    public int regCom(SqlSession session, Map data);

    public int createForm(SqlSession session, Map data);

    public int setFile(SqlSession session, Map fileMap);

    public int delPost(SqlSession session, int postId);

    public int delFile(SqlSession session, int fileId);

    public int updatePost(SqlSession session, Map data);
}
