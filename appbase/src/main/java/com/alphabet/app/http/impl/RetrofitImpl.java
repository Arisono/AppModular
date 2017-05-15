package com.alphabet.app.http.impl;

import com.alphabet.app.http.HttpBase;
import com.alphabet.app.http.HttpClient;
import com.alphabet.app.http.interceptor.CacheInterceptor;
import com.alphabet.app.http.interceptor.LogInterceptor;
import com.alphabet.app.http.retrofit.StringConverterFactory;
import com.alphabet.app.http.rx.RxjavaUtils;
import com.alphabet.app.http.service.ParamService;
import com.alphabet.app.http.ssl.TrustAllCerts;
import com.alphabet.app.http.ssl.TrustAllHostnameVerifier;
import com.google.gson.Gson;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
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
		ParamService paramService = initApi(ParamService.class);
		Observable<Object> o = paramService.getParam(builder.getBaseUrl(), builder.getParams(), builder.getHeaders());
		toSubscribe(o, s);
		
	}

	@Override
	public void post(HttpClient builder, Subscriber<Object> s) {
		ParamService paramService = initApi(ParamService.class);
		Observable<Object> o = paramService.postParam(builder.getBaseUrl(), builder.getParams(), builder.getHeaders());
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
		}).subscribeOn(RxjavaUtils.getScheduler("newThread"))
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