package com.alexsykes.trialmonsterk

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TrialRepository(private val trialDao: TrialDao) {
    val trialid: Int = 0
    val allTrials: Flow<List<Trial>> = trialDao.getTrialList()
    val trial: Flow<Trial> = trialDao.getTrialDetail(trialid)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(trial: Trial) {
        trialDao.insert(trial)
    }

     fun  getTrial(trialid: Int): Flow<Trial> {
        return trialDao.getTrialDetail(trialid)
    }

    suspend fun insert(result: Result) {
        trialDao.insert(result)
    }
}