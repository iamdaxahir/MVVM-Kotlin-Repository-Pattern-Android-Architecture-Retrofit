package com.dashrath.kotlinrepositorypattern.di

import com.dashrath.kotlinrepositorypattern.githubusers.repository.GitUserRepository
import org.koin.dsl.module

/**
 * Used for define repository
 */
val repositoryModule = module {
    single {
        GitUserRepository(get())
    }
}