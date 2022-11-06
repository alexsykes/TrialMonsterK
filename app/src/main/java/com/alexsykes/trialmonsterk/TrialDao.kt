package com.alexsykes.trialmonsterk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrialDao {

    @Query("SELECT * FROM trials ORDER BY date DESC")
    fun getTrialList(): Flow<List<Trial>>

    @Query("SELECT * FROM trials WHERE id = :trialid")
    fun getTrialDetail(trialid: Int): Flow<Trial>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trial: Trial)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(result: Result)

    @Query("DELETE FROM trials")
    suspend fun deleteAll()
}