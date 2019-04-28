package com.example.mylibrary.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.mylibrary.R;
import com.example.mylibrary.base.BaseActivity;
import com.example.mylibrary.data.model.CreateDataEvent;
import com.example.mylibrary.screen.main.author.AuthorFragment;
import com.example.mylibrary.screen.main.book.BookFragment;
import com.example.mylibrary.screen.main.store.StoreFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Màn hình chính
 *
 * @created_by xuan phuoc on 2019-04-27
 */
public class MainActivity extends BaseActivity implements MainContract.IView, BottomNavigationView.OnNavigationItemSelectedListener {

    private MainContract.IPresenter mPresenter;

    private FrameLayout frContainer;

    private BottomNavigationView navView;

    private int mTabSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        openBookFragment();

//        showLoading();

        findViewByIds();

        mPresenter = new MainPresenter(this);


    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(CreateDataEvent event){
        Log.d("fragment", "onEvent: ");
        onLoadingDataDone();
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-28
    */
    private void onLoadingDataDone() {
//        hideLoading();
        openBookFragment();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Chức năng: Khởi tạo view
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    private void findViewByIds() {
        try {
            frContainer = findViewById(R.id.frContainer);
            navView = findViewById(R.id.nav_view);

            navView.setOnNavigationItemSelectedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Chức năng: thiết lập sự kiện
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mTabSelected = item.getItemId();
        try {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    openBookFragment();
                    return true;
                case R.id.navigation_dashboard:
                    openAuthorFragment();
                    return true;
                case R.id.navigation_notifications:
                    openStoreFragment();
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Chức năng: hiển thị fragment tủ sách
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public void openStoreFragment() {
        StoreFragment storeFragment = StoreFragment.newInstance();
        replaceFragment(storeFragment);
    }

    /**
     * Chức năng: hiển thị fragment danh sách tác giả
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public void openAuthorFragment() {
        AuthorFragment fragment = AuthorFragment.newInstance();
        replaceFragment(fragment);
    }

    /**
     * Chức năng: hiển thị fragment danh sách sách
     *
     * @created_by xuan phuoc on 2019-04-27
     */
    public void openBookFragment() {
        BookFragment bookFragment = BookFragment.newInstance();
        replaceFragment(bookFragment);
    }
}
