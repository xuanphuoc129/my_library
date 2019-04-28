package com.example.mylibrary.screen.main.store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.R;

/**
 * Màn hình Store
 */
public class StoreFragment extends Fragment implements IStoreContract.IView {

    private static final String TAG = StoreFragment.class.getName();

    private IStoreContract.IPresenter mPresenter;

    public StoreFragment() {

    }

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new StorePresenter(this);
    }
}
