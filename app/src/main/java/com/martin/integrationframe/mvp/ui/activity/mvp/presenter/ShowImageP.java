package com.martin.integrationframe.mvp.ui.activity.mvp.presenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.martin.integrationframe.base.adapter.ViewPagerFragmentAdapter;
import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.constant.ConstantExtra;
import com.martin.integrationframe.model.ShowImageModel;
import com.martin.integrationframe.mvp.ui.activity.mvp.view.ShowImageV;
import com.martin.integrationframe.mvp.ui.fragment.functionality.ShowImageFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * 作者：Martin on 2018/2/2 12:44
 * 邮箱：martin0207mfh@163.com
 */
public class ShowImageP extends BaseMvpPresenter<ShowImageV> {

    private Disposable subscribe;
    private List<Fragment> listFragment;

    public void initPageAdapter(final FragmentManager manager, final List<ShowImageModel> list) {
        listFragment = new ArrayList<>();

        subscribe = Observable.fromIterable(list)
                .filter(new Predicate<ShowImageModel>() {
                    @Override
                    public boolean test(ShowImageModel model) throws Exception {
                        return model != null && (model.getPath() != null || model.getUri() != null);
                    }
                })
                .map(new Function<ShowImageModel, ShowImageFragment>() {
                    @Override
                    public ShowImageFragment apply(ShowImageModel model) throws Exception {
                        ShowImageFragment fragment = new ShowImageFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(ConstantExtra.REQUEST_DATA_SINGLE, model);
                        fragment.setArguments(bundle);
                        return fragment;
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(manager, listFragment);
                        getView().showImages(adapter);
                    }
                })
                .subscribe(new Consumer<ShowImageFragment>() {
                    @Override
                    public void accept(ShowImageFragment showImageFragment) throws Exception {
                        listFragment.add(showImageFragment);
                    }
                });
    }

    @Override
    public void onDetachView() {
        super.onDetachView();
        if (!subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
