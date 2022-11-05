package com.alexsykes.trialmonsterk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

class ResultActivity : AppCompatActivity() {

    val TAG: String = "Info"
    lateinit var trial: Trial
    private val trialViewModel: TrialViewModel by viewModels {
        TrialViewModelFactory((application as TrialApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var intent: Intent = getIntent()
        val trialid: Int = intent.getIntExtra("trialid", 0)
        trial = trialViewModel.getTrial(trialid)
    }
}