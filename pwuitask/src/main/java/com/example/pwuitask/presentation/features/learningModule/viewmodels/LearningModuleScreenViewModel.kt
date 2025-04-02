package com.example.pwuitask.presentation.features.learningModule.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LearningModuleScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LearningModuleScreenStates())
    val state = _state.asStateFlow()

    fun learningModuleScreenEvents(event: LearningModuleScreenEvents) {
        when (event) {
            is LearningModuleScreenEvents.OnOptionDropped -> {
                onOptionDropped(event.slot, event.option)
            }
            LearningModuleScreenEvents.CheckAnswers -> {
                checkAnswers()
            }
            LearningModuleScreenEvents.NextQuestion -> {
                nextQuestion()
            }
        }
    }

    fun onOptionDropped(slot: Char, option: String) {
        val currentState = _state.value
        if (!currentState.userAnswers.containsKey(slot)) {
            val newAnswers = currentState.userAnswers.toMutableMap()
            newAnswers[slot] = option
            val newProgress = newAnswers.size

            _state.update {
                it.copy(
                    userAnswers = newAnswers,
                    progress = newProgress
                )
            }
        }
    }

    fun checkAnswers() {
       _state.update {
            it.copy(isCheckingAnswers = !state.value.isCheckingAnswers)
        }

    }

    fun nextQuestion() {
        val currentIndex = _state.value.currentQuestionIndex
        if (currentIndex < _state.value.questions.size - 1) {
            _state.update {
                it.copy(
                    currentQuestionIndex = currentIndex + 1,
                    userAnswers = mutableMapOf(),
                    progress = 0,
                    isCheckingAnswers = false
                )
            }
        }
    }
}

