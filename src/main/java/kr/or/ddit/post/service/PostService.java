package kr.or.ddit.post.service;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDao;
import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PostService implements IPostService {
    private static IPostService ourInstance;
    private IPostDao dao;

    public static IPostService getInstance() {
        if(ourInstance == null) ourInstance = new PostService();
        return ourInstance;
    }

    private PostService() {
        dao = PostDao.getInstance();
    }

    @Override
    public List<Map> getAllPost(Map data) {
        SqlSession session = MybatisUtil.getSession();
        List<Map> map = dao.getAllPost(session, data);
        session.close();
        return map;
    }

    @Override
    public int getCntPost(int boardId) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.getCntPost(session, boardId);
        session.close();
        return cnt;
    }

    @Override
    public Map getPost(int postId) {
        SqlSession session = MybatisUtil.getSession();
        Map data = dao.getPost(session, postId);
        session.close();
        return data;
    }

    @Override
    public List<Map> getPostCom(int postId) {
        SqlSession session = MybatisUtil.getSession();
        List<Map> postCom = dao.getPostCom(session, postId);
        session.close();
        return postCom;
    }

    @Override
    public List<Map> getPostFile(int postId) {
        SqlSession session = MybatisUtil.getSession();
        List<Map> postFile = dao.getPostFile(session, postId);
        session.close();
        return postFile;
    }

    @Override
    public int regCom(Map map) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.regCom(session, map);
        session.close();
        return cnt;
    }

    @Override
    public int createForm(Map data) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.createForm(session, data);
        session.close();
        return cnt;
    }

    @Override
    public int setFile(Map fileMap) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.setFile(session, fileMap);
        session.close();
        return cnt;
    }

    @Override
    public int delPost(int postId) {
        SqlSession session = MybatisUtil.getSession();
        int cnt = dao.delPost(session, postId);
        session.close();
        return cnt;
    }

    @Override
    public int delFile(int fileId) {
        SqlSession session = MybatisUtil.getSession();
        int delCnt = dao.delFile(session, fileId);
        return delCnt;
    }

    @Override
    public int updatePost(Map data) {
        SqlSession session = MybatisUtil.getSession();
        int upCnt = dao.updatePost(session, data);
        return upCnt;
    }
}
