<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License.  You may obtain a
    copy of the License at the following location:

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
	<jsp:directive.include file="includes/top.jsp" />
	  <div id="msg" class="success">
	    <h2><spring:message code="screen.logout.header" /></h2>
	    <p><spring:message code="screen.logout.success" /></p>
	    <p><spring:message code="screen.logout.security" /></p>
	  </div>
	<jsp:directive.include file="includes/bottom.jsp" />

--%>

<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
	<head>
	  <meta charset="UTF-8" />
	  <title>logout</title>
	</head>
	<body>
	      
		<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%-- <fmt:bundle basename="system"><fmt:message key="approot" var="approot" /></fmt:bundle> --%>
		
		<script type="text/javascript">
			window.location.href ='http://www.rapidppt.net';
		</script>
	</body>
</html>