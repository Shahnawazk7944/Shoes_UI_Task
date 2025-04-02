package com.example.pwuitask.presentation.features.learningModule.viewmodels

val QuizQuestion = listOf(
    Question(
        id = 1,
        text = "Anatomy of the Heart",
        options = mutableMapOf(
            'A' to "Right Atrium",
            'B' to "Left Ventricle",
            'C' to "Aorta",
            'D' to "Superior Vena Cava",
            'E' to "Right Ventricle"
        ),
        correctAnswers = mapOf(
            'A' to "Superior Vena Cava",
            'B' to "Right Atrium",
            'C' to "Right Ventricle",
            'D' to "Left Ventricle",
            'E' to "Aorta"
        )
    ),
)

data class LearningModuleScreenStates(
    val questions: List<Question> = QuizQuestion,
    val currentQuestionIndex: Int = 0,
    val userAnswers: MutableMap<Char, String> = mutableMapOf(),
    val progress: Int = 0,
    val isCheckingAnswers: Boolean = false
)

data class Question(
    val id: Int,
    val text: String,
    val options: MutableMap<Char, String>,
    val correctAnswers: Map<Char, String>
)

sealed class LearningModuleScreenEvents {
    data object NextQuestion : LearningModuleScreenEvents()
    data object CheckAnswers : LearningModuleScreenEvents()
    data class OnOptionDropped(val slot: Char, val option: String) : LearningModuleScreenEvents()
}