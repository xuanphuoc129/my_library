package com.example.mylibrary.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.example.mylibrary.data.model.BookType;

import java.util.List;


/**
 * Dao xử lý dữ liệu với bảng sách
 *
 * @created_by xuan phuoc on 2019-04-26
 */

@Dao
public interface BookTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addBookType(BookType bookType);   // thêm mới 1 thể loại sách

    @Insert
    @Transaction
    long[] addAllBookType(List<BookType> bookTypes);     // thêm nhiều thể sách

    @Query("SELECT * FROM book_type")
    List<BookType> getAllBookType();     // lấy toàn bộ thể loại sách


}
