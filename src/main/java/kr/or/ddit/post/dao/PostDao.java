package kr.or.ddit.post.dao;

import kr.or.ddit.board.dao.IBoardDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PostDao implements IPostDao {
    private static IPostDao ourInstance;

    public static IPostDao getInstance() {
        if(ourInstance == null) ourInstance = new PostDao();
        return ourInstance;
    }

    private PostDao() {
    }


    @Override
    public List<Map> getAllPost(SqlSession session, Map data) {
        return session.selectList("post.getAllPost", data);
    }

    @Override
    public int getCntPost(SqlSession session, int boardId) {
        return session.selectOne("post.getCntPost", boardId);
    }

    @Override
    public Map getPost(SqlSession session, int postId) {
        return session.selectOne("post.getPost", postId);
    }

    @Override
    public List<Map> getPostCom(SqlSession session, int postId) {
        return session.selectList("post.getPostCom", postId);
    }

    @Override
    public List<Map> getPostFile(SqlSession session, int postId) {
        return session.selectList("post.getPostFile", postId);
    }

    @Override
    public int regCom(SqlSession session, Map data) {
        int cnt = session.insert("post.regCom", data);
        session.commit();
        return cnt;
    }

    @Override
    public int createForm(SqlSession session, Map data) {
        int cnt = session.insert("post.newPost", data);
        session.commit();
        return cnt;
    }

    @Override
    public int setFile(SqlSession session, Map fileMap) {
        int cnt = session.insert("post.newFile", fileMap);
        session.commit();
        return cnt;
    }

    @Override
    public int delPost(SqlSession session, int postId) {
//        int fileDel = session.delete("post.delPostFile", postId);
//        int comDel = session.delete("post.delPostCom", postId);
        int postDel = session.update("post.delPost", postId);
        session.commit();
        return postDel;
    }

    @Override
    public int delFile(SqlSession session, int fileId) {
        int delCnt = session.delete("post.delFile", fileId);
        session.commit();
        return delCnt;
    }

    @Override
    public int updatePost(SqlSession session, Map data) {
        int upCnt = session.update("post.updatePost", data);
        session.commit();
        return upCnt;
    }

}
