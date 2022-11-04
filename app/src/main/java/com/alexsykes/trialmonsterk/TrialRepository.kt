package com.alexsykes.trialmonsterk

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TrialRepository(private val trialDao: TrialDao) {

    val allTrials: Flow<List<Trial>> = trialDao.getTrialList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(trial: Trial) {
        trialDao.insert(trial)
    }
}