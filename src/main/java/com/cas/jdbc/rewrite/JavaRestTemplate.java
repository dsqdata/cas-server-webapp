package com.cas.jdbc.rewrite;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
public class JavaRestTemplate {

    private static RestTemplate restTemplate = new RestTemplate();


    private static long expire  =14000;

    private static String pathUrl = "http://127.0.0.1:8005/sys/sendMessage/doPost";
    private static String pathUrlN = "http://127.0.0.1:8005/api/mq/sms/sendSms";
    private static String loginUrl = "http://127.0.0.1:8005/auth/login";
    private static String username = "统一认证平台";
    private static String password = "Jx6VWC98ENsN5SXgMi91QgdUiU8XQ9Y+gjKDHTnOB9q5TxXAaMraFZ/WFOJ5jRUwfZPmFfNpfXTvs25gDMrArw==";
    private static String templateN = "SMS_184220413";
    private static String sign = "园区企业服务平台";



    public static void sendMessage(String mobile,String verifyCode,String content) throws Exception{

        String str = "";
        String tokens = "";

        tokens =getToken();
        if("".equals(tokens)){
            return ;
        }
        //接口地址
        String url = pathUrlN;

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
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        //访问接口并获取返回值
       // ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity,String.class);
       /* RequestEntity<JSONObject> requestEntity = RequestEntity.post(new URI(url)).header("Authorization",tokens).
               contentType(type).accept(type).body(datas);//此处传入obj

        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(requestEntity, JSONObject.class);

        //输出接口所返回过来的值
        System.out.println(responseEntity.getBody());*/

    }

    public static String getToken() throws Exception{
        String str = "";
        String token = "token";
        String tokens = "";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);

        //接口地址
        String url = loginUrl;

        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("password", password);
        //放入Http传输的数据
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(data,headers);
        //访问接口并获取返回值
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity,String.class);
        //输出接口所返回过来的值
        System.out.println(responseEntity.getBody());
        com.alibaba.fastjson.JSONObject dataObject = null;
        dataObject = JSON.parseObject( responseEntity.getBody());
        tokens = String.valueOf(dataObject.get("token"));


        return tokens;
    }

}