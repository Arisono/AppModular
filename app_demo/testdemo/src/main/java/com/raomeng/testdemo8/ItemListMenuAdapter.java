package com.raomeng.testdemo8;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListMenuAdapter extends BaseAdapter {
    private List<MenuBean> objects = new ArrayList<MenuBean>();
    private List<MenuBean> mConfirmMenuBeans = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemListMenuAdapter(Context context, List<MenuBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public List<MenuBean> getObjects() {
        return objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MenuBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list_menu, null);
            convertView.setTag(new ViewHolder(convertView));
//        }
        initializeViews((MenuBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final MenuBean object, final ViewHolder holder, final int position) {
        holder.caption.setText(object.getCaption());
        holder.textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/8/15 弹出时间选择框
            }
        });

        holder.textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/8/15 弹出时间选择框
            }
        });



 

        holder.menuGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<ItemStatesBean> itemStatesBeans
                        = object.getDefaultValues();
                for (int i = 0; i < itemStatesBeans.size(); i++) {
                    if (i == position) {
                        itemStatesBeans.get(i).setState(true);
                    } else {
                        itemStatesBeans.get(i).setState(false);
                    }
                    notifyDataSetChanged();
                }
            }
        });


        switch (object.getType()) {
            case "A":

                holder.edittext1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d("Test","Position:"+object.getPosition()+" position:"+position);
                      
                        if ("A".equals(objects.get(position).getType())&&object.getPosition()==position){
                            Log.d("Test", position + "");
                            object.getDefaultValues().get(0).setOption(s.toString());
                        }

                    }
                });

                holder.edittext2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if ("A".equals(objects.get(position).getType())&&object.getPosition()==position) {
                            Log.d("Cposition2", position + "");
                            objects.get(position).getDefaultValues().get(1).setOption(s.toString());
                        }
                    }
                });
                holder.menuEdittext.setVisibility(View.VISIBLE);
                holder.menuTextview.setVisibility(View.GONE);
                holder.menuGridview.setVisibility(View.GONE);

                List<ItemStatesBean> defaultValues = object.getDefaultValues();
                if (defaultValues != null && defaultValues.size() == 2) {
                    holder.edittext1.setText(defaultValues.get(0).getOption());
                    holder.edittext2.setText(defaultValues.get(1).getOption());
                }
                break;
            case "B":
                holder.menuEdittext.setVisibility(View.GONE);
                holder.menuTextview.setVisibility(View.VISIBLE);
                holder.menuGridview.setVisibility(View.GONE);

                defaultValues = object.getDefaultValues();
                if (defaultValues != null && defaultValues.size() == 2) {
                    holder.textview1.setText(defaultValues.get(0).getOption());
                    holder.textview2.setText(defaultValues.get(1).getOption());
                }

                break;
            case "C":
                holder.menuEdittext.setVisibility(View.GONE);
                holder.menuTextview.setVisibility(View.GONE);
                holder.menuGridview.setVisibility(View.VISIBLE);

                defaultValues = object.getDefaultValues();

                if (defaultValues != null) {
                    ItemGridLayoutAdapter arrayAdapter = new ItemGridLayoutAdapter(context, defaultValues);
                    holder.menuGridview.setAdapter(arrayAdapter);
                }

                break;
            default:
                break;
        }
    }

    protected class ViewHolder {
        private TextView caption;
        private LinearLayout menuEdittext;
        private EditText edittext1;
        private EditText edittext2;
        private LinearLayout menuTextview;
        private TextView textview1;
        private TextView textview2;
        private MyGridView menuGridview;

        public ViewHolder(View view) {
            caption = (TextView) view.findViewById(R.id.caption);
            menuEdittext = (LinearLayout) view.findViewById(R.id.menu_edittext);
            edittext1 = (EditText) view.findViewById(R.id.edittext1);
            edittext2 = (EditText) view.findViewById(R.id.edittext2);
            menuTextview = (LinearLayout) view.findViewById(R.id.menu_textview);
            textview1 = (TextView) view.findViewById(R.id.textview1);
            textview2 = (TextView) view.findViewById(R.id.textview2);
            menuGridview = (MyGridView) view.findViewById(R.id.menu_gridview);
        }
    }
}
