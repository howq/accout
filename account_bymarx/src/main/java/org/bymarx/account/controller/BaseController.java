package org.bymarx.account.controller;

import org.bymarx.account.service.MessageHelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by howq on 2017/3/9.
 */
@Controller
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "messageHelpService")
    protected MessageHelpService messageHelper;

    /**
     * Message表示
     *
     * @param msgNo Message编号
     * @param arr   替换字符串
     * @return      Message表示信息
     */
    public String messageShow(String msgNo, String... arr) {
        return messageHelper.getMessage(msgNo, arr);
    }

    /**
     *
     * @param msgNo Message编号
     * @return      Message表示信息
     */
    public String messageShow(String msgNo) {
        return messageShow(msgNo, new String[] {});
    }

}
