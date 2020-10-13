<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta http-equiv="content-language" content="zh-cn" />
	    <title>登录</title>
	</head>
	<body>
		<div class="mainCon">
			<form:form method="post" commandName="${commandName}" htmlEscape="true" onsubmit="return ksd._login();">
				<div id="msgContainer" class="login-tip"><form:errors path="*" id="msg" cssClass="" element="div" /></div>
				<div class="login-user">
					<span class="login-text">用户名</span>
					<form:input id="username" path="username" htmlEscape="true" autocomplete="off" tabindex="1" value="${mail}" maxlength="40" />
				</div>
				<div class="login-password">
					<span class="login-text">密码</span>
					<form:password id="password" path="password" htmlEscape="true" autocomplete="off" tabindex="2" maxlength="20" />
				</div>
				<input type="hidden" name="lt" value="${loginTicket}" />
				<input type="hidden" name="execution" value="${flowExecutionKey}" />
				<input type="hidden" name="_eventId" value="submit" />
				<input class="btn-login" type="submit" value="登录" />
			</form:form>
					
		</div>
	
	</body>
</html>