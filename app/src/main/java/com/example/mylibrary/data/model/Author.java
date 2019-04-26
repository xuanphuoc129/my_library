package com.example.mylibrary.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Bảng thông tin tác giả
 *
 * @created_by xuan phuoc on 2019-04-26
 */
@Entity(tableName = "authors")
public class Author {

    @PrimaryKey
    @ColumnInfo(name = "AuthorID")
    @NonNull
    private String AuthorID; // mã tác giả

    @ColumnInfo(name = "AuthorName")
    private String AuthorName; // tên tác giả

    public Author() {
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String authorID) {
        AuthorID = authorID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}
