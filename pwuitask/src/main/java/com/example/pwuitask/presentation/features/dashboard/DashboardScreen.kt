package com.example.pwuitask.presentation.features.dashboard


import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.components.CustomConfirmationDialog
import com.example.designsystem.theme.Shoes_UI_TaskTheme
import com.example.pwuitask.R
import com.example.pwuitask.presentation.features.dashboard.components.DashboardOuterBoxes
import com.example.pwuitask.presentation.features.dashboard.components.DashboardTopTabBar
import com.example.pwuitask.presentation.features.dashboard.components.LeaderboardCard
import com.example.pwuitask.presentation.features.dashboard.components.MentorAndTipsSection
import com.example.pwuitask.presentation.features.dashboard.components.RecentLearningSection
import com.example.pwuitask.presentation.features.dashboard.viewmodels.DashboardScreenEvents
import com.example.pwuitask.presentation.features.dashboard.viewmodels.DashboardScreenStates
import com.example.pwuitask.presentation.features.dashboard.viewmodels.DashboardScreenViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardScreenViewModel = hiltViewModel(),
    onRouteChange: (subjectId: String) -> Unit,
) {
    val activity = LocalActivity.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showLogoutDialog by remember { mutableStateOf(false) }
    BackHandler {
        showLogoutDialog = true
    }
    DashboardScreenContent(
        state = state,
        events = viewModel::dashboardScreenEvents,
        onStartClick = { onRouteChange.invoke("NA") },
    )
    if (showLogoutDialog) {
        CustomConfirmationDialog(
            icon = Icons.AutoMirrored.Filled.Logout,
            title = "Oh no! You’re leaving...",
            message = "Are you sure? Please don't go",
            confirmButtonText = "Yes, Kick me Out",
            dismissButtonText = "Nah, Just Kidding",
            onConfirm = {
                showLogoutDialog = false
                if (activity?.isTaskRoot == true) {
                    activity.finishAndRemoveTask()
                }
            },
            onDismiss = { showLogoutDialog = false }
        )
    }
}

@Composable
fun DashboardScreenContent(
    state: DashboardScreenStates,
    events: (DashboardScreenEvents) -> Unit,
    onStartClick: () -> Unit
) {
    val tabs = listOf("Dashboard", "Pitara", "Maths", "Science", "English", "More")

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
                painter = painterResource(id = R.drawable.dashboard_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                DashboardTopTabBar(tabs = tabs, state = state, events = events)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(.65f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)

                    ) {
                        DashboardOuterBoxes()
                        MentorAndTipsSection { onStartClick.invoke() }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        LeaderboardCard()
                        RecentLearningSection()
                    }

                }
            }
        }
    }
}

@Preview(
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
    wallpaper = Wallpapers.NONE
)
@Preview(
    device = "spec:width=411dp,height=891dp,orientation=landscape",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    wallpaper = Wallpapers.NONE
)
@Composable
fun DashboardScreenContentHorizontalPreview() {
    var tab by remember { mutableIntStateOf(0) }
    Shoes_UI_TaskTheme {
        DashboardScreenContent(
            state = DashboardScreenStates(
                topBarSelectedTab = listOf(
                    "Dashboard",
                    "Pitara",
                    "Maths",
                    "Science",
                    "English",
                    "More"
                )[tab]
            ),
            events = {
                tab = if (it is DashboardScreenEvents.ChangeTopBarNavigationTab) listOf(
                    "Dashboard",
                    "Pitara",
                    "Maths",
                    "Science",
                    "English",
                    "More"
                ).indexOf(it.newTab) else 0
            },
            onStartClick = {}
        )
    }
}

@PreviewLightDark
@Composable
fun DashboardScreenContentPreview() {
    Shoes_UI_TaskTheme {
        DashboardScreenContent(
            state = DashboardScreenStates(),
            events = {},
            onStartClick = {}
        )
    }
}
