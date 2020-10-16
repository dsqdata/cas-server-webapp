package com.cas.jdbc.rewrite;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMessage extends HttpServlet{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

    @Autowired
    private  JdbcTemplate jdbcTemplate =  new JdbcTemplate( (DataSource) wac.getBean("dataSource"));

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

        request.setCharacterEncoding("utf-8");  // 设置request字符编码
        String searchText = request.getParameter("mobile"); // 获取传入的search字段的内容
        response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
        PrintWriter out = response.getWriter();
        Map resMap =  findPhone(searchText);//返回的验证码
        request.getSession().setAttribute("mess_username",resMap.get("username"));
        request.getSession().setAttribute("mess_password",resMap.get("password"));
        String resJSON = JSONObject.toJSONString(resMap);     // 转换为json
        out.print(resJSON); // 输出
    }
    public Map findPhone(String telephone) {
        String phone = telephone;
        String nick_name = telephone;
        String username = telephone;

        String sql = "select count(*) as num ,username,password from sys_user where  phone  = ? or nick_name = ?  or username  = ?  and enabled = 1";
        Map m = new HashMap();
        try {
           m =  jdbcTemplate.queryForMap(sql,phone,nick_name,username) ;

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return m;
    }
}