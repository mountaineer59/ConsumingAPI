package com.soropromo.myapiapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soropromo.myapiapp.R;
import com.soropromo.myapiapp.model.Repo;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<Repo> repos;
    private int rowLayout;
    private Context context;

    public ReposAdapter(List<Repo> repos, int rowLayout, Context context) {
        this.setRepos(repos);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class ReposViewHolder extends RecyclerView.ViewHolder {
        LinearLayout reposLayout;
        TextView repoName;
        TextView repoDescription;
        TextView repolanguage;


        public ReposViewHolder(View v) {
            super(v);
            reposLayout = (LinearLayout) v.findViewById(R.id.repo_item_layout);
            repoName = (TextView) v.findViewById(R.id.repoName);
            repoDescription = (TextView) v.findViewById(R.id.repoDescription);
            repolanguage = (TextView) v.findViewById(R.id.repoLanguage);
        }
    }

    @NonNull
    @Override
    public ReposAdapter.ReposViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ReposViewHolder holder, int position) {
        holder.repoName.setText(repos.get(position).getName());
        if (repos.get(position).getDescription() != null)
            holder.repoDescription.setText(repos.get(position).getDescription());
        else
            holder.repoDescription.setText("No description provided");

            holder.repolanguage.setText(repos.get(position).getLanguage());
    }


    @Override
    public int getItemCount() {
        return repos.size();
    }
}
