package com.bcit.streamerdex;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StreamCardAdapter extends RecyclerView.Adapter<StreamCardAdapter.StreamCardHolder> {

    private List<Stream> streamList;
    public StreamCardAdapter(List<Stream> streamList) {
        this.streamList = streamList;
    }

    @NonNull
    @Override
    public StreamCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View streamView = inflater.inflate(R.layout.stream_card_item, parent, false);

        return new StreamCardHolder(streamView);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamCardHolder holder, int position) {
        TextView streamerName = holder.streamerName;
        TextView streamTitle = holder.streamTitle;
        TextView description = holder.description;
        TextView streamTags = holder.streamTags;
        WebView streamView = holder.streamView;
        Button viewStreamOnTwitch = holder.viewStreamOnTwitch;

        Stream stream = streamList.get(position);
        ArrayList<String> tags = stream.getTags();
        String tagsString = String.join(", ", tags);

        streamerName.setText(stream.getStreamerName());
        streamTitle.setText(stream.getStreamTitle());
        description.setText(stream.getStreamDescription());
        streamTags.setText(tagsString);

        viewStreamOnTwitch.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(stream.getStreamLink()));
            v.getContext().startActivity(intent);
        });

        streamView.setInitialScale(1);
        WebSettings webSettings = streamView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        streamView.loadUrl(stream.getVideoLink());
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }

    public class StreamCardHolder extends RecyclerView.ViewHolder {
        TextView streamerName;
        TextView streamTitle;
        TextView description;
        WebView streamView;
        TextView streamTags;
        Button viewStreamOnTwitch;

        public StreamCardHolder(@NonNull View itemView) {
            super(itemView);

            streamerName = itemView.findViewById(R.id.textView_StreamerName);
            streamTitle = itemView.findViewById(R.id.textView_StreamName);
            description = itemView.findViewById(R.id.textView_StreamDesc);
            streamView = itemView.findViewById(R.id.stream_view);
            streamTags = itemView.findViewById(R.id.textView_TagsList);
            viewStreamOnTwitch = itemView.findViewById(R.id.button_GoToStreamOnTwitch);

        }
    }
}

