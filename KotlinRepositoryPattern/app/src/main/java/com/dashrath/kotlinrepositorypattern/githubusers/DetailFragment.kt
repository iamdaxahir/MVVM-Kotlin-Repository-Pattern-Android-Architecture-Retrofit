package com.dashrath.kotlinrepositorypattern.githubusers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.dashrath.kotlinrepositorypattern.R
import com.dashrath.kotlinrepositorypattern.databinding.FragmentDetailBinding
import com.dashrath.kotlinrepositorypattern.githubusers.model.GitUserRepoModel
import com.dashrath.kotlinrepositorypattern.githubusers.viewmodel.GithubUsersViewModel
import com.dashrath.kotlinrepositorypattern.network.APIResponse
import timber.log.Timber

private const val ARG_LOGIN = "UserLogin"


class DetailFragment : Fragment() {

    private var userLogin: String? = null

    private val viewModel: GithubUsersViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userLogin = it.getString(ARG_LOGIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        userLogin?.let {
            viewModel.getUserRepo(it)
        }

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initObserver() {
        viewModel.getUserRepoData().observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success -> {
                    binding.apiLoader.visibility = View.GONE
                    binding.consDetail.visibility = View.VISIBLE
                    binding.gitUser = it.data as GitUserRepoModel
                }
                is APIResponse.Error -> {
                    binding.apiLoader.visibility = View.GONE
                    binding.consDetail.visibility = View.VISIBLE
                    Timber.e(it.throwable)
                }
                is APIResponse.Loading -> {
                    if (it.isProgressShow) {
                        binding.apiLoader.visibility = View.VISIBLE
                        binding.consDetail.visibility = View.GONE
                    }
                }
                is APIResponse.Remove -> {

                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(userLogin: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LOGIN, userLogin)
                }
            }
    }
}