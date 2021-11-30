package com.osmanboy.oppoproducts.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.osmanboy.oppoproducts.R
import com.osmanboy.oppoproducts.data.OPPOREPOSITORY
import com.osmanboy.oppoproducts.databinding.FragmentHomeBinding
import com.osmanboy.oppoproducts.ui.ShowInfo
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var adapter: OppoAdapter
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val opporepository by lazy {
        OPPOREPOSITORY(application = requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.text.observe(viewLifecycleOwner) {
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            Log.d("TAG","isCalled")
            val list = opporepository.getShopList()
            Log.d("TAG",list.toString())

            adapter= OppoAdapter(requireContext(),list)
            adapter.onClick = {
                parentFragmentManager.beginTransaction().replace(R.id
                    .nav_host_fragment_activity_main,ShowInfo.newInstance(it.name,it.price,it
                    .description,it.imageUri)).commit()
            }
            binding.recyclerView.adapter = adapter

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}