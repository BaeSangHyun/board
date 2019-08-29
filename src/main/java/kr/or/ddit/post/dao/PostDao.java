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
}
