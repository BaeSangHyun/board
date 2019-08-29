package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

public interface IPostService {
    public List<Map> getAllPost(Map data);

    public int getCntPost(int boardId);

    public Map getPost(int postId);

    public List<Map> getPostCom(int postId);

    public List<Map> getPostFile(int postId);

    public int regCom(Map map);
}
