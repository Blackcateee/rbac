package com.zmxstudy.rbac.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServletResponse 统一返回结果工具类
 *
 * @author star
 */
public class ResponseUtil {
    /**
     * 分会JSON格式的字符串
     */
    public static void writeJson(HttpServletResponse response, String jsonContent) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(jsonContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
