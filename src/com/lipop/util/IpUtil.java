package com.lipop.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	 public static String getIp(HttpServletRequest request) {
         String ip = request.getHeader("X-Forwarded-For");
         if(StringUtil.IsNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
             //��η���������ж��ipֵ����һ��ip������ʵip
             int index = ip.indexOf(",");
             if(index != -1){
                 return ip.substring(0,index);
             }else{
                 return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtil.IsNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
