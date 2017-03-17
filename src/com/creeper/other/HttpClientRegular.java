package com.creeper.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientRegular {
	private String getHtmlFile(String rootUrl) {
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		HttpGet httpget = new HttpGet(rootUrl);  
		CloseableHttpResponse response;
			try {
				response = httpclient.execute(httpget);
				HttpEntity httpEntity=response.getEntity();
				if (httpEntity != null) {
					return getIs(httpEntity);
				}
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	private String getIs(HttpEntity httpEntity) {
		InputStream is;
		try {
			is = httpEntity.getContent();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sBuffer=new StringBuffer();
			String str="";
			while ((str = br.readLine()) != null)
				sBuffer.append(str);
			return sBuffer.toString();
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
