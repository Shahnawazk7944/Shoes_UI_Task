package com.example.pwuitask.presentation.features.dashboard.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pwuitask.R
import com.example.pwuitask.presentation.features.dashboard.viewmodels.DashboardScreenEvents
import com.example.pwuitask.presentation.features.dashboard.viewmodels.DashboardScreenStates

@Composable
fun DashboardOuterBoxes() {
    val leftBox = Brush.linearGradient(
        colors = listOf(Color(0xFF5A61FF), Color(0xFF9B30FF)),
        start = Offset.Zero,
        end = Offset(600f, 0f)
    )

    val rightBox = Brush.linearGradient(
        colors = listOf(Color(0xFF1B1464), Color(0xFF161D59)),
        start = Offset.Zero,
        end = Offset(500f, 0f)
    )

    Box(
        modifier = Modifier
            .height(250.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxHeight(.85f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(leftBox)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Hello Jasmeet",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Class 10th",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Right Box
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxHeight()
                .fillMaxWidth(.65f)
                .clip(RoundedCornerShape(20.dp))
                .background(rightBox)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Gifts row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(12.dp, 12.dp, 12.dp, 8.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.White.copy(.2f))
                ) {
                    Text(
                        text = "You Have 2 Gifts",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_gift),
                        contentDescription = "Gift Icon",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 12.dp)
                    )
                }

                // Performance and Achievements row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(12.dp, 8.dp, 12.dp, 12.dp)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    val performanceBackground = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF161D59),
                            Color(0xFF1B1464),
                            Color.White.copy(alpha = 0.2f)
                        ),
                        start = Offset.Zero,
                        end = Offset(500f, 330f)
                    )
                    // My Performance Box
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(.2f))
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(performanceBackground, RoundedCornerShape(8.dp)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(12.dp)
                                        .background(
                                            Color(0xFF686AF6),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .align(Alignment.CenterVertically)
                                )

                                Text(
                                    text = "My Performance",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "223",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Lectures\nAttempted",
                                        fontSize = 11.sp,
                                        lineHeight = 16.sp,
                                        color = Color.White.copy(alpha = 0.7f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = "/",
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.7f),
                                    textAlign = TextAlign.Center
                                )
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "223",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Dpp\nAttempted",
                                        fontSize = 11.sp,
                                        lineHeight = 16.sp,
                                        color = Color.White.copy(alpha = 0.7f),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }


                    // My Achievements Box
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = Color.White.copy(.2f))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(performanceBackground, RoundedCornerShape(8.dp)),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(12.dp)
                                        .background(
                                            Color(0xFF63C9F1),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .align(Alignment.CenterVertically)
                                )

                                Text(
                                    text = "My Achievements",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .background(Color(0xFFFFD700), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "MD",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "1 Medal",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .background(Color(0xFFFFD700), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "XP",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "200 XP",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Profile avatar positioned over both boxes
        Image(
            painter = painterResource(id = R.drawable.profile_avatar),
            contentDescription = "Profile Avatar",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 80.dp)
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun LeaderboardCard(
    level: String = "12/100",
    rank: String = "5/200"
) {
    Box(
        modifier = Modifier
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(Color(0xFF7B1FA2), Color(0xFF9C27B0))))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = "Leaderboard",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                StatBox(level, "Level", R.drawable.ic_chart)
                Spacer(modifier = Modifier.width(8.dp))
                StatBox(rank, "Rank", R.drawable.ic_medal2)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.CenterEnd

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trophy_icon),
                        contentDescription = "Trophy",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun StatBox(value: String, label: String, iconRes: Int) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black.copy(alpha = 0.2f))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Column {
                Text(
                    text = value,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = label,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )


            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
fun MentorAndTipsSection(
    onStartClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Mentor Connect Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF5A79F8),
                            Color(0xFF2A1E6C)
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Mentor Connect",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_video),
                            contentDescription = "Trophy",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            text = "Connect with your mentor on the go.",
                            color = Color.White,
                            lineHeight = 20.sp,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = { onStartClick.invoke() },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = "Start Now",
                                fontWeight = FontWeight.SemiBold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 14.sp,
                                color = Color.White,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Arrow",
                            tint = Color.White
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .leftBorderShadow()
                        .padding(16.dp)
                ) {


                    // Tips items
                    Column(
                    ) {
                        Text(
                            text = "Tips & Motivation",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TipItem("Complete 5 minutes of mindfulness for 2 XP")
                        TipItem("Lorem Ipsum is simply dummy text of the...")
                        TipItem("Lorem Ipsum is simply dummy text of the...")
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalGraphicsApi::class)
fun Modifier.leftBorderShadow(
    color: Color = Color.Black.copy(alpha = 0.3f),
    shadowWidth: Dp = 8.dp,
    cornerRadius: Dp = 20.dp,
    blurRadius: Dp = 16.dp
) = this
    .graphicsLayer {
        compositingStrategy = CompositingStrategy.Offscreen
    }
    .drawBehind {
        val shadowWidthPx = shadowWidth.toPx()
        val cornerRadiusPx = cornerRadius.toPx()
        val blurRadiusPx = blurRadius.toPx()

        val paint = Paint().apply {
            this.color = color
            this.style = PaintingStyle.Stroke
            this.strokeWidth = shadowWidthPx
            this.asFrameworkPaint().maskFilter = BlurMaskFilter(
                blurRadiusPx,
                BlurMaskFilter.Blur.NORMAL
            )
        }

        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        left = -shadowWidthPx,
                        top = 0f,
                        right = 0f,
                        bottom = size.height
                    ),
                    cornerRadius = CornerRadius(cornerRadiusPx)
                )
            )
        }

        drawIntoCanvas { canvas ->
            canvas.drawPath(path, paint)
        }
    }

