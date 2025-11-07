package com.example.spesatracker.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spesatracker.R
import com.example.spesatracker.databinding.FragmentHomeBinding
import com.example.spesatracker.groups.adapter.GroupAdapter
import com.example.spesatracker.groups.model.Group
import com.example.spesatracker.groups.view.CreateGroupActivity

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    private lateinit var groupAdapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding?.addGroup?.setOnClickListener {
            val intent = Intent(requireActivity(), CreateGroupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val sampleGroups = listOf(
            Group("1", "Trip to Goa", "", 50.00),
            Group("2", "Apartment Rent", "", -20.50),
            Group("3", "Office Lunch", "", 0.0),
            Group("4", "Weekend Project", "", 15.00),
            Group("5", "Weekend Project", "", 15.00),
            Group("6", "Weekend Project", "", 15.00),
            Group("7", "Weekend Project", "", 15.00),
            Group("8", "Weekend Project", "", 15.00)
        )
        binding?.groupsRecyclerView?.layoutManager = LinearLayoutManager(context)
        groupAdapter = GroupAdapter(sampleGroups)
        binding?.groupsRecyclerView?.adapter = groupAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
