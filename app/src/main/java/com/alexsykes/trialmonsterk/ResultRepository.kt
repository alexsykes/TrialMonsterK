package com.alexsykes.trialmonsterk

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class ResultRepository(private val resultDao: ResultDao) {
    val trialid: Int = 0

    val trialResults: Flow<List<Result>> = resultDao.getTrialDetail(trialid)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(result: Result) {
        resultDao.insert(result)
    }
}