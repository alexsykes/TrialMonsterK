package com.alexsykes.trialmonsterk

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alexsykes.trialmonsterk.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = arrayOf(Trial::class, Result::class), version = 1, exportSchema = false)
public abstract class TrialRoomDatabase : RoomDatabase() {
    abstract fun trialDao(): TrialDao
    abstract fun resultDao(): ResultDao

    private class TrialDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                    database ->
                scope.launch {
                    populateDatabase(database.trialDao(), database.resultDao())
                }
            }
        }

        suspend fun populateDatabase(trialDao: TrialDao, resultDao: ResultDao) {
            trialDao.deleteAll()
            resultDao.deleteAll()
        }
    }

    companion object {
        @Volatile private var INSTANCE: TrialRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TrialRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrialRoomDatabase::class.java,
                    "trial_database"
                )
                    .addCallback(TrialDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}