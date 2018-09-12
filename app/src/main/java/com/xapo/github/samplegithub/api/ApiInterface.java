package com.xapo.github.samplegithub.api;

import com.xapo.github.samplegithub.models.RepoListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=50")
    Call<RepoListResponse> getRepoList(@Query("page") int n);
}
