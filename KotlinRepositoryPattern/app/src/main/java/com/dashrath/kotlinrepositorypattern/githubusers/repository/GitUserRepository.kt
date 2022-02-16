package com.dashrath.kotlinrepositorypattern.githubusers.repository

import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserModel
import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserRepoModel
import com.dashrath.kotlinrepositorypattern.githubusers.model.UsersResponse
import com.dashrath.kotlinrepositorypattern.network.APIResponse
import com.dashrath.kotlinrepositorypattern.network.ApiService
import retrofit2.await

class GitUserRepository(private val apiService: ApiService) {

    suspend fun getUser(): APIResponse<*> {

        kotlin.runCatching {
            apiService.getGitUserList().await()
        }.onSuccess {
            val users = ArrayList<GitUserModel>()
            users.addAll(it)
            val usersResponse = UsersResponse(users)
            return APIResponse.Success(usersResponse)
        }.onFailure {
            return APIResponse.Error<Throwable>(it)
        }
        return APIResponse.Loading<Boolean>(true)
    }

    suspend fun getUserRepo(userLogin: String): APIResponse<*> {

        kotlin.runCatching {
            apiService.getRepositoryDetail(userLogin).await()
        }.onSuccess {
            return APIResponse.Success(it)
        }.onFailure {
            return APIResponse.Error<Throwable>(it)
        }
        return APIResponse.Loading<Boolean>(true)
    }
}