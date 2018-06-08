package com.android.base.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.base.R;
import com.android.base.adapter.ItemTestDataAdapter;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomSheetActivity extends Activity {
    @BindView(R.id.bottom_sheet_layout)
    BottomSheetLayout mBottomSheetLayout;
    @BindView(R.id.bt_bottom)
    Button button;
    ListPanel listPanel;
    @BindView(R.id.lv_left)
    ListView lv_left;
    @BindView(R.id.lv_right)
     ListView lv_right;
    List<String> data=new ArrayList<>();
    ItemTestDataAdapter itemTestDataAdapter;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_bottomsheet);
        ButterKnife.bind(this);

        for (int i = 0; i <15; i++) {
            data.add("姓名："+i);
        }
        itemTestDataAdapter=new ItemTestDataAdapter(this,data);
        lv_left.setAdapter(itemTestDataAdapter);
        
        lv_right.setAdapter(itemTestDataAdapter);
        listPanel=new ListPanel(this);
        mBottomSheetLayout.showWithSheetView(  listPanel);
        
        mBottomSheetLayout.addOnSheetStateChangeListener(new BottomSheetLayout.OnSheetStateChangeListener() {
            @Override
            public void onSheetStateChanged(BottomSheetLayout.State state) {
                Logger.d("BottomSheetLayout.State:"+state);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (! mBottomSheetLayout.isSheetShowing()){
                    listPanel.updateData(BottomSheetActivity.this);
                    mBottomSheetLayout.showWithSheetView(  listPanel);
                }else{
                    mBottomSheetLayout.dismissSheet();
                }
            }
        });
    }
}
