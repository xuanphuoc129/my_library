package com.example.mylibrary.screen.main.author;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.R;

/**
 * Màn hình danh sách tác giả
 *
 * @created_by xuan phuoc on 2019-04-27
 */
public class AuthorFragment extends Fragment implements IAuthorContract.IView {

    private IAuthorContract.IPresenter mPresenter;

    public AuthorFragment() {
    }

    public static AuthorFragment newInstance() {
        return new AuthorFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new AuthorPresenter(this);
    }
}
