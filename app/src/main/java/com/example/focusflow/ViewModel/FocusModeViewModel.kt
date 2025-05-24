package com.example.focusflow.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.focusflow.Model.FocusModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FocusModeViewModel : ViewModel() {
    private val _focusList = MutableStateFlow<List<FocusModel>>(emptyList())
    val focusList: StateFlow<List<FocusModel>> = _focusList.asStateFlow()

    init {
        // Simulasi data (nanti ganti ambil dari database)
        viewModelScope.launch {
            _focusList.value = listOf(
                FocusModel(1, "Study", "Study", "Math", 25, 5, true),
                FocusModel(2, "Work", "Work", "Emails", 30, 10, false),
                FocusModel(3, "Read", "Study", "Novel", 20, 5, true)
            )
        }
    }

    val usedCount: Int
        get() = _focusList.value.size

    val doneCount: Int
        get() = _focusList.value.count { it.isCompleted }
}