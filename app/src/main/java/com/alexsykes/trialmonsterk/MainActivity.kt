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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    val TAG: String = "Info"
    var prefs: SharedPreferences? = null
    private val trialViewModel: TrialViewModel by viewModels {
        TrialViewModelFactory((application as TrialApplication).trialRepository)
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

        getResultList()
        prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            val timestamp = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = timestamp.format(formatter)
            putString("Refreshed", formatted)
            apply()
        }
    }

    public fun getTrialList() {
        val queue = Volley.newRequestQueue(this)
        val  url: String = "https://android.trialmonster.uk/getTrialListKotlin.php"

        val stringReq = StringRequest(
            Request.Method.GET, url, { response ->
                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                addTrialsToDb(jsonArray)
            },

            { Log.i(TAG, "That didn't work!") })
        queue.add(stringReq)
    }



    private fun getResultList() {
        val queue = Volley.newRequestQueue(this)
        val  url: String = "https://android.trialmonster.uk/getResultListKotlin.php"

        val stringReq = StringRequest(
            Request.Method.GET, url, { response ->
                var strResp = response.toString()
                val jsonArray: JSONArray = JSONArray(strResp)
                addResultsToDb(jsonArray)
            },

            { Log.i(TAG, "That didn't work!") })
        queue.add(stringReq)
    }

    private fun addResultsToDb(array: JSONArray) {
        for (i in 0 until array.length()) {

            val result: JSONObject = array.getJSONObject(i)

            val id: Int = result.getInt("id")
//            Log.i(TAG,"id: " + id )
            val trialid: Int = result.getInt("trialid")
            val rider: String = result.getString("rider")
            val course: String = result.getString("course")
            val name: String = result.getString("name")
            val classs: String = result.getString("class")
            val machine: String = result.getString("machine")
            val total: Int = result.getInt("total")
            val cleans: Int = result.getInt("cleans")
            val ones: Int = result.getInt("ones")
            val twos: Int = result.getInt("twos")
            val threes: Int = result.getInt("threes")
            val fives: Int = result.getInt("fives")
            val missed: Int = result.getInt("missed")
            val dnf: Int = result.getInt("dnf")
            val scores: String = result.getString("scores")
            val sectionscores: String = result.getString("sectionscores")
            val created: String = result.getString("created")
            val modified: String = result.getString("modified")

            val newResult: Result = Result(id, trialid, rider, course, name, classs, machine, total, cleans, ones, twos, threes, fives, missed,
            dnf, scores, sectionscores, created, modified)
            trialViewModel.insert(newResult)

//            Log.i(TAG, "addResultsToDb: " + id)
        }
    }

    fun addTrialsToDb(array: JSONArray) {
        for (i in 0 until array.length()) {

            val trial: JSONObject = array.getJSONObject(i)

            val id: Int = trial.getInt("id")
            val date: String = trial.getString("date")
            val club: String = trial.getString("club")
            val trialName: String = trial.getString("name")
            val location: String = trial.getString("location")
            val classlist: String = trial.getString("classlist")
            val courselist: String = trial.getString("courselist")
            val formatted_date: String = trial.getString("formatted_date")
            val updated: String = trial.getString("updated")

            val newTrial: Trial = Trial(id, trialName, club,date, location, classlist, courselist, formatted_date, updated )
            trialViewModel.insert(newTrial)
//
//            Log.i(TAG, "addTrialsToDb: " + id)

        }
    }
}