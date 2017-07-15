package org.bymarx.account.service;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 公用信息提示
 *
 * @author howq
 * @create 2017/3/11 14:17
 **/
@Service(value = "messageHelpService")
public class MessageHelpService implements MessageSource {

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    public String getMessage(String code) {
        return getMessage(code, new Object[]{});
    }

    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return getMessage(code, args, "", Locale.CHINA);
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String msg = messageSource.getMessage(code, args, defaultMessage, locale);
        return msg != null ? msg.trim() : "";
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale)
            throws NoSuchMessageException {
        return getMessage(code, args, "", locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale)
            throws NoSuchMessageException {
        String msg = messageSource.getMessage(resolvable, locale);
        return msg != null ? msg.trim() : "";
    }
}
