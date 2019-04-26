package com.example.mylibrary.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Bảng thông tin loại sách
 *
 * @created_by xuan phuoc on 2019-04-26
 */
@Entity(tableName = "book_type")
public class BookType {

    @PrimaryKey
    @ColumnInfo(name = "TypeID")
    @NonNull
    private String TypeID; // mã loại sách

    @ColumnInfo(name = "TypeName")
    private String TypeName; // tên loại sách

    public BookType() {

    }

    public String getTypeID() {
        return TypeID;
    }

    public void setTypeID(String typeID) {
        TypeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }
}
