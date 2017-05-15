package com.alphabet.app.http.rx;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * @author Arison
 * 网络订阅者
 * @param <T>
 */
public class NetResquestSubscriber<T> extends Subscriber<T> {

	private SubscriberOnNextListener<T> mSubscriberOnNextListener;
	
	public NetResquestSubscriber(SubscriberOnNextListener<T> listener) {
		this.mSubscriberOnNextListener=listener;
	}
	
	
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	
	@Override
	public void onCompleted() {
		
	}

	@Override
	public void onError(Throwable e) {
		   if (e instanceof SocketTimeoutException) {
			   
		   } else if (e instanceof ConnectException) {
			
	        } else {
			   HttpException he=(HttpException) e;
	       }
	}

	@Override
	public void onNext(T t) {
		if(mSubscriberOnNextListener!=null){
			mSubscriberOnNextListener.onNext(t);
		}	
	}

}
