package com.xapo.github.samplegithub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thefinestartist.finestwebview.FinestWebView;
import com.xapo.github.samplegithub.R;
import com.xapo.github.samplegithub.models.RepoListResponse;

import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    List<RepoListResponse.Items> itemsList;
    Context context;

    public RepoListAdapter(List<RepoListResponse.Items> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final RepoListResponse.Items items = itemsList.get(position);

        holder.textView_repo_title.setText(items.getName());
        holder.textView_repo_description.setText(items.getFullName());
        holder.textView_repo_watchers.setText(""+items.getWatchers());
        holder.textView_repo_language.setText(items.getLanguage());
        Glide.with(context).load(items.getOwner().getAvatarUrl()).into(holder.imageView_avatar);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FinestWebView.Builder(context).show(items.getHtmlUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_avatar;
        TextView textView_repo_title;
        TextView textView_repo_watchers;
        TextView textView_repo_description;
        TextView textView_repo_language;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_avatar = itemView.findViewById(R.id.imageView_avatar);
            textView_repo_title = itemView.findViewById(R.id.textView_repo_title);
            textView_repo_description = itemView.findViewById(R.id.textView_repo_description);
            textView_repo_watchers = itemView.findViewById(R.id.textView_repo_watchers);
            textView_repo_language = itemView.findViewById(R.id.textView_repo_language);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
