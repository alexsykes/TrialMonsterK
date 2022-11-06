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

    @Query("SELECT * FROM results WHERE trialid = :trialid")
    fun getTrialDetail(trialid: Int): Flow<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(results: Result)

//    @MapInfo(keyColumn = "layername", valueColumn = "placename")
//    @Query("SELECT layers.layername AS layername, layers.isVisible AS isVisible, markers.* FROM layers JOIN markers ON layers.layername = markers.layer_id WHERE markers.isArchived = 0 ORDER BY layername, placename")
//    fun getMarkersByLayer(): Map<String?, List<MMarker?>?>?
}
