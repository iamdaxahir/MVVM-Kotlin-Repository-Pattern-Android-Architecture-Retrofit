package com.dashrath.kotlinrepositorypattern.githubusers

import android.os.Bundle
import com.dashrath.kotlinrepositorypattern.R
import com.dashrath.kotlinrepositorypattern.base.BaseActivity
import com.dashrath.kotlinrepositorypattern.databinding.ActivityGithubUsersBinding
import com.dashrath.kotlinrepositorypattern.githubusers.adapter.UserListAdapter
import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserModel
import com.dashrath.kotlinrepositorypattern.githubusers.viewmodel.GithubUsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
class GithubUsersActivity : BaseActivity<GithubUsersViewModel, ActivityGithubUsersBinding>(),
    UserListAdapter.UserItemClick {

    override val layout: Int = R.layout.activity_github_users
    override val viewModel: GithubUsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GitUsersFragment.newInstance())
                .commit()
        }
    }

    override fun onItemClick(gitUserModel: GitUserModel) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_up,
                R.anim.slide_down,
                R.anim.slide_up,
                R.anim.slide_down
            )
            .add(R.id.container, DetailFragment.newInstance(gitUserModel.login ?: ""))
            .addToBackStack(DetailFragment::class.java.name)
            .commit()
    }


}