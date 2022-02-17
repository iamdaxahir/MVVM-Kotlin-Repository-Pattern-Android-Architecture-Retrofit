package com.dashrath.kotlinrepositorypattern.githubusers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.dashrath.kotlinrepositorypattern.R
import com.dashrath.kotlinrepositorypattern.databinding.FragmentGitUsersBinding
import com.dashrath.kotlinrepositorypattern.githubusers.adapter.UserListAdapter
import com.dashrath.kotlinrepositorypattern.githubusers.model.UsersResponse
import com.dashrath.kotlinrepositorypattern.githubusers.viewmodel.GithubUsersViewModel
import com.dashrath.kotlinrepositorypattern.network.APIResponse
import timber.log.Timber

class GitUsersFragment : Fragment() {

    private lateinit var binding: FragmentGitUsersBinding

    private val viewModel: GithubUsersViewModel by activityViewModels()

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_git_users, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        binding.recyclerView.adapter = userListAdapter
        viewModel.getUsers()
    }

    private fun initObserver() {
        viewModel.getUserData().observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success -> {
                    userListAdapter.submitList((it.data as UsersResponse).results)
                    viewModel.getUserData().postValue(APIResponse.Remove)

                    binding.apiLoader.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is APIResponse.Error -> {
                    binding.apiLoader.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    Timber.e(it.throwable)
                }
                is APIResponse.Loading -> {
                    if (it.isProgressShow) {
                        binding.apiLoader.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
                is APIResponse.Remove -> {

                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GitUsersFragment().apply {
            }
    }


}