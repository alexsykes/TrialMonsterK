package com.alexsykes.trialmonsterk

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TrialRepository(private val trialDao: TrialDao, private val resultDao: ResultDao) {
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

    fun getTrialResults(trialid: Int) : Flow<List<Result>> {
        return resultDao.getTrialResults(trialid)
    }

    suspend fun insert(result: Result) {
        resultDao.insert(result)
    }
}