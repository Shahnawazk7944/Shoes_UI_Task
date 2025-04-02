package com.example.pwuitask.presentation.features.learningModule


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.pwuitask.R
import com.example.pwuitask.presentation.features.learningModule.components.DragOptionsProgressSection
import com.example.pwuitask.presentation.features.learningModule.components.DraggableOptions
import com.example.pwuitask.presentation.features.learningModule.components.HeartSection
import com.example.pwuitask.presentation.features.learningModule.components.LearningModuleTopBar
import com.example.pwuitask.presentation.features.learningModule.components.NavigationButtons
import com.example.pwuitask.presentation.features.learningModule.viewmodels.LearningModuleScreenEvents
import com.example.pwuitask.presentation.features.learningModule.viewmodels.LearningModuleScreenStates
import com.example.pwuitask.presentation.features.learningModule.viewmodels.LearningModuleScreenViewModel

@Composable
fun LearningModuleScreenScreen(
    viewModel: LearningModuleScreenViewModel = hiltViewModel(),
    subjectId: String,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LearningModuleScreenContent(
        state = state,
        events = viewModel::learningModuleScreenEvents,
        onBackClick = { onBackClick.invoke() },
    )
}

@Composable
fun LearningModuleScreenContent(
    state: LearningModuleScreenStates,
    events: (LearningModuleScreenEvents) -> Unit,
    onBackClick: () -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.learning_screen_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LearningModuleTopBar(onBackClick)
                Spacer(modifier = Modifier.height(24.dp))
                QuizSection(
                    state = state,
                    events = events
                )

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizSection(
    state: LearningModuleScreenStates,
    events: (LearningModuleScreenEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(.9f)
            .background(Color.Black.copy(alpha = 0.3f), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Anatomy of the Human Heart",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        DragOptionsProgressSection(state = state)
        Spacer(modifier = Modifier.height(20.dp))
        Text("Fill the slots with given answers below", color = Color.White, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))
        HeartSection(events = events, state = state)
        Spacer(modifier = Modifier.height(16.dp))
        DraggableOptions(state = state)
        Spacer(modifier = Modifier.height(16.dp))
        NavigationButtons(
            state = state,
            checkAnswers = { events(LearningModuleScreenEvents.CheckAnswers) }
        )
    }
}

@Preview(
    device = "spec:width=411dp,height=1291dp,orientation=landscape",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    wallpaper = Wallpapers.NONE
)
@Preview(
    device = "spec:width=411dp,height=1291dp,orientation=landscape",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.NONE
)
@Composable
fun DashboardScreenContentHorizontalPreview() {
    var tab by remember { mutableIntStateOf(0) }
    Shoes_UI_TaskTheme {
        LearningModuleScreenContent(
            state = LearningModuleScreenStates(),
            events = {},
            onBackClick = {}
        )
    }
}


@PreviewLightDark
@Composable
fun DashboardScreenContentPreview() {
    Shoes_UI_TaskTheme {
        LearningModuleScreenContent(
            state = LearningModuleScreenStates(),
            events = {},
            onBackClick = {}
        )
    }
}
