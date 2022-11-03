package com.alexsykes.trialmonsterk

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Trial::class), version = 1, exportSchema = false)
public abstract class TrialRoomDatabase : RoomDatabase() {
    abstract fun trialDao(): TrialDao

    companion object {
        @Volatile private var INSTANCE: TrialRoomDatabase? = null

        fun getDatabase(context: Context): TrialRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                    TrialRoomDatabase::class.java,
                    "trial_database"
                        ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}