package com.alexsykes.trialmonsterk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val TAG: String = "Info"
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

        getTrialList()
    }

    public fun getTrialList() {
        val queue = Volley.newRequestQueue(this)
        val  url: String = "https://android.trialmonster.uk/getAndroidPastTrials.php"

        val stringReq = StringRequest(
            Request.Method.GET, url, { response ->
            var strResp = response.toString()
            val jsonArray: JSONArray = JSONArray(strResp)
            addTrialsToDb(jsonArray)
        },

            { Log.i(TAG, "That didn't work!") })
        queue.add(stringReq)
    }

    fun addTrialsToDb(array: JSONArray) {
        for (i in 0 until array.length()) {

            val trial: JSONObject = array.getJSONObject(i)

            val id: Int = trial.getInt("id")
            val date: String = trial.getString("date")
            val club: String = trial.getString("club")
            val trialName: String = trial.getString("name")
            val location: String = trial.getString("location")

            val newTrial: Trial = Trial(id, trialName, club,date, location)
            trialViewModel.insert(newTrial)

            Log.i(TAG, "addTrialsToDb: " + id)

        }
    }
}