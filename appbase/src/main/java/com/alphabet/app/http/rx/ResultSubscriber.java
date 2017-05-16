package com.alphabet.app.http.rx;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author Arison
 * 网络订阅者
 * @param <T>
 */
public class ResultSubscriber<T> extends Subscriber<T> {

	private ResultListener<T> resultListener;
	private Result2Listener<T> result2Listener;
	
	public ResultSubscriber(ResultListener<T> listener) {
		if (listener!=null){
			if (listener instanceof  Result2Listener){
				this.result2Listener= (Result2Listener<T>) listener;
			}
			this.resultListener=listener;
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		//Logger.d("网络请求开始 onStart()");
	}
	
	
	@Override
	public void onCompleted() {
		//Logger.d("网络请求结束 onCompleted()");
	}

	@Override
	public void onError(Throwable e) {
		if (result2Listener!=null) {
			if (e instanceof SocketTimeoutException) {
				Logger.e("SocketTimeoutException");
				result2Listener.onFailure(e);
			} else if (e instanceof ConnectException) {
				Logger.e("ConnectException");
				result2Listener.onFailure(e);
			} else {
				HttpException he = (HttpException) e;
				try {
					result2Listener.onFailure(he.response().errorBody().string());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			if (e instanceof HttpException){
				try {
					resultListener.onResponse((T)((HttpException) e).response().errorBody().string());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else if (e instanceof SocketTimeoutException){
				resultListener.onResponse((T)e);
			}else if(e instanceof ConnectException){
				resultListener.onResponse((T)e);
			}
			
		}
	}

	@Override
	public void onNext(T t) {
		if (resultListener!=null){
			resultListener.onResponse(t);
		}
	}

}
