package com.alexsykes.trialmonsterk

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


    class ResultApplication: Application() {
        val applicationScope = CoroutineScope(SupervisorJob())

        val database by lazy { TrialRoomDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { ResultRepository(database.resultDao()) }
    }

