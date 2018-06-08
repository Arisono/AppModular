package com.android.base.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.android.base.R;
import com.android.base.adapter.ItemTestDataAdapter;
import com.android.base.adapter.LayoutShoppingCartItemAdapter;
import com.android.base.model.ShoppingEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arison on 2018/2/1.
 */

public class ListPanel extends FrameLayout {

    @BindView(R.id.lv_data)
    ListView lv_data;
    List<String> data=new ArrayList<>();
     ItemTestDataAdapter itemTestDataAdapter;
    LayoutShoppingCartItemAdapter itemAdapter;
    List<ShoppingEntity>  shoppingEntities=new ArrayList<>();
    public ListPanel(@NonNull Context context) {
        super(context);
        View view= LayoutInflater.from(context).inflate(R.layout.activity_bottom_sheet, this);
        ButterKnife.bind(view);
        updateData(context);
    }

    public  void updateData(@NonNull Context context) {
        for (int i = 0; i <15; i++) {
            data.add("姓名："+i);
            ShoppingEntity entity=new ShoppingEntity();
            entity.setId("123"+i);
            entity.setName("shangping"+i);
            entity.setUnitPrice(12);
            entity.setQuantity(2);
            shoppingEntities.add(entity);
        }
        itemTestDataAdapter=new ItemTestDataAdapter(context,data);
        itemAdapter=new LayoutShoppingCartItemAdapter(context);
       
        lv_data.setAdapter(itemAdapter);
        itemAdapter.setItems(shoppingEntities);
    }
}
