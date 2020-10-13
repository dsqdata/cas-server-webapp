package com.cas.jdbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 作者 zhaobb
 * @version 创建时间：2015年8月18日 下午2:41:14
 * 类说明
 */
public class KsdUtils {
	
	
	public static  boolean isEmail(String str) {
		return Pattern.matches("\\w+((\\.|\\-|\\+)\\w+)*@([\\w\\-]+\\.)+(\\w{2,4})+", str);
	}
	
	public static  boolean isMobile(String mobile){
		Pattern p = Pattern.compile("^(1[0-9])\\d{9}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}
	
	public static String getAccountField (String  account){
		boolean ismobile = KsdUtils.isMobile(account);
		String accountField = ismobile ? "mobile" : (KsdUtils.isEmail(account) ? "email" : "nickname");
		return accountField;
	}
}
