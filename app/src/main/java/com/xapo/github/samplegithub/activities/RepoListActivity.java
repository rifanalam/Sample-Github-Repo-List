package com.xapo.github.samplegithub.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.xapo.github.samplegithub.R;
import com.xapo.github.samplegithub.adapter.RepoListAdapter;
import com.xapo.github.samplegithub.api.ApiInterface;
import com.xapo.github.samplegithub.api.RetrofitClient;
import com.xapo.github.samplegithub.models.RepoListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RepoListAdapter repoListAdapter;
    List<RepoListResponse.Items> itemsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);

        recyclerView = findViewById(R.id.recyclerView);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        getRepoList(1);


    }

    private void getRepoList(int n){
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<RepoListResponse> call = apiInterface.getRepoList(n);
        call.enqueue(new Callback<RepoListResponse>() {
            @Override
            public void onResponse(Call<RepoListResponse> call, Response<RepoListResponse> response) {
                repoListAdapter= new RepoListAdapter(response.body().getItems(),RepoListActivity.this);
                recyclerView.setAdapter(repoListAdapter);
            }

            @Override
            public void onFailure(Call<RepoListResponse> call, Throwable t) {

            }
        });
    }
}
