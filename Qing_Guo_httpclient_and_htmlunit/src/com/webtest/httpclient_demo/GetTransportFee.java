package com.webtest.httpclient_demo;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.webtest.core.BaseTest;

import net.sf.json.JSONObject;

/*
 * 作者：黄瑾然
 * */

public class GetTransportFee extends BaseTest{
	String address;
	double transportFee;
	CloseableHttpClient httpclient=null;
	
	//计算运费成功
	@Test
    public void getTransportFee1() throws IOException{
    	JSONObject json= JSONObject.fromObject(this.address);
    	JSONObject result=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
    	String addressDetail=result.get("province")+"_"+result.get("city")+"_"+result.get("area");
    	int id=result.getInt("id");
    	String url=String.format("http://study-perf.qa.netease.com/common/getTransportFee?id=%d&addressDetail=%s", id,addressDetail);
    	CloseableHttpResponse response=null;
    	try {
    		HttpGet httpGet=new HttpGet(url);
    		response=httpclient.execute(httpGet);
    		httpGet.setHeader("Content-Type", "application/json");
    		HttpEntity entity=response.getEntity();
    		if(entity !=null) {
    			String feeResult=EntityUtils.toString(entity,"utf-8");
    			this.transportFee=JSONObject.fromObject(feeResult).getDouble("result");
    		}
    		EntityUtils.consume(entity);
    		response.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
	//计算运费失败（收货地址编号类型不正确）
	@Test
    public void getTransportFee2() throws IOException{
    	JSONObject json= JSONObject.fromObject(this.address);
    	JSONObject result=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
    	String addressDetail=result.get("province")+"_"+result.get("city")+"_"+result.get("area");
    	int id=result.getInt("id");
    	String url=String.format("http://study-perf.qa.netease.com/common/getTransportFee?id='%d&addressDetail=%s'", id,addressDetail);
    	CloseableHttpResponse response=null;
    	try {
    		HttpGet httpGet=new HttpGet(url);
    		response=httpclient.execute(httpGet);
    		httpGet.setHeader("Content-Type", "application/json");
    		HttpEntity entity=response.getEntity();
    		if(entity !=null) {
    			String feeResult=EntityUtils.toString(entity,"utf-8");
    			this.transportFee=JSONObject.fromObject(feeResult).getDouble("result");
    		}
    		EntityUtils.consume(entity);
    		response.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
	//计算运费失败（收货地址编号不存在）
	@Test
    public void getTransportFee3() throws IOException{
    	JSONObject json= JSONObject.fromObject(this.address);
    	JSONObject result=json.getJSONObject("result").getJSONArray("list").getJSONObject(0);
    	String addressDetail=result.get("province")+"_"+result.get("city")+"_"+result.get("area");
    	int id=result.getInt("id");
    	String url=String.format("http://study-perf.qa.netease.com/common/getTransportFee?id=hhh", id,addressDetail);
    	CloseableHttpResponse response=null;
    	try {
    		HttpGet httpGet=new HttpGet(url);
    		response=httpclient.execute(httpGet);
    		httpGet.setHeader("Content-Type", "application/json");
    		HttpEntity entity=response.getEntity();
    		if(entity !=null) {
    			String feeResult=EntityUtils.toString(entity,"utf-8");
    			this.transportFee=JSONObject.fromObject(feeResult).getDouble("result");
    		}
    		EntityUtils.consume(entity);
    		response.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    } 
}
