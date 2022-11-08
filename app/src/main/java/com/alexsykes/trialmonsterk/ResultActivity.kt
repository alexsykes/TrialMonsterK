package com.alexsykes.trialmonsterk
// https://www.geeksforgeeks.org/room-database-with-kotlin-coroutines-in-android/
// https://medium.com/@tonia.tkachuk/android-app-example-using-room-database-63f7091e69af
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexsykes.trialmonsterk.Result

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

        val trialid: Int = intent.getIntExtra("trialid", 0)
        val courselist: String? = intent.getStringExtra("courselist")
        val courses: List<String> = mutableListOf(*courselist!!.split(",").toTypedArray())
        val adapter: CourseListAdapter = CourseListAdapter(courses)
        val mainRV: RecyclerView = findViewById(R.id.mainRV)
        mainRV.layoutManager = LinearLayoutManager(this)
        mainRV.adapter = adapter

        for (i in 0..courses.size - 1) {
            var course = courses.get(i)
            trialViewModel.getResultsByCourse(trialid, course).observe(this) { results ->
                Log.i(TAG, "results: " + results.size)
            }
        }
    }


//    suspend fun getTrialResult(): List<Result> {
//        return trialViewModel.getCourseResults(61,"Expert")
//    }


    private fun showTrialDetails(intent: Intent) {
        val trialname: String? = intent.getStringExtra("name")
        val club: String? = intent.getStringExtra("club")
        val venue: String? = intent.getStringExtra("venue")
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