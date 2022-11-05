package com.alexsykes.trialmonsterk

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MainActivity : AppCompatActivity() {
    val TAG: String = "Info"
    var prefs: SharedPreferences? = null
    private val trialViewModel: TrialViewModel by viewModels {
        TrialViewModelFactory((application as TrialApplication).repository)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            val timestamp = LocalDateTime.now()
//            val formatter = DateTimeFormatter.RFC_1123_DATE_TIME
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = timestamp.format(formatter)
//            putLong("Lastrefreshed", )
            putString("Refreshed", formatted)
            apply()
        }
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
            val formatted_date: String = trial.getString("formatted_date")

            val newTrial: Trial = Trial(id, trialName, club,date, location,formatted_date )
            trialViewModel.insert(newTrial)

            Log.i(TAG, "addTrialsToDb: " + id)

        }
    }
}