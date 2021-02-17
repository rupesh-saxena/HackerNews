package com.digivalet.hackernews.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.digivalet.data.dto.ArticleResponse;
import com.digivalet.hackernews.R;
import com.digivalet.hackernews.databinding.RowItemBinding;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final Context context;
    public List<ArticleResponse> list;


    public ListAdapter(Context context, List<ArticleResponse> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<ArticleResponse> list) {
        this.list = list;
    }

    public ArticleResponse getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemBinding.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RowItemBinding itemBinding;

        public ViewHolder(RowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }
    }
}
