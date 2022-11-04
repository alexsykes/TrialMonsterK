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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trial: Trial)

    @Query("DELETE FROM trials")
    suspend fun deleteAll()

}