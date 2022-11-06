package com.alexsykes.trialmonsterk

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class TrialApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { TrialRoomDatabase.getDatabase(this, applicationScope) }
    val trialRepository by lazy { TrialRepository(database.trialDao()) }
}
