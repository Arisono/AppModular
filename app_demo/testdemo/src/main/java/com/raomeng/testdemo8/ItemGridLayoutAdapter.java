package com.raomeng.testdemo8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemGridLayoutAdapter extends BaseAdapter {
    private List<ItemStatesBean> objects = new ArrayList<ItemStatesBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemGridLayoutAdapter(Context context, List<ItemStatesBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public List<ItemStatesBean> getObjects() {
        return objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ItemStatesBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid_layout, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ItemStatesBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(ItemStatesBean object, ViewHolder holder) {
        holder.gridText.setText(object.getOption());
        if (object.isState()) {
            holder.gridText.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            holder.gridText.setTextColor(context.getResources().getColor(android.R.color.black));
        }
    }

    protected class ViewHolder {
        private TextView gridText;

        public ViewHolder(View view) {
            gridText = (TextView) view.findViewById(R.id.grid_text);
        }
    }
}
