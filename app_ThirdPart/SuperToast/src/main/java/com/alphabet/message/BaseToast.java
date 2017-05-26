package com.alphabet.message;

import android.content.Context;

/**
 * 统一规范消息组件的调用
 * 方向：底部，中部，顶部
 * 监听事件:
 * 样式：
 * 第一步：构建指定参数的公共接口
 * 第二步：通过builder模式传入大量自定义化参数的接口
 * Created by Arison on 2017/5/25.
 */
public interface BaseToast {
    
    /**
      * @desc:APP系统级别的消息提示
      *        与Activity生命周期无关
      *  @author：Arison
     */
    void show(String text);
    void showAtTop(String text);
    void showAtCenter(String text);
    void showAtBottom(String text);
    void showAtNotiftion(String text);

    /**
      * @desc:Activity 生命周期绑定的消息提示
      * @author：Arison 
      */
    void show(Context ct,String text);
    void showAtTop(Context ct,String text);
    void showAtCenter(Context ct,String text);
    void showAtBottom(Context ct,String text);
    void showAtNotiftion(Context ct,String text);

}
