package com.alexsykes.trialmonsterk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val trialViewModel: TrialViewModel by viewModels {
        TrialViewModelFactory((application as TrialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TrialListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        trialViewModel.allTrials.observe(owner = this) { trials ->
            // Update the cached copy of the words in the adapter.
            trials.let { adapter.submitList(it) }
        }
    }
}