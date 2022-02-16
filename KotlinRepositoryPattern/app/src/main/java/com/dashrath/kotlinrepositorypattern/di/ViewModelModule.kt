package com.dashrath.kotlinrepositorypattern.di


import com.dashrath.kotlinrepositorypattern.githubusers.viewmodel.GithubUsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Used for define viewmodel object.
 */
val viewModelModule = module {
    viewModel {
        GithubUsersViewModel(get())
    }
}