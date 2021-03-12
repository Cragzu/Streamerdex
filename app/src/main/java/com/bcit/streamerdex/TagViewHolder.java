package com.bcit.streamerdex;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagViewHolder extends RecyclerView.ViewHolder {
    // BM - moving into TagAdapter having hard time getting this working separately

    public TextView tagNameTextView;

    public TagViewHolder(@NonNull View itemView) {
        super(itemView);
        tagNameTextView = itemView.findViewById(R.id.textView_tagName);
    }

}
