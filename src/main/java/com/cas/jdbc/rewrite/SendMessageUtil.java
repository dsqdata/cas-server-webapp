package com.cas.jdbc.rewrite;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

@Component
public class SendMessageUtil {


    private static long expire  =14000;

    private static String pathUrl = "http://127.0.0.1:8005/sys/sendMessage/doPost";
    private static String pathUrlN = "http://127.0.0.1:8005/api/mq/sms/sendSms";
    private static String loginUrl = "http://127.0.0.1:8005/auth/login";
    private static String username = "统一认证平台";
    private static String password = "Jx6VWC98ENsN5SXgMi91QgdUiU8XQ9Y+gjKDHTnOB9q5TxXAaMraFZ/WFOJ5jRUwfZPmFfNpfXTvs25gDMrArw==";
    private static String templateN = "SMS_184220413";
    private static String sign = "园区企业服务平台";

    public static String sendMessage(String mobile,String verifyCode,String content) throws  Exception{

        String str = "";
        String tokens = "";
        int code = 0;
        com.alibaba.fastjson.JSONObject dataObject = null;


        tokens =getToken();
        JSONObject datas = new JSONObject();
        JSONObject datass = new JSONObject();
        datass.put("code",verifyCode);
        datas.put("acceptUser","");
        datas.put("contents",datass);
        datas.put("mobile",mobile);
        datas.put("sender","");
        datas.put("sign",sign);
        datas.put("template",templateN);
        datas.put("id",UUID.randomUUID().toString());

        URL url = new URL(pathUrlN);
        //打开和url之间的连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        PrintWriter out = null;
        //请求方式
        conn.setRequestMethod("POST");
//           //设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setRequestProperty("Authorization",tokens+"123");
        //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
        //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
        //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //获取URLConnection对象对应的输出流
        out = new PrintWriter(conn.getOutputStream());
        //发送请求参数即数据
        out.print(datas);
        //out.write("&mobile="+mobile);
        //缓冲数据
        out.flush();
        code=conn.getResponseCode();
        if(code == 401){

            conn.disconnect();
            tokens=getToken();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
//           //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Authorization",tokens);
            out = new PrintWriter(conn.getOutputStream());
            out.print(datas);
            out.flush();
            code=conn.getResponseCode();
        }
        if(code == 200){
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();

            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            //关闭流
            is.close();
        }
        //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
        //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
        conn.disconnect();

        return str;
    }


public static String getToken() throws  Exception{
        String str = "";
        String token = "token";
        String tokens = "";
        com.alibaba.fastjson.JSONObject dataObject = null;

        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("password", password);

        URL urls = new URL(loginUrl);
        //打开和url之间的连接
        HttpURLConnection conns = (HttpURLConnection) urls.openConnection();
        PrintWriter outs = null;
        //请求方式
        conns.setRequestMethod("POST");
//           //设置通用的请求属性
        conns.setRequestProperty("accept", "*/*");
        conns.setRequestProperty("connection", "Keep-Alive");
        conns.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conns.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conns.setRequestProperty("Authorization", "Bearer ");
        //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
        //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
        //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
        conns.setDoOutput(true);
        conns.setDoInput(true);
        //获取URLConnection对象对应的输出流
        outs = new PrintWriter(conns.getOutputStream());
        //发送请求参数即数据
        outs.print(data);
        //outs.write("&mobile="+mobile);
        //缓冲数据
        outs.flush();
        //获取URLConnection对象对应的输入流
        InputStream iss = conns.getInputStream();
        //构造一个字符流缓存
        BufferedReader brs = new BufferedReader(new InputStreamReader(iss));

        while ((str = brs.readLine()) != null) {
            dataObject = JSON.parseObject(str);
        }
        if(dataObject != null) {
            tokens = String.valueOf(dataObject.get("token"));
        }

        //关闭流
        iss.close();
        //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
        //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
        conns.disconnect();



        return tokens;
    }





}
