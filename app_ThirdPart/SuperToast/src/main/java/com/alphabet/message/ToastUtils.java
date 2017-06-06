package com.alphabet.message;

/**
 * 全局消息调用层
 * Created by Arison on 2017/5/27.
 */
public class ToastUtils {
    
    private BaseToast mToast;
    private static ToastUtils instance;
    
    public ToastUtils(BaseToast toast){
        mToast= toast;
    }

    public static ToastUtils create(BaseToast mToast){
        if (instance==null){
            synchronized (ToastUtils.class){
                if (instance==null){
                    instance=new ToastUtils(mToast);
                }
            }
        }
        return  instance;
    }
    
    
    public  BaseToast Builder(){
        return  mToast;
    }
    
    

}
