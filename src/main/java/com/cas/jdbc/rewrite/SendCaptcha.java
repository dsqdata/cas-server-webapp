package com.cas.jdbc.rewrite;

import com.alibaba.fastjson.JSONObject;
import com.wf.captcha.ArithmeticCaptcha;
import org.jasig.cas.web.support.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SendCaptcha extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

        request.setCharacterEncoding("utf-8");  // 设置request字符编码
        String searchText = request.getParameter("search"); // 获取传入的search字段的内容
        response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
        PrintWriter out = response.getWriter();
        Map resMap = getCode();//返回的验证码
        String resJSON = JSONObject.toJSONString(resMap);     // 转换为json
        request.getSession().setAttribute("captchaResult",resMap.get("result"));

        out.print(resJSON); // 输出
    }
    public Map getCode() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        final ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        final String result = captcha.text();
        //final String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        // 保存
        // ratelCacheProvider.set(uuid, result, expiration * 60 * 1000L, TimeUnit.MILLISECONDS);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("result", result);
        }};
        return imgResult;
    }
}