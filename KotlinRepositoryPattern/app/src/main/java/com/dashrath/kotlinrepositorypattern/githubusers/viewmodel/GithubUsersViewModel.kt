package com.dashrath.kotlinrepositorypattern.githubusers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dashrath.kotlinrepositorypattern.base.BaseVM
import com.dashrath.kotlinrepositorypattern.githubusers.repository.GitUserRepository
import com.dashrath.kotlinrepositorypattern.network.APIResponse
import kotlinx.coroutines.launch


class GithubUsersViewModel(private val repository: GitUserRepository) : BaseVM() {

    private val userLiveData = MutableLiveData<APIResponse<*>>()
    private val userRepoLiveData = MutableLiveData<APIResponse<*>>()

    fun getUserData() = userLiveData
    fun getUserRepoData() = userRepoLiveData

    fun getUsers() {
        viewModelScope.launch {
            userLiveData.postValue(APIResponse.Loading<Boolean>(true))
            userLiveData.postValue(repository.getUser())
        }
    }

    fun getUserRepo(userLogin: String) {
        viewModelScope.launch {
            userRepoLiveData.postValue(APIResponse.Loading<Boolean>(true))
            userRepoLiveData.postValue(repository.getUserRepo(userLogin))
        }
    }

}