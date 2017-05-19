package com.alphabet.app.http.interceptor;

import com.alibaba.fastjson.JSON;
import com.alphabet.app.http.HttpClient;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LogInterceptor implements Interceptor {
	
	private HttpClient builder;

	public LogInterceptor() {
	  super();
	}
	
	public LogInterceptor(HttpClient builder) {
		this.builder = builder;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		  Request request = chain.request();
		  Map<String, Object> headers;
		  Map<String, Object> params;
		  Map<String,Object> postParam=new HashMap<>();
		  //添加公共Header,公共参数
		  if (builder!=null) {
			  headers=builder.getHeaders();
			  params=builder.getParams();
			if(!headers.isEmpty()){
			  for (Map.Entry<String,Object> entry : headers.entrySet()) {
				  request=request.newBuilder()
						  .addHeader(entry.getKey(), String.valueOf(entry.getValue()))
						  .build();
				  }
			}
			if (!params.isEmpty()) {
				  //get请求    添加公共参数
				  if(request.method().equals("GET")){
					  for (Map.Entry<String, Object> entry : params.entrySet()) {
						  HttpUrl httpUrl=request.url().newBuilder()
								  .addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()))
								  .build();
						  postParam.put(entry.getKey(),  String.valueOf(entry.getValue()));
						  request=request.newBuilder().url(httpUrl).build();
						
					} 
				  }
				  if(request.method().equals("POST")){
					  if (request.body() instanceof FormBody) {
						  FormBody.Builder bodyBuilder = new FormBody.Builder();
						  FormBody formBody = (FormBody) request.body();
						  for (int i = 0; i < formBody.size(); i++) {
								postParam.put(formBody.encodedName(i), formBody.encodedValue(i));
				                bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
				            }
						  for (Map.Entry<String, Object> entry :params.entrySet()) {
							  postParam.put(entry.getKey(), String.valueOf(entry.getValue()));
							  formBody = bodyBuilder
					                    .addEncoded(entry.getKey(), String.valueOf(entry.getValue()))
					                    .build();
						  }
						  request = request.newBuilder().post(formBody).build();
					  }
				  }
			}
		  }
		
		  Response response = chain.proceed(request);
		  okhttp3.MediaType mediaType = response.body().contentType();
          String content = response.body().string();
		
         if (builder.isDebug()) {
			 Logger.init("PRETTYLOGGER")
					 .hideThreadInfo()
					 .methodCount(0);
			 Logger.i("url:" + JSON.toJSONString(response.request().url().toString()));
			 Logger.i("headers:"+ JSON.toJSONString(response.request().headers().toMultimap()));
			 Logger.i("params:" + JSON.toJSONString(postParam));
			 Logger.init("PRETTYLOGGER")
					 .methodCount(1);
		}
    
		return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
	}

	public void setBuilder(HttpClient builder) {
		this.builder = builder;
	}
	
}
