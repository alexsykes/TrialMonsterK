package com.alexsykes.trialmonsterk

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class TrialRepository(private val trialDao: TrialDao, private val resultDao: ResultDao) {
    val trialid: Int = 0
    val course: String = ""
    val allTrials: Flow<List<Trial>> = trialDao.getTrialList()
    val trial: Flow<Trial> = trialDao.getTrialDetail(trialid)
    val courseResults: Flow<List<Result>> = resultDao.getCourseResults(trialid, course)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(trial: Trial) {
        trialDao.insert(trial)
    }

     fun  getTrial(trialid: Int): Flow<Trial> {
        return trialDao.getTrialDetail(trialid)
    }

    suspend fun getTrialResults(trialid: Int) : List<Result> {
        return resultDao.getTrialResults(trialid)
    }

    fun getCourseResults(trialid: Int, course: String): Flow<List<Result>> {
        return resultDao.getCourseResults(trialid, course)
    }

    fun getResultsByCourse(trialid: Int, course: String): Flow<List<Result>> {
        return resultDao.getResultsByCourse(trialid, course)
    }

    suspend fun insert(result: Result) {
        resultDao.insert(result)
    }
}