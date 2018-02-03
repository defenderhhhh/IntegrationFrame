package com.martin.integrationframe.mvp.ui.fragment.functionality;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.martin.integrationframe.R;
import com.martin.integrationframe.base.mvc.BaseFragment;
import com.martin.integrationframe.constant.ConstantExtra;
import com.martin.integrationframe.model.ShowImageModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * 作者：Martin on 2018/2/2 13:40
 * 邮箱：martin0207mfh@163.com
 */
public class ShowImageFragment extends BaseFragment {

    @BindView(R.id.img)
    ImageViewTouch img;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void init() {
        ShowImageModel model = getArguments().getParcelable(ConstantExtra.REQUEST_DATA_SINGLE);
        img.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        if (model == null) {
            return;
        }
        Glide.with(getContext())
                .load(model.getPath() == null ? model.getUri() : model.getPath())
                .into(img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
