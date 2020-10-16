package com.cas.jdbc.rewrite;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.RootCasException;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


public class AuthenticationViaFormCaptchaAction extends
        AuthenticationViaFormAction {

    public final String validatorCode(final RequestContext context,
                                      final Credential credentials, final MessageContext messageContext)
            throws Exception {
        final HttpServletRequest request = WebUtils
                .getHttpServletRequest(context);
        HttpSession session = request.getSession();
        String authcode = (String) session
                .getAttribute("captchaResult");
        session.removeAttribute("captchaResult");

        UsernamePasswordCaptchaCredential upc = (UsernamePasswordCaptchaCredential) credentials;
        String submitAuthcode = upc.getAuthcode();

        //验证码超时
        Long startStr =   (Long) session
                .getAttribute("captchaResultTime") ;
        Long endStr = System.currentTimeMillis();
        long random =  endStr - startStr;

        long expire = 300000L;//验证码5分钟内有效
        //long expire = 10000L;

        if(random > expire){
            populateErrorsInstance(new rangeException(),
                    messageContext);
            return "error";
        }


        if (StringUtils.isEmpty(submitAuthcode)
                || StringUtils.isEmpty(authcode)) {
            populateErrorsInstance(new NullAuthcodeAuthenticationException(),
                    messageContext);
            return "error";
        }



        if (submitAuthcode.equals(authcode)) {
            return "success";
        }
        populateErrorsInstance(new BadAuthcodeAuthenticationException(),
                messageContext);
        return "error";
    }

    private void populateErrorsInstance(final RootCasException e,
                                        final MessageContext messageContext) {

        try {
            messageContext.addMessage(new MessageBuilder().error()
                    .code(e.getCode()).defaultText(e.getCode()).build());
        } catch (final Exception fe) {
            logger.error(fe.getMessage(), fe);
        }
    }
}