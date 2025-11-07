package com.example.spesatracker.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spesatracker.R
import com.example.spesatracker.groups.adapter.GroupAdapter
import com.example.spesatracker.groups.model.Group

class HomeFragment : Fragment() {

    private lateinit var groupsRecyclerView: RecyclerView
    private lateinit var groupAdapter: GroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // **THE FIX**: Initialize the RecyclerView by finding it in the layout.
        groupsRecyclerView = view.findViewById(R.id.groups_recycler_view)

        // Now that the RecyclerView is initialized, set it up.
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // This is sample data. In a real app, you would fetch this from a database or API.
        val sampleGroups = listOf(
            Group("1", "Trip to Goa", "", 50.00),
            Group("2", "Apartment Rent", "", -20.50),
            Group("3", "Office Lunch", "", 0.0),
            Group("4", "Weekend Project", "", 15.00)
        )

        // Set the layout manager for the RecyclerView
        groupsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Create and set the adapter
        groupAdapter = GroupAdapter(sampleGroups)
        groupsRecyclerView.adapter = groupAdapter
    }
}
