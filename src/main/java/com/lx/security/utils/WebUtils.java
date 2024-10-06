package com.lx.security.utils;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param data   待渲染的数据
     * @return null
     */
    public static String render(HttpServletResponse response, String data) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String render(HttpServletResponse response,Integer code) {
        try {
            response.setStatus(code);
            response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}