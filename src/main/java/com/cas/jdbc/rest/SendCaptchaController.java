/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.cas.jdbc.rest;

import java.io.PrintWriter;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.wf.captcha.ArithmeticCaptcha;
import org.jasig.cas.ticket.ServiceTicket;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.registry.TicketRegistry;
import org.perf4j.log4j.GraphingStatisticsAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class SendCaptchaController extends AbstractController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    protected ModelAndView handleRequestInternal(final HttpServletRequest request, final HttpServletResponse response)
                throws Exception {
        log.info("SendCaptchaController - 开始执行");
        try {
            request.setCharacterEncoding("utf-8");  // 设置request字符编码
            //String searchText = request.getParameter("search"); // 获取传入的search字段的内容
            response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
            PrintWriter out = response.getWriter();
            Map resMap = getCode();//返回的验证码
            String resJSON = JSONObject.toJSONString(resMap);     // 转换为json
            request.getSession().setAttribute("captchaResult",resMap.get("result"));
            request.getSession().setAttribute("captchaResultTime",System.currentTimeMillis());
            out.print(resJSON); // 输出
        } catch (Exception e ) {
            log.error(e.getMessage());
            throw e;
        }

        return null;
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
