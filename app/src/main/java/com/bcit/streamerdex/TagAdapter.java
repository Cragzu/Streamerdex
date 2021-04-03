package com.bcit.streamerdex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> implements Filterable {

    private List<TagItem> tagItemList;
    private List<TagItem> tagItemListAll;

    public TagAdapter(List<TagItem> tagItemList) {
        this.tagItemList = tagItemList;
        this.tagItemListAll = new ArrayList<>(tagItemList);
    }

    class TagViewHolder extends RecyclerView.ViewHolder {
        private TextView tagNameTextView;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tagNameTextView = itemView.findViewById(R.id.textView_tagName);
        }
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View tagView = inflater.inflate(R.layout.item_tag, parent, false);
        return new TagViewHolder(tagView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        TagItem currItem = tagItemList.get(position);
        holder.tagNameTextView.setText(currItem.getName());
    }

    @Override
    public int getItemCount() {
        return tagItemList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TagItem> filteredList = new ArrayList<>();

            if (constraint.toString().isEmpty() || constraint == null) {
                filteredList.addAll(tagItemListAll);
            } else {
                for (TagItem tagItem : tagItemListAll) {
                    if (tagItem.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(tagItem);
                    }
                }
            }
            FilterResults fResults = new FilterResults();
            fResults.values = filteredList;
            return fResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            tagItemList.clear();
            tagItemList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
