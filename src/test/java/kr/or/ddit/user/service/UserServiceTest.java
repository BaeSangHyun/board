package kr.or.ddit.user.service;

import kr.or.ddit.user.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {

    private IUserService userService;

    @Before
    public void setup() {
        userService = UserService.getInstance();
    }

    @Test
    public void getUser() {
        /***Given***/
        Map map = new HashMap();
        map.put("userId", "bshn123");
        map.put("pass", "123123");
                
        /***When***/
        User user = userService.getUser(map);

        /***Then***/
        System.out.println(user.getUserid());
        System.out.println(user.getPass());
        
    }
}