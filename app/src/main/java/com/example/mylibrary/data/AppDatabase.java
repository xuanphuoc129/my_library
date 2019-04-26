package com.example.mylibrary.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mylibrary.data.dao.AuthorDao;
import com.example.mylibrary.data.dao.BookDao;
import com.example.mylibrary.data.dao.BookTypeDao;
import com.example.mylibrary.data.model.Author;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.data.model.BookType;

/**
 * Class đăng ký danh sách bảng lưu trong database và các interface dao lấy dữ liệu
 *
 * @created_by xuan phuoc on 2019-04-26
 */
@Database(entities = {Book.class, Author.class, BookType.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao getBookDao();

    public abstract AuthorDao getAuthorDao();

    public abstract BookTypeDao getBookTypeDao();

}
