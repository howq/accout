package org.bymarx.account.controller.wordpress;

import org.bymarx.account.controller.WebExceptionHandler;
import org.bymarx.account.dto.wordpress.UserInfo;
import org.bymarx.account.service.UserService;
import org.bymarx.account.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author howq
 * @create 2017-07-13 11:33
 **/
@RequestMapping("/wp")
@RestController
public class WordPressController extends WebExceptionHandler {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Object> add(UserInfo userInfo,
                              @RequestParam(value = "domain", required = true) byte domain) {
        logger.info("=========新增用户==============");
        Result<Object> result = new Result<Object>();
        if (!userInfo.getPass1().equals(userInfo.getPass2())) {
            logger.error("=========新增用户失败:密码不对应==============");
            result.setCode(Result.Code.ERROR);
            result.setMessage("新增用户失败:密码不对应");
            return result;
        }
        try {
            userService.addUser(userInfo, domain);
//            topicService.delTopic(topicId, request);

        } catch (Exception e) {
            logger.error("=========新增用户失败:" + e.getMessage() + "==============");
            result.setCode(Result.Code.ERROR);
            return result;
        }

        logger.info("=========新增用户成功==============");
        result.setCode(Result.Code.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Result<Object> del(@RequestParam(value = "ids", required = true) String ids, @RequestParam(value = "domain", required = true) byte domain) {
        logger.info("=========删除用户==============");
        Result<Object> result = new Result<Object>();
        try {
//            topicService.delTopic(topicId, request);
        } catch (Exception e) {
            logger.error("=========删除用户失败:" + e.getMessage() + "==============");
            result.setCode(Result.Code.ERROR);
            return result;
        }
        logger.info("=========删除用户成功==============");
        result.setCode(Result.Code.SUCCESS);
        return result;
    }
}
