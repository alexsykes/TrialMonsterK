package com.alexsykes.trialmonsterk

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception

class TrialViewModel(private val repository: TrialRepository) : ViewModel() {
    val trialid: Int = 0
    val allTrials: LiveData<List<Trial>> = repository.allTrials.asLiveData()
//    val courseResults: LiveData<List<Result>> = repository.getCourseResults(
//        trialid = 61,
//        course = "Expert"
//    ).asLiveData()
    val trial: Flow<Trial> = repository.getTrial(trialid)

    fun insert(trial: Trial) = viewModelScope.launch {
        repository.insert(trial)
    }

    fun insert(result: Result) = viewModelScope.launch {
        repository.insert(result)
    }

    fun getCourseResults(trialid: Int, course: String): LiveData<List<Result>> {
        return repository.getCourseResults(trialid, course).asLiveData()
    }

    fun getResultsByCourse(trialid: Int, course: String): LiveData<List<Result>> {
        return repository.getResultsByCourse(trialid, course).asLiveData()
    }
}

class TrialViewModelFactory(private  val repository: TrialRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrialViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrialViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}