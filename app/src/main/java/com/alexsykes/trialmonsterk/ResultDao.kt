package com.alexsykes.trialmonsterk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Query("DELETE FROM results")
    suspend fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(results: Result)

    @Query("SELECT * FROM results WHERE trialid = :trialid ORDER BY dnf, course, total DESC, cleans DESC, ones DESC, twos DESC, threes DESC, scores ASC;")
    fun getTrialResults(trialid: Int): List<Result>

    @Query("SELECT * FROM results WHERE trialid = :trialid AND course = :course ORDER BY dnf, course, total DESC, cleans DESC, ones DESC, twos DESC, threes DESC, scores ASC;")
    fun getCourseResults(trialid: Int, course: String): Flow<List<Result>>
}
