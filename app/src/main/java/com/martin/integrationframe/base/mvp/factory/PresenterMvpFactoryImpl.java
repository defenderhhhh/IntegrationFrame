package com.martin.integrationframe.base.mvp.factory;


import com.martin.integrationframe.base.mvp.presenter.BaseMvpPresenter;
import com.martin.integrationframe.base.mvp.ui.BaseMvpView;

/**
 * 作者：Martin on 2017/12/16 14:51
 * 邮箱：martin0207mfh@163.com
 */
public class PresenterMvpFactoryImpl<V extends BaseMvpView, P extends BaseMvpPresenter<V>>
        implements PresenterMvpFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V>       当前View实现的接口类型
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseMvpView, P extends BaseMvpPresenter<V>> PresenterMvpFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }

}