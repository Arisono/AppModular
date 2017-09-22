package com.alphabet.app.http.service;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @des 主要用于网络请求
 * @name ParamService
 * @method Rxjava+Retrofit
 * @author Arison
 */
public interface ParamService {
   
	@GET()
	Observable<Object> getParam(@Url String url);
	@GET()
	Observable<Object> getParam(@Url String url, @QueryMap Map<String, Object> param);
	@GET()
	Observable<Object> getParam(@Url String url, @QueryMap Map<String, Object> param, @HeaderMap Map<String, Object> header);
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url);
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url, @FieldMap Map<String, Object> param);
	@FormUrlEncoded
	@POST()
	Observable<Object> postParam(@Url String url, @FieldMap Map<String, Object> param, @HeaderMap Map<String, Object> header);


	@POST()
	Observable<Object> uploads(
			@Url String url,
			@Body RequestBody body);
}
