package com.android.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

/**
 * @author :LiuJie 2015年11月24日 上午9:31:39
 * @注释:并排特性的ListView
 */
public class CustomerListView extends ListView {

    public CustomerListView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerListView(Context context) {
        super(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**@注释：这两个代码是用在嵌套scrollview控件中的，解决显示一行的问题，但是会产生新的问题  */
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
        int height = getMeasuredHeight();
        int width = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            //设置了精确的宽度，则为精确模式
            Log.i("ListView", "精确模式");
            width = widthSize;
        } else {
            if (widthMode == MeasureSpec.AT_MOST) {
                Log.i("ListView", "AT_MOST模式");
                final int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = getChildAt(i);
                    measureChild(view, widthMeasureSpec, heightMeasureSpec);
                    width = Math.max(width, view.getMeasuredWidth());
                    //height =+Math.max(height, view.getMeasuredHeight());
                }
            }
        }
        setMeasuredDimension(width, height);
    }
}
