package com.alexsykes.trialmonsterk

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TrialViewModel(private val repository: TrialRepository) : ViewModel() {

    val allTrials: LiveData<List<Trial>> = repository.allTrials.asLiveData()

    fun insert(trial: Trial) = viewModelScope.launch {
        repository.insert(trial)
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