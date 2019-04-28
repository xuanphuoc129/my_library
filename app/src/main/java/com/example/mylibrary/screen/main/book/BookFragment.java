package com.example.mylibrary.screen.main.book;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mylibrary.R;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;

import java.util.List;

/**
* Màn hình danh sách sách
* @created_by xuan phuoc on 2019-04-27
*/
public class BookFragment extends Fragment implements IBookContract.IView {

    private IBookContract.IPresenter mPresenter;

    private RecyclerView rcvData, rcvDataType;

    private BookAdapter mBookAdapter;

    private TypeBookAdapter mTypeBookAdapter;

    public BookFragment() {
        // Required empty public constructor
    }

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewByIds(view);

        initRecycleView();

        mPresenter = new BookPresenter(this);
        mPresenter.onLoadBooks();
        mPresenter.onLoadTypeBooks();
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-27
    */
    private void initRecycleView() {
        try {
            mBookAdapter = new BookAdapter(getContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            rcvData.setAdapter(mBookAdapter);
            rcvData.setLayoutManager(linearLayoutManager);


            mTypeBookAdapter = new TypeBookAdapter(getContext());
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            rcvDataType.setAdapter(mTypeBookAdapter);
            rcvDataType.setLayoutManager(layoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-27
    */
    private void findViewByIds(View view) {
        rcvData = view.findViewById(R.id.rcvData);
        rcvDataType = view.findViewById(R.id.rcvDataType);
    }

    /**
    *
    * @created_by xuan phuoc on 2019-04-27
    */
    @Override
    public void setBookDatas(List<Book> list) {
        mBookAdapter.setData(list);
    }

    @Override
    public void setTypeBooks(List<BookInType> list) {
        mTypeBookAdapter.setData(list);
    }

    @Override
    public void showError() {

    }
}
