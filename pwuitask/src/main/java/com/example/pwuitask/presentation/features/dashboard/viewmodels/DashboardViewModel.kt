package com.example.pwuitask.presentation.features.dashboard.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DashboardScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DashboardScreenStates())
    val state = _state.asStateFlow()

    fun dashboardScreenEvents(event: DashboardScreenEvents) {
        when (event) {
            is DashboardScreenEvents.ChangeTopBarNavigationTab -> {
                _state.update {
                    it.copy(topBarSelectedTab = event.newTab)
                }
            }
        }
    }

}

