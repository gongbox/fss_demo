package com.fss.demo.base;


import androidx.annotation.Keep;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Administrator on 2017/10/21.
 */

public class BaseBindingActivity<VB extends ViewDataBinding> extends BaseActivity {

    @Keep
    protected VB binding;

    @Override
    protected void setContentLayout(int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
    }
}