@Composable
fun TipItem(text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 3.dp, horizontal = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
        Text(
            text = text,
            color = Color.White,
            lineHeight = 18.sp,
            fontSize = 12.sp
        )
    }

}

@Composable
fun RecentLearningSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C364D).copy(alpha = .6f))
            .padding(16.dp)
    ) {
        Text(
            text = "Recent Learning",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )


        Spacer(modifier = Modifier.height(8.dp))

        val recentLessons = listOf(
            LearningItem(
                "System of Linear Equations",
                "Math Lecture - Linear Equations",
                R.drawable.ic_video
            ),
            LearningItem("Algebra", "Math Lecture - Linear Equations", R.drawable.ic_pdf),
            LearningItem("Algebra", "Math Lecture - Linear Equations", R.drawable.ic_pdf),
            LearningItem("Algebra", "Math Lecture - Linear Equations", R.drawable.ic_video)
        )

        recentLessons.forEach { lesson ->
            RecentLearningCard(lesson)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecentLearningCard(lesson: LearningItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black.copy(alpha = .4f))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = lesson.iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(20.dp)
                .weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(4f)) {
            Text(
                text = lesson.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = lesson.subtitle,
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.White.copy(alpha = .6f),
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp)
                    .padding(4.dp)
            )
        }
    }
}

data class LearningItem(val title: String, val subtitle: String, val iconRes: Int)

@Composable
fun DashboardTopTabBar(
    tabs: List<String>,
    state: DashboardScreenStates,
    events: (DashboardScreenEvents) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.5f)),
            color = Color.Transparent
        ) {
            LazyRow(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(tabs) { tab ->
                    Box(
                        modifier = Modifier
                            .clickable {
                                events(DashboardScreenEvents.ChangeTopBarNavigationTab(tab))
                            }
                            .background(
                                if (state.topBarSelectedTab == tab) Color.Black else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = tab,
                            color = if (state.topBarSelectedTab == tab) Color.White else Color.Gray,
                            fontWeight = if (state.topBarSelectedTab == tab) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
