package com.alphabet.message;

import com.alphabet.message.impl.SuperToastUtils;

/**
 * 全局消息调用层
 * Created by Arison on 2017/5/27.
 */
public class ToastUtils {
    
    private BaseToast mToast;
    private static ToastUtils instance;
    
    public ToastUtils(){
        mToast= SuperToastUtils.getInstance();
    }

    public static ToastUtils create(){
        if (instance==null){
            synchronized (ToastUtils.class){
                if (instance==null){
                    instance=new ToastUtils();
                }
            }
        }
        return  instance;
    }
    
    
    public  BaseToast Builder(){
        return  mToast;
    }
    
    

}
