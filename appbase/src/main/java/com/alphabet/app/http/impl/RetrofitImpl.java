package com.alphabet.app.http.impl;

import com.alphabet.app.http.HttpBase;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.interceptor.CacheInterceptor;
import com.alphabet.app.http.interceptor.LogInterceptor;
import com.alphabet.app.http.retrofit.StringConverterFactory;
import com.alphabet.app.http.rx.RxJavaUtils;
import com.alphabet.app.http.service.ParamService;
import com.alphabet.app.http.ssl.TrustAllCerts;
import com.alphabet.app.http.ssl.TrustAllHostnameVerifier;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Cache;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Retrofit封装Okhttp的方式进行网络操作
 * 
 * @author Arison
 *
 */
public class RetrofitImpl extends HttpBase {

	public Retrofit retrofit;
	public ParamService paramService;
	private static RetrofitImpl instance;
	

	public static RetrofitImpl getInstance() {
		if (instance == null) {
			synchronized (RetrofitImpl.class) {
				if (instance == null) {
					instance = new RetrofitImpl();
				}
			}
		}

		return instance;
	}

	@Override
	public void initClient() {
	    paramService = initApi(ParamService.class);
		Builder okBuilder = new Builder()
				.connectTimeout(mbuilder.getConnectTimeout(), TimeUnit.SECONDS)
				.readTimeout(mbuilder.getReadTimeout(), TimeUnit.SECONDS)
				.writeTimeout(mbuilder.getWriteTimeout(), TimeUnit.SECONDS)
				.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())// 信任所有证书
				.hostnameVerifier(new TrustAllHostnameVerifier());

		LogInterceptor logInterceptor = new LogInterceptor();
		logInterceptor.setBuilder(mbuilder);
		okBuilder.addInterceptor(logInterceptor);	
		okBuilder.cache(new Cache(mbuilder.getCacheFile(), mbuilder.getCacheFileSize()));
		okBuilder.addInterceptor(new CacheInterceptor(String.valueOf(mbuilder.getCacheTime()),mbuilder.getCacheType()));


		OkHttpClient client = okBuilder.build();
		retrofit = new Retrofit.Builder().client(client)
				.baseUrl(mbuilder.getBaseUrl())
				.addConverterFactory(StringConverterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(new Gson()))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
	}

	public <T> T initApi(Class<T> service) {
		return retrofit.create(service);
	}

	@Override
	public void get(HttpClient builder, Subscriber<Object> s) {
		Observable<Object> o = paramService.getParam(builder.getBaseUrl(), builder.getParams(), builder.getHeaders());
		toSubscribe(o, s);
		
	}

	@Override
	public void post(HttpClient builder, Subscriber<Object> s) {
		Observable<Object> o = paramService.postParam(builder.getBaseUrl(), builder.getParams(), builder.getHeaders());
		toSubscribe(o, s);
	}
	
	@Override
	public void uploads(HttpClient mBuilder,Subscriber<Object> s){
		MultipartBody.Builder builder = new MultipartBody.Builder();
		builder.setType(MultipartBody.FORM);
		//追加参数
		for (String key : mbuilder.getParams().keySet()) {
			Object object = mbuilder.getParams().get(key);
			if (!(object instanceof File)) {
				builder.addFormDataPart(key, object.toString());
			} else {
				File file = (File) object;
				//其中参数“file”和服务器接收的参数 一一对应,保证多文件上传唯一key不变
				builder.addFormDataPart("file", file.getName(), RequestBody.create(null, file));
			}
		}
		//创建RequestBody
		RequestBody body = builder.build();
		Observable<Object> o=paramService.uploads(mbuilder.getBaseUrl(), body);
		toSubscribe(o, s);
	}

	private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
		o.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {

			@Override
			public Observable<?> call(Observable<? extends Throwable> t) {
           
				return t.flatMap(new Func1<Throwable, Observable<?>>() {
					private int count = 0;
                     
					@Override
					public Observable<?> call(Throwable t) {
						if (++count <= mbuilder.getMaxRetryCount()) {
							Logger.d("请求重试"+count+"："+t.getMessage());
							Observable<?> ob=	Observable.timer(mbuilder.getRetryTimeout(), TimeUnit.MILLISECONDS);
							return ob;
						}
		
						return Observable.error(t);
					}
				});
			}
		}).map(new Func1<T, T>() {

			@Override
			public T call(T t) {
				return (T) t;
			}
		}).subscribeOn(RxJavaUtils.getScheduler("newThread"))
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(s);
	}


	public SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null,  new TrustManager[] { new TrustAllCerts() }, new SecureRandom());
			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}
		return ssfFactory;
	}
}