package com.alexsykes.trialmonsterk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultActivity : AppCompatActivity() {
    val TAG: String = "Info"

    private val trialViewModel: TrialViewModel by viewModels {
        TrialViewModelFactory((application as TrialApplication).trialRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var intent: Intent = getIntent()
        showTrialDetails(intent)

        val courselist: String? = intent.getStringExtra("courselist")
        val courses: List<String> = mutableListOf(*courselist!!.split(",").toTypedArray())
        val adapter: CourseListAdapter = CourseListAdapter(courses)
        val mainRV: RecyclerView = findViewById(R.id.mainRV)
        mainRV.layoutManager = LinearLayoutManager(this)
        mainRV.adapter = adapter
    }




    private fun showTrialDetails(intent: Intent) {
        val trialid: Int = intent.getIntExtra("trialid", 0)
        val trialname: String? = intent.getStringExtra("name")
        val club: String? = intent.getStringExtra("club")
        val venue: String? = intent.getStringExtra("venue")
        val triclasslistalname: String? = intent.getStringExtra("classlist")
        val courselist: String? = intent.getStringExtra("courselist")
        val date: String? = intent.getStringExtra("formatted_date")
        val updated: String? = intent.getStringExtra("updated")

        val trialNameLabel: TextView = findViewById(R.id.trialNameLabel)
        val dateLabel: TextView = findViewById(R.id.dateLabel)
        val clubLabel: TextView = findViewById(R.id.clubLabel)
        val venueLabel: TextView = findViewById(R.id.venueLabel)
        val updatedLabel: TextView = findViewById(R.id.updatedLabel)

        trialNameLabel.text = trialname
        clubLabel.text = club
        dateLabel.text = date
        venueLabel.text = venue
        updatedLabel.text = "Last updated: " + updated
    }
}