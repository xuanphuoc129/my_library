package com.example.mylibrary.screen.main.book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.example.mylibrary.base.IBaseAdapter;
import com.example.mylibrary.data.model.Book;
import com.example.mylibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements IBaseAdapter<Book> {

    private ArrayList<Book> mDatas;

    private Context mContext;

    public BookAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBindData(mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void setData(List<Book> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivBookThumbnail;

        TextView tvRateNumber, tvBookName, tvBookAuthorName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivBookThumbnail = itemView.findViewById(R.id.ivBookThumbnail);
            tvRateNumber = itemView.findViewById(R.id.tvRateNumber);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookAuthorName = itemView.findViewById(R.id.tvBookAuthorName);

        }

        void onBindData(Book book) {
            CommonUtils.loadImageFromUrl(mContext, ivBookThumbnail, book.getBookThumbnail());
            tvRateNumber.setText(String.valueOf(book.getRateNumber()));
            tvBookName.setText(book.getBookName());
        }
    }
}
