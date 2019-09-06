package kr.or.ddit.post.dao;

import kr.or.ddit.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PostDaoTest {
    private SqlSession session;
    private IPostDao postDao;
    private static final Logger logger = LoggerFactory.getLogger(PostDaoTest.class);

    @Before
    public void setUp() throws Exception {
        session = MybatisUtil.getSession();
        postDao = PostDao.getInstance();
    }

    @Test
    public void getAllPost() {
        /***Given***/
        HashMap map = new HashMap();
        map.put("boardId", 1);
        map.put("pageSize", 10);
        map.put("page", 1);

        /***When***/
        List<Map> allPost = postDao.getAllPost(session, map);

        /***Then***/
        assertEquals(10, allPost.size());
    }

    @Test
    public void getCntPost() {
        /***Given***/
        int boardId = 1;

        /***When***/
        int cnt = postDao.getCntPost(session, boardId);

        /***Then***/
        assertEquals(27, cnt);
    }

    @Test
    public void getPost() {
        /***Given***/
        int postId = 1;

        /***When***/
        Map post = postDao.getPost(session, postId);

        /***Then***/
        logger.debug("data : {}, {}",post.get("POSTID"), post.get("TITLE"));
    }

    @Test
    public void getPostCom() {
        /***Given***/
        int postId = 1;

        /***When***/
        List<Map> postCom = postDao.getPostCom(session, postId);

        /***Then***/
        assertEquals(8, postCom.size());
    }

    @Test
    public void getPostFile() {
        /***Given***/
        int postId = 1;

        /***When***/
        List<Map> postFile = postDao.getPostFile(session, postId);

        /***Then***/
        assertEquals(3, postFile.size());

    }

    @Test
    public void regCom() {
        /***Given***/
        HashMap data = new HashMap();
        data.put("postId", 40);
        data.put("userId", "bshn123");
        data.put("cont", "테스트댓글입니다");

        /***When***/
        int i = postDao.regCom(session, data);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void createForm() {
        /***Given***/
        Map map = new HashMap();
        map.put("boardId", 2);
        map.put("title", "Testtitle");
        map.put("userId", "bshn123" );
        map.put("cont", "testCont");

        /***When***/
        int form = postDao.createForm(session, map);

        /***Then***/
        assertEquals(1, form);
    }

    @Test
    public void setFile() {
        /***Given***/
        Map map = new HashMap();
        map.put("postId", 46);
        map.put("fileName", "ryan.png");
        map.put("path", "d:\\dev\\upload\\2019\\08\\9427ed80-3894-480a-939e-defww325e9ff78.png");

        /***When***/
        int i = postDao.setFile(session, map);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void delPost() {
        /***Given***/
        Map map = new HashMap();
        map.put("postId", 40);
        map.put("userId", "bshn123");

        /***When***/
        int i = postDao.delPost(session, map);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void delFile() {
        /***Given***/
        int fileId = 50;

        /***When***/
        int i = postDao.delFile(session, fileId);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void updatePost() {
        /***Given***/
        Map map = new HashMap();
        map.put("title", "다오제목테스트");
        map.put("cont", "다오내용테스트");
        map.put("postId", 40);

        /***When***/
        int i = postDao.updatePost(session, map);

        /***Then***/
        assertEquals(1, i);
    }

    @Test
    public void getFilePath() {
        /***Given***/
        int fileId = 1;

        /***When***/
        String filePath = postDao.getFilePath(session, fileId);

        /***Then***/
        logger.debug("filePath : {}", filePath);
        assertEquals("d:\\dev\\upload\\2019\\08\\2b38b216-c1b3-424b-93db-186cdb62810c.png", filePath);
    }

    @Test
    public void delCom() {
        /***Given***/
        int comId = 8;
                
        /***When***/
        int i = postDao.delCom(session, comId);
        
        /***Then***/
        assertEquals(1, i);
    }
}