package kr.or.ddit.post.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.*;

public class PostServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceTest.class);
    private IPostService postService;

    @Before
    public void setup() {
        postService = PostService.getInstance();
    }

    @Test
    public void getAllPost() {
    }

    @Test
    public void getCntPost() {
        /***Given***/
        int boardId = 1;

        /***When***/
        int cnt = postService.getCntPost(boardId);

        /***Then***/
        assertEquals(5, cnt);
    }

    @Test
    public void getPost() {
        /***Given***/
        int postId = 3;

        /***When***/
        Map data = postService.getPost(postId);

        /***Then***/
        logger.debug("data : {}, {}",data.get("POSTID"), data.get("TITLE"));
    }
}