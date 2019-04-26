package com.example.mylibrary.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Relation;

import java.util.List;

@Entity(tableName = "book_in_type")
public class BookInType {

    @ColumnInfo(name = "TypeID")
    private String TypeID; // mã loại sách

    @ColumnInfo(name = "TypeName")
    private String TypeName; // tên loại sách

    @Relation(parentColumn = "TypeID", entityColumn = "TypeID", entity = Book.class)
    private List<Book> books; // danh sách sách cùng thể loại

    public BookInType() {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
