package com.example.mylibrary.screen.main.book;

import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookInType;

import java.util.List;

/**
 * View cho màn hình sách
 *
 * @created_by xuan phuoc on 2019-04-27
 */
public interface IBookContract {

    interface IView {

        void setBookDatas(List<Book> list);

        void setTypeBooks(List<BookInType> list);

        void showError();

    }

    interface IPresenter {

        void onLoadBooks();

        void onLoadTypeBooks();

    }
}
