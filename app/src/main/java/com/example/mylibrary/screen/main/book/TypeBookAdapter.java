package com.example.mylibrary.screen.main.book;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.example.mylibrary.base.IBaseAdapter;
import com.example.mylibrary.data.model.BookInType;

import java.util.ArrayList;
import java.util.List;

public class TypeBookAdapter extends RecyclerView.Adapter<TypeBookAdapter.ViewHolder> implements IBaseAdapter<BookInType> {
        
    private ArrayList<BookInType> mDatas;
    
    private Context context;

    private static final String[] COLORS = {"#9c27b0", "#673ab7", "#2196f3", "#e91e63", "#f44336"};

    public TypeBookAdapter(Context context) {
        this.context = context;
        mDatas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_type_book, viewGroup, false);
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
    public void setData(List<BookInType> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        private TextView tvTypeBookName, tvNumberBook;

        private LinearLayout llContainer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTypeBookName = itemView.findViewById(R.id.tvTypeBookName);
            tvNumberBook = itemView.findViewById(R.id.tvNumberBook);
            llContainer = itemView.findViewById(R.id.llContainer);
        }
        
        void onBindData(BookInType item){
            tvTypeBookName.setText(item.getTypeName());
            String quantity = item.getBooks().size() +" "+ context.getString(R.string.book_unit);
            tvNumberBook.setText(quantity);


            tvTypeBookName.setTextColor(Color.WHITE);
            tvNumberBook.setTextColor(Color.WHITE);

            Drawable drawable = context.getResources().getDrawable(R.drawable.bg_book_type);
            drawable.setColorFilter(Color.parseColor(COLORS[getAdapterPosition()]), PorterDuff.Mode.SRC);
            llContainer.setBackground(drawable);
        }
    }
}
