package com.alexsykes.trialmonsterk

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface TrialDao {

    @Query("SELECT * FROM trials ORDER BY date DESC")
    suspend fun getTrialList(): Flow<List<Trial>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(trial: Trial)

    @Query("DELETE FROM trials")
    suspend fun deleteAll()

}