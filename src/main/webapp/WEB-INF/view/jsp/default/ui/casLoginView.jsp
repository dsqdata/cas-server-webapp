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
    <script src="/js/jquery.js"></script>
    <title>登录</title>
    <style type="text/css">
        body{
            height: 100% ;
            margin: 0px!important;
            color: rgba(0, 0, 0, 0.85);
            font-size: 14px;
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';
            font-variant: tabular-nums;
            line-height: 1.5715;
            background-color: #fff;
            font-feature-settings: 'tnum';
        }
        .report-login {
            background-image: url("/images/report/1.png");
            width: 100vw;
            height: 100vh;
            position: relative;
            background-size: cover;
        }
        .report-login-inner {
            width: 80%;
            height: 80%;
            position: absolute;
            left: 10%;
            top: 10%;
            box-shadow: 20px 20px 80px #2f5fcb;
        }

        .report-login-up {
            width: 100%;
            height: 100%;
            background-image: url(/images/report/2.png);
            background-size: 100% 100%;
        }

        .sign-in-header {
            position: absolute;
            left: 5%;
            top: 5%;
            display: flex;
        }

        .report-login  h2 {
            font-size: 20px;
            color: #fff;
            display: inline-block;
        }

        .sign-in-left {
            position: absolute;
            left: 10%;
            top: 22%;
            width: 30%;
        }

        .wrap {
            height: 100%;
            position: relative;
            padding-bottom: 105%;
            background-image: url(/images/report/u1.png);
            background-repeat: no-repeat;
            background-size: 100%;
            margin: 0 auto;
        }

        .sign-in-right {
            width: 420px;
            position: absolute;
            left: 60%;
            top: 22%;
            margin: auto;
            background-color: rgba(248, 248, 248, 1);
            box-shadow: 10px 10px 30px rgb(141, 187, 220);
        }
        .sign-in-right  .title {
            font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
            font-weight: 700;
            font-size: 20px;
            color: rgb(24, 144, 255);
            text-align: center;
            background-image: -webkit-gradient(linear, 37.219838% 34.532506%, 36.425669% 93.178216%, from(#47a8ec), to(#0a60ff), color-stop(0.37, #148eff));
            margin-bottom: 20px;
            -webkit-text-fill-color: transparent;
            -webkit-background-clip: text;
        }

        .user-name {
            margin-top: 10px;
            height: 50px;
            line-height: 50px;
            text-align: center;
        }
        .label {
            display: inline-block;
            width: 60px;
            color: #333333;
            font-size: 14px;
        }

        .user-input {
            width: 100%;
        }

        .login-btn-border {
            height: 100px;
            line-height: 100px;
        }
        .login-btn {
            width: 100%;

        }

        .login-box {
            padding: 0 25px;
        }

        .login-form-forgot {
            float: right;
        }

        .ant-col-rtl .login-form-forgot {
            float: left;
        }

        .login-form-button {
            width: 100%;
        }

        .other-btn-0 {
            margin-left: 0px;
            background-color: #21242a;
            border-color: #21242a;
        }

        .other-btn-1 {
            margin-left: 20px;
        }

        .other-btn-2 {
            margin-left: 20px;
            background-color: #46d800;
            border-color: #46d800;
        }

        h1, h2, h3, h4, h5, h6 {
            margin-top: 0;
            margin-bottom: 0.5em;
            color: rgba(0, 0, 0, 0.85);
            font-weight: 500;
        }
        .login-input{
            margin-bottom: 25px;
        }
        .login-button{
            height: 40px;
            padding: 6.4px 15px;
            font-size: 16px;
            border-radius: 5px;
            color: #fff;
            background: #1890ff;
            border-color: #1890ff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
            box-shadow: 0 2px 0 rgba(0, 0, 0, 0.045);
            margin-bottom: 25px;
        }
        .login-error{
            padding: 6.4px 15px;
            font-size: 18px;
            margin-bottom: 25px;
        }
        input{
            width: 100%;
            height: 30px;
        }
        .btnRed{  background: #FE5D5A; color: #fff;border:none; text-shadow:none;  padding: 6px 19px; margin: 0 5px;}
        .btnRed:hover{ background:#fe7371;  color: #fff;}
        @media (min-width: 800px) {
            .report-login .sign-in-right {
                width: 300px;
                left: 55%;
                top: 16%;
            }

        }


        @media (min-width: 1024px) {
            .report-login .sign-in-right {
                width: 320px;
                left: 55%;
                top: 16%;
            }
        }

        @media (min-width: 1100px) {
            .report-login .sign-in-right {
                width: 320px;
                left: 55%;
                top: 10%;
            }
        }

        @media (min-width: 1280px) {
            .report-login .sign-in-right {
                width: 360px;
                left: 58%;
                top: 18%;
            }
        }

        @media (min-width: 1366px) {
            .report-login .sign-in-right {
                width: 360px;
                left: 59%;
                top: 18%;
            }
        }

        @media (min-width: 1440px) {
            .report-login .sign-in-right {
                width: 380px;
                left: 59%;
                top: 20%;
            }
        }

        @media (min-width: 1680px) {
            .report-login .sign-in-right {
                width: 380px;
                left: 62%;
                top: 20%;
            }
        }

        @media (min-width: 1920px) {
            .report-login .sign-in-right {
                width: 420px;
                left: 62%;
                top: 22%;
            }
        }

    </style>

</head>
<body>

<div class='report-login'>
    <div class='report-login-inner'>
        <div class='report-login-up'>
            <div class='sign-in-header'>
                <img style='width: 15%;height: 15%;' alt="logo" src="/images/report/u44.png"/>
                <h2 style='margin-left: 10px;'>
                    <span style='font-size: 25px;'>统一认证平台</span><br/>
                    <span style='font-size: 12px;'>中国(云南)自由贸易试验区昆明片区管理委员会</span>
                </h2>
            </div>

            <div class='sign-in-left'>
                <div class="wrap"></div>
            </div>

            <div  id="block" class='sign-in-right' >
                <div style="text-align: right;"  >
                    <img style="height: 50px;" onclick="loginChange()" alt="logo" src="/images/report/icon1.png"/>
                </div>
                <h1 class='title'>
                    账号登录
                </h1>

                <div class="login-box">
                   <form:form method="post" commandName="${commandName}" htmlEscape="true" onsubmit="return ksd._login();">

                        <div class="login-input">
                            <form:input placeholder="请输入账号" id="username" path="username" htmlEscape="true" autocomplete="off" tabindex="1" value="${mail}" maxlength="40" />
                        </div>
                        <div class="login-input">
                            <form:password placeholder="请输入密码" id="password" path="password" htmlEscape="true" autocomplete="off" tabindex="2" maxlength="20" />
                        </div>
                       <div class="login-input">
                           <div>
                               <form:input placeholder="请输入验证码" style='width:40%;' id="authcode" path="authcode" htmlEscape="true" autocomplete="off" tabindex="3"  maxlength="40" />
                               <img  id="imgCaptcha"  style='width:40%;float: right' onclick="changeCaptcha()"  />
                           </div>

                       </div>
                       <%--<section class="row fl-controls-left login-input">
                           <label for="authcode"><spring:message code="screen.welcome.label.authcode" /></label>
                           <spring:message code="screen.welcome.label.authcode.accesskey" var="authcodeAccessKey" />
                           <table>
                               <tr>
                                   <td>
                                       <form:input cssClass="required" placeholder="请输入验证码"  cssErrorClass="error" id="authcode" size="10" tabindex="2" path="authcode"
                                                   accesskey="${authcodeAccessKey}" htmlEscape="true" autocomplete="off" />
                                   </td>
                                   <td style="vertical-align: bottom">
                                       <img  id="imgCaptcha"  style='float: right' onclick="changeCaptcha()"  />
                                   </td>
                               </tr>
                           </table>
                       </section>--%>
                        <form:input type="hidden" name="authtype" path="authtype" value="passwd"/>
                        <input type="hidden" name="lt" value="${loginTicket}" />
                        <input type="hidden" name="execution" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submit" />
                        <input class="login-button" type="submit" value="登录" />
                        <div id="msgContainer" class="login-error">
                            <form:errors path="*" id="msg" cssClass="" element="div" />
                        </div>
                       <div  class="login-input" style="text-align: center">
                           <span>请点击右上角图标,切换其他方式登录</span>
                       </div>
                    </form:form>

                </div>
            </div>
                <div id="none" class='sign-in-right'>
                    <div style="text-align: right;"  >
                         <img style="height: 50px;"   alt="logo"  onclick="loginChange()"  src="/images/report/icon1.png"/>
                    </div>
                    <h1 class='title'>
                        动态登录
                    </h1>

                    <div class="login-box">
                        <form:form method="post" commandName="${commandName}" htmlEscape="true" onsubmit="return ksd._login();">

                            <div class="login-input">
                                <input id="telephone" placeholder="请输入手机号"  name="telephone" type="text" >
                            </div>
                            <div class="login-input">
                                <form:input type="text" path="authcode" id="code" placeholder="请输入验证码"  name="code"  style="width: 40%;"/>
                                <button id="but2" type="button" onclick="btnCancel2()" class="btn btnRed" style="width: 40%;height: 35px; float: right;" >发送验证码</button>

                            </div>
                            <form:input type="hidden" name="authtype" path="authtype" value="mess"/>
                            <form:input type="hidden" name="mess_username" path="username" id="mess_username"/>
                            <form:input type="hidden" name="mess_password" path="password" id="mess_password"/>

                            <input type="hidden" name="lt" value="${loginTicket}" />
                            <input type="hidden" name="execution" value="${flowExecutionKey}" />
                            <input type="hidden" name="_eventId" value="submit" />
                            <input class="login-button" type="submit" value="登录" />
                            <div id="msgC" class="login-error">

                            </div>
                            <div  class="login-input"  style="text-align: center">
                                <span>请点击右上角图标,切换其他方式登录</span>
                            </div>
                        </form:form>
                    </div>
                </div>
        </div>
    </div>
</div>
</body>

</html>
<script type="text/javascript">
    $(function(){
        console.log(window.location.href);
        if (window.top!=window.self) {
            //alert("<p>这个窗口不是最顶层窗口!我在一个框架?</p>")
            var x =  window.location.href;
            x = x.replace("blackboard","" );
            top.location.href=window.location.href;
        }
        else{
            //alert("<p>这个窗口是最顶层窗口!</p>")
        }
        $("#block").css("display","block");
        $("#none").css("display","none");
        $.ajax({
            url:"/SendCaptcha",
            dataType:"json",   //返回格式为json
            async:false,//请求是否异步，默认为异步，这也是ajax重要特性
            type:"GET",   //请求方式
            success:function(data){
                console.log(data.img);
                $("#imgCaptcha").attr("src",data.img);

            }
        });
    })

    function loginChange(){
        if($('#block').css("display")=="block"){

            $("#block").css("display","none");
            $("#none").css("display","block");

        }else{
            $("#block").css("display","block");
            $("#none").css("display","none");
        }
        $("#msgC").html("");
        $("#code").val("");
    }
    function changeCaptcha() {
        $.ajax({
            url:"/SendCaptcha",
            dataType:"json",   //返回格式为json
            async:false,//请求是否异步，默认为异步，这也是ajax重要特性
            type:"GET",   //请求方式
            success:function(data){
                console.log(data.img);
                $("#imgCaptcha").attr("src",data.img);

            }
        });
    }
    function btnCancel2(){
        var $mobile = $("input[id=telephone]");
        var data = {};
        data.mobile = $.trim($mobile.val());
        if(data.mobile == ''){
           // alert('请输入手机号码');
            $("#msgC").html("请输入手机号码");
            return;
        }
        var reg = /^1\d{10}$/;
        if(!reg.test(data.mobile)){
            //alert('请输入合法的手机号码');
            $("#msgC").html("请输入合法的手机号码");
            return ;
        }

        $.ajax({
            type: "get",
            async: false,
            url: "${pageContext.request.contextPath}/SendMessage?mobile="+$("#telephone").val(),
            async:false,
            success: function (obj) {

                if(obj.num<1){
                   // alert("该账号不存在！");
                    $("#msgC").html("该账号不存在！");
                }else{
                    $("#mess_username").val(obj.username)
                    $("#mess_password").val(obj.password)
                    //alert("发送短信");
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "${pageContext.request.contextPath}/SendSmsPost?mobile="+data.mobile,
                        dataType: "json",
                        success: function (data) {
                             console.log(data);

                            if(data.flag == "success"){
                                $("#msgC").html("");
                                time2 = 60;
                               //设置一个定时器
                               var timeout2 = setInterval(
                                   function(obj){
                                       if(time2==0){//重新获取验证码
                                           $("#but2").attr("disabled",false);
                                           $("#but2").empty().append("发送验证码");
                                           time2 = 60;
                                           clearInterval(timeout2);
                                           return false;//清除定时器
                                       }else{
                                           $("#but2").attr("disabled",true);
                                           time2--;
                                           $("#but2").empty().append("重新发送("+time2+")");
                                           return true;
                                       }
                                   }
                                   ,1000)
                            }else{
                                $("#msgC").html(data.message);
                            }
                        },
                        error: function (errorMsg) {
                        }
                    });

                }
            },
            error: function (errorMsg) {
            }
        });
    }
</script>