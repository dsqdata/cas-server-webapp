package com.cas.jdbc.rewrite;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class SendSmsPost extends HttpServlet{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");  // 设置request字符编码
        String searchText = request.getParameter("mobile"); // 获取传入的search字段的内容
        response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
        PrintWriter out = response.getWriter();

        //生成6位验证码
        final String result = String.valueOf(new Random().nextInt(899999) + 100000);

        Map resMap = new HashMap();

        try {
            SendMessageUtil.sendMessage(searchText,result,"");
            //JavaRestTemplate.sendMessage(searchText,result,"");
            //验证码信息
            request.getSession().setAttribute("captchaResult",result);
            request.getSession().setAttribute("captchaResultTime",System.currentTimeMillis());
            resMap.put("flag","success");
            resMap.put("message","发送短信成功");
        } catch (Exception e) {
            request.getSession().setAttribute("captchaResult","");
            request.getSession().setAttribute("captchaResultTime",System.currentTimeMillis());
            resMap.put("flag","fail");
            resMap.put("message","发送短信失败");
            log.error(e.getMessage());
        }

        String resJSON = JSONObject.toJSONString(resMap);     // 转换为json
        out.print(resJSON); // 输出
    }

}