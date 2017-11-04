package org.bymarx.account.controller.accout;

import org.bymarx.account.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author howq
 * @create 2017-07-11 下午2:53
 **/
@RestController
public class HelloController {

    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "你好";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@RequestParam(value = "username",required = true) String username,
    @RequestParam(value = "pwd",required = true) String pwd){
        if(userService.isLogin(username, pwd)) {
            return "1";
        }
        return "0";
    }
}