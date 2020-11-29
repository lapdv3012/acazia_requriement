package com.mobile.acaziarecruitment.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.mobile.acaziarecruitment.R;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import io.reactivex.disposables.CompositeDisposable;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;
    protected CompositeDisposable compositeDisposable;
    protected int badeCount;
    protected QBadgeView qBadgeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        initView();
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
        EventBus.getDefault().removeAllStickyEvents();
        super.onDestroy();
    }

    public void openActivity(Class<? extends Activity> pClass) {
        openActivity(pClass, null);
    }

    public void openActivity(Class<? extends Activity> pClass, boolean isFinish) {
        openActivity(pClass);
        if (isFinish) {
            finish();
        }
    }

    public void openActivity(Class<? extends Activity> pClass, Bundle bundle) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public <K> Bundle pushBundle(K k) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(k.getClass().getName(), (Parcelable) k);
        return bundle;
    }

    public <K> Bundle putSerializable(K k) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(k.getClass().getName(), (Serializable) k);
        return bundle;
    }

    public void openActivityForResult(Class<? extends Activity> pClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void openActivityForResult(Class<? extends Activity> pClass, int requestCode) {
        Intent intent = new Intent(this, pClass);
        startActivityForResult(intent, requestCode);
    }

    protected void initQBadgeView(int number, View view) {
        if (qBadgeView == null) {
            qBadgeView = new QBadgeView(this);
        }
        qBadgeView.setBadgeNumber(number)
                .setGravityOffset(34, 2, true)
                .bindTarget(view)
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                    }
                });
    }

}