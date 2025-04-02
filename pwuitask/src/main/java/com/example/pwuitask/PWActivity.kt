package com.example.pwuitask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.pwuitask.presentation.navGraph.PW_UI_Task_NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PWActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Shoes_UI_TaskTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    PW_UI_Task_NavGraph()
                }
            }
        }
    }
}