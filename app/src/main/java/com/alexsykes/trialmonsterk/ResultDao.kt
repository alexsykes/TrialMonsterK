package com.alexsykes.trialmonsterk

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ResultDao {

    @Query("DELETE FROM results")
    suspend fun deleteAll()
}
