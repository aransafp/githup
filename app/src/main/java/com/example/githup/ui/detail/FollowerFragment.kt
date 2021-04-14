package com.example.githup.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githup.databinding.FollowerFragmentBinding
import com.example.githup.ui.detail.adapter.FollowerListAdapter

class FollowerFragment : Fragment() {

    companion object {
        const val ARGS_USERNAME = "username"
    }

    private var _binding: FollowerFragmentBinding? = null
    private lateinit var adapter: FollowerListAdapter
    private lateinit var viewModel: FollowerViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FollowerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARGS_USERNAME) as String

        adapter = FollowerListAdapter()
        adapter.notifyDataSetChanged()

        binding.rvListFollower.layoutManager = LinearLayoutManager(context)
        binding.rvListFollower.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowerViewModel::class.java)

        viewModel.setListFollowers(username)

        viewModel.getListFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it)
            }
        })

        viewModel.isLoading().observe(viewLifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}