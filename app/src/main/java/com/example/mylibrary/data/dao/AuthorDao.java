package com.example.mylibrary.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.mylibrary.data.model.Author;
import com.example.mylibrary.data.model.BookOfAuthor;

import java.util.List;

/**
 * Dao xử lý dữ liệu với bảng tác giả
 *
 * @created_by xuan phuoc on 2019-04-26
 */
@Dao
public interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addAuthor(Author author); // thêm 1 tác giả

    @Insert
    @Transaction
    long[] addAllAuthor(List<Author> authors); // thêm nhiều tác giả

    @Query("SELECT * FROM authors")
    List<Author> getAllAuthor(); // Lấy toàn bộ danh sách tác giả


}

