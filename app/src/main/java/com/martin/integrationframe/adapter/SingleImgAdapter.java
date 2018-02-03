package com.martin.integrationframe.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.martin.integrationframe.R;
import com.martin.integrationframe.base.adapter.BaseAdapter;
import com.martin.integrationframe.util.FileUtils;
import com.martin.integrationframe.util.glide.ImageLoader;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Martin on 2018/2/2 15:10
 * 邮箱：martin0207mfh@163.com
 */
public class SingleImgAdapter extends BaseAdapter<Uri> {

    public SingleImgAdapter(List<Uri> data, Context context) {
        super(data, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = getInflater().inflate(R.layout.item_img_single, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Uri item = getItem(position);

        if (item != null) {
            Glide.with(getContext())
                    .load(item)
                    .into(holder.img);
        } else {
            Logger.e("图片地址为空");
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
