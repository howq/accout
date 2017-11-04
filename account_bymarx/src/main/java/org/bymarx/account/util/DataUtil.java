package org.bymarx.account.util;

import org.springframework.util.StringUtils;


public class DataUtil {

    public static String convert2UTF8(String data) throws Exception {
    	if(StringUtils.isEmpty(data)) {
            return "";
        }
        return new String(data.getBytes("ISO-8859-1"), "UTF-8");
    }
}
