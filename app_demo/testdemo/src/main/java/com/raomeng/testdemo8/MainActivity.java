package com.raomeng.testdemo8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyListView mMyListView;
    private Button mCancelBtn, mResetBtn, mConfirmBtn;
    private ItemListMenuAdapter mItemListMenuAdapter;
    private List<MenuBean> mMenuBeans, mResetMenuBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyListView = (MyListView) findViewById(R.id.listview);
        mCancelBtn = (Button) findViewById(R.id.cancel_btn);
        mResetBtn = (Button) findViewById(R.id.reset_btn);
        mConfirmBtn = (Button) findViewById(R.id.confirm_btn);

        mCancelBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        mMenuBeans = new ArrayList<>();
        mResetMenuBeans = new ArrayList<>();

        initData();
        Log.d("Test", JSON.toJSONString(mMenuBeans));
        mItemListMenuAdapter = new ItemListMenuAdapter(this, mMenuBeans);
        mMyListView.setAdapter(mItemListMenuAdapter);

    }

    private void initData() {
        MenuBean menuBean1 = new MenuBean();
        menuBean1.setCaption("范围");
        menuBean1.setType("A");
        menuBean1.setPosition(0);

        List<ItemStatesBean> itemStatesBeans1 = new ArrayList<>();
        ItemStatesBean itemStatesBean = new ItemStatesBean();

        itemStatesBean.setOption("0");
        itemStatesBean.setState(false);
        itemStatesBeans1.add(itemStatesBean);
        itemStatesBean = new ItemStatesBean();
        itemStatesBean.setOption("100");
        itemStatesBean.setState(false);
        itemStatesBeans1.add(itemStatesBean);

        menuBean1.setDefaultValues(itemStatesBeans1);


        MenuBean menuBean5 = new MenuBean();
        menuBean5.setCaption("范围");
        menuBean5.setType("A");
        menuBean5.setPosition(3);

        List<ItemStatesBean> itemStatesBeans5 = new ArrayList<>();
        ItemStatesBean itemStatesBean5 = new ItemStatesBean();

        itemStatesBean.setOption("0");
        itemStatesBean.setState(false);
        itemStatesBeans5.add(itemStatesBean5);
        itemStatesBean = new ItemStatesBean();
        itemStatesBean.setOption("100");
        itemStatesBean.setState(false);
        itemStatesBeans5.add(itemStatesBean5);

        menuBean5.setDefaultValues(itemStatesBeans5);
        
        MenuBean menuBean2 = new MenuBean();
        menuBean2.setCaption("时间");
        menuBean2.setType("B");
        menuBean2.setPosition(1);

        List<ItemStatesBean> itemStatesBeans2 = new ArrayList<>();
        ItemStatesBean itemStatesBean2 = new ItemStatesBean();

        itemStatesBean2.setOption("2017-08-10");
        itemStatesBean2.setState(false);
        itemStatesBeans2.add(itemStatesBean2);
        itemStatesBean2 = new ItemStatesBean();
        itemStatesBean2.setOption("2017-08-11");
        itemStatesBean2.setState(false);
        itemStatesBeans2.add(itemStatesBean2);

        menuBean2.setDefaultValues(itemStatesBeans2);

        MenuBean menuBean3 = new MenuBean();
        menuBean3.setCaption("类型");
        menuBean3.setType("C");
        menuBean3.setPosition(2);

        List<ItemStatesBean> itemStatesBeans3 = new ArrayList<>();
        ItemStatesBean itemStatesBean3 = new ItemStatesBean();

        itemStatesBean3.setOption("类型一");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型二");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型三");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型四");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型五");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型六");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);
        itemStatesBean3 = new ItemStatesBean();
        itemStatesBean3.setOption("类型七");
        itemStatesBean3.setState(false);
        itemStatesBeans3.add(itemStatesBean3);

        menuBean3.setDefaultValues(itemStatesBeans3);


        MenuBean menuBean4 = new MenuBean();
        menuBean4.setCaption("状态");
        menuBean4.setType("C");
        menuBean4.setPosition(4);
        List<ItemStatesBean> itemStatesBeans4 = new ArrayList<>();
        ItemStatesBean itemStatesBean4 = new ItemStatesBean();

        itemStatesBean4.setOption("已审核");
        itemStatesBean4.setState(false);
        itemStatesBeans4.add(itemStatesBean4);
        itemStatesBean4 = new ItemStatesBean();
        itemStatesBean4.setOption("待确认");
        itemStatesBean4.setState(false);
        itemStatesBeans4.add(itemStatesBean4);
        itemStatesBean4 = new ItemStatesBean();
        itemStatesBean4.setOption("待审核");
        itemStatesBean4.setState(false);
        itemStatesBeans4.add(itemStatesBean4);

        menuBean4.setDefaultValues(itemStatesBeans4);

        mMenuBeans.add(menuBean1);
        mMenuBeans.add(menuBean3);
        mMenuBeans.add(menuBean2);

        mMenuBeans.add(menuBean5);
        mMenuBeans.add(menuBean4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_btn:
                break;
            case R.id.reset_btn:
//                for (int i = 0; i < mResetMenuBeans.size(); i++) {
//                    List<ItemStatesBean> defaultValues = mResetMenuBeans.get(i).getDefaultValues();
//                    for (int j = 0; j < defaultValues.size(); j++) {
//                        defaultValues.get(j).setState(false);
//                    }
//                }
                mMenuBeans.clear();
                initData();
//                mMenuBeans.addAll(mResetMenuBeans);
//                Log.d("menubeans", mResetMenuBeans.toString());
                mItemListMenuAdapter.notifyDataSetChanged();


                break;
            case R.id.cancel_btn:
                break;
        }
    }
}
