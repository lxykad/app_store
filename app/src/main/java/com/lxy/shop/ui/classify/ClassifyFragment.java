package com.lxy.shop.ui.classify;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxy.shop.R;
import com.lxy.shop.common.base.BaseFragment;
import com.lxy.shop.databinding.FragmentClassifyBinding;
import com.lxy.shop.di.component.AppComponent;
import com.lxy.shop.di.component.DaggerClassifyComponent;
import com.lxy.shop.di.module.ClassifyModule;
import com.lxy.shop.ui.classify.adapter.ClassifyAdapter;
import com.lxy.shop.ui.classify.bean.ClassifyBean;
import com.lxy.shop.ui.classify.constract.ClassifyConstract;
import com.lxy.shop.ui.classify.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

/**
 * Created by lxy on 2017/6/8.
 */

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyConstract.ClassifyView {


    private FragmentClassifyBinding mBinding;
    private List<ClassifyBean> mList;
    private ClassifyAdapter mAdapter;

    @Override
    protected void visiableToUser() {

    }

    @Override
    protected void firstVisiableToUser() {
        loadData();
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerClassifyComponent.builder()
                .appComponent(appComponent)
                .classifyModule(new ClassifyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutId() {

        return R.layout.fragment_classify;
    }

    @Override
    public void initChildBinding() {

        mBinding = (FragmentClassifyBinding) mChildBinding;
        mList = new ArrayList<>();

        mAdapter = new ClassifyAdapter(R.layout.list_item_classify_fragment,mList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerView.setAdapter(mAdapter);

        initEvents();

    }

    @Override
    public void onEmptyClick(View view) {
        loadData();
    }

    @Override
    public void onGetClassifySuccess(List<ClassifyBean> list) {
        LogUtils.d("ClassifyFragment=====size=="+list.size());
        mAdapter.addData(list);
    }

    public void loadData() {
        mPresenter.getData();
    }

    public void initEvents(){
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ClassifyBean bean = mList.get(position);

                showToast(bean.getName());
            }
        });
    }
}
