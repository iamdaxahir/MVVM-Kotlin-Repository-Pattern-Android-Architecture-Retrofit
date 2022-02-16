package com.dashrath.kotlinrepositorypattern.network

import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserModel
import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserRepoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    fun getGitUserList(): Call<List<GitUserModel>>

    @GET("users/{username}")
    fun getRepositoryDetail(@Path("username") userLogin: String): Call<GitUserRepoModel>
}