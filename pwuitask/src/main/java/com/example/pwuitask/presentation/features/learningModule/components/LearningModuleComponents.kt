package com.example.pwuitask.presentation.features.learningModule.components

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pwuitask.R
import com.example.pwuitask.presentation.features.learningModule.viewmodels.LearningModuleScreenEvents
import com.example.pwuitask.presentation.features.learningModule.viewmodels.LearningModuleScreenStates

@Composable
fun DragOptionsProgressSection(
    state: LearningModuleScreenStates
) {
    LazyRow(verticalAlignment = Alignment.CenterVertically) {
        itemsIndexed(
            items = state.questions[state.currentQuestionIndex].options.keys.toList(),
            key = { index, _ -> index }) { index, slot ->

            val userAnswer = state.userAnswers[slot]
            val correctAnswer = state.questions[state.currentQuestionIndex].correctAnswers[slot]

            val backgroundColor = when {
                !state.isCheckingAnswers -> Color.White.copy(.2f)
                userAnswer == correctAnswer -> Color.Green.copy(0.5f)
                else -> Color.Red.copy(0.5f)
            }
            Box(
                modifier = Modifier
                    .background(backgroundColor, shape = CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.question_icon),
                    contentDescription = "Step $index",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(25.dp)
                )
            }
            if (index < state.questions[state.currentQuestionIndex].options.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier.width(60.dp),
                    thickness = 3.dp,
                    color = if (index < state.progress) Color.Green else Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeartSection(
    events: (LearningModuleScreenEvents) -> Unit,
    state: LearningModuleScreenStates
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        val heartSize = 200.dp
        // Heart Image
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = "Heart Diagram",
            modifier = Modifier
                .size(heartSize)
                .align(Alignment.Center)
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 120.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp)
                    .dragAndDropTarget(
                        shouldStartDragAndDrop = { event ->
                            event
                                .mimeTypes()
                                .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        },
                        target = remember {
                            object : DragAndDropTarget {
                                override fun onDrop(event: DragAndDropEvent): Boolean {
                                    val option = event.toAndroidDragEvent().clipData
                                        ?.getItemAt(0)?.text
                                    events(
                                        LearningModuleScreenEvents.OnOptionDropped(
                                            slot = 'A',
                                            option = option.toString()
                                        )
                                    )
                                    return true
                                }
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    state.userAnswers['A'] ?: "",
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 4.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.White.copy(.5f)),
                contentAlignment = Alignment.Center
            ) { Text("A", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.width(2.dp))
            HorizontalDivider(
                modifier = Modifier
                    .width(150.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp),
                thickness = 0.dp,
                color = Color.Transparent
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 120.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp)
                    .dragAndDropTarget(
                        shouldStartDragAndDrop = { event ->
                            event
                                .mimeTypes()
                                .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        },
                        target = remember {
                            object : DragAndDropTarget {
                                override fun onDrop(event: DragAndDropEvent): Boolean {
                                    val option = event.toAndroidDragEvent().clipData
                                        ?.getItemAt(0)?.text
                                    events(
                                        LearningModuleScreenEvents.OnOptionDropped(
                                            slot = 'B',
                                            option = option.toString()
                                        )
                                    )
                                    return true
                                }
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    state.userAnswers['B'] ?: "",
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 4.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.White.copy(.5f)),
                contentAlignment = Alignment.Center
            ) { Text("B", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.width(2.dp))
            HorizontalDivider(
                modifier = Modifier
                    .width(150.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp),
                thickness = 0.dp,
                color = Color.Transparent
            )
        }

        Row(
            modifier = Modifier.align(Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 120.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp)
                    .dragAndDropTarget(
                        shouldStartDragAndDrop = { event ->
                            event
                                .mimeTypes()
                                .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        },
                        target = remember {
                            object : DragAndDropTarget {
                                override fun onDrop(event: DragAndDropEvent): Boolean {
                                    val option = event.toAndroidDragEvent().clipData
                                        ?.getItemAt(0)?.text
                                    events(
                                        LearningModuleScreenEvents.OnOptionDropped(
                                            slot = 'C',
                                            option = option.toString()
                                        )
                                    )
                                    return true
                                }
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    state.userAnswers['C'] ?: "",
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 4.dp)
                )
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.White.copy(.5f)),
                contentAlignment = Alignment.Center
            ) { Text("C", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.width(2.dp))
            HorizontalDivider(
                modifier = Modifier
                    .width(150.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp),
                thickness = 0.dp,
                color = Color.Transparent
            )
        }

        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .width(150.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp),
                thickness = 0.dp,
                color = Color.Transparent
            )
            Spacer(Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.White.copy(.5f)),
                contentAlignment = Alignment.Center
            ) { Text("D", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 120.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp)
                    .dragAndDropTarget(
                        shouldStartDragAndDrop = { event ->
                            event
                                .mimeTypes()
                                .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        },
                        target = remember {
                            object : DragAndDropTarget {
                                override fun onDrop(event: DragAndDropEvent): Boolean {
                                    val option = event.toAndroidDragEvent().clipData
                                        ?.getItemAt(0)?.text
                                    events(
                                        LearningModuleScreenEvents.OnOptionDropped(
                                            slot = 'D',
                                            option = option.toString()
                                        )
                                    )
                                    return true
                                }
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    state.userAnswers['D'] ?: "",
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 4.dp)
                )
            }

        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .width(150.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp),
                thickness = 0.dp,
                color = Color.Transparent
            )
            Spacer(Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .border(width = 2.dp, shape = CircleShape, color = Color.White.copy(.5f)),
                contentAlignment = Alignment.Center
            ) { Text("E", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp) }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .widthIn(min = 120.dp)
                    .dashedBorder(1.dp, Color.White.copy(.5f), 8.dp)
                    .dragAndDropTarget(
                        shouldStartDragAndDrop = { event ->
                            event
                                .mimeTypes()
                                .contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        },
                        target = remember {
                            object : DragAndDropTarget {
                                override fun onDrop(event: DragAndDropEvent): Boolean {
                                    val option = event.toAndroidDragEvent().clipData
                                        ?.getItemAt(0)?.text
                                    events(
                                        LearningModuleScreenEvents.OnOptionDropped(
                                            slot = 'E',
                                            option = option.toString()
                                        )
                                    )
                                    return true
                                }
                            }
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    state.userAnswers['E'] ?: "",
                    color = Color.White,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 4.dp)
                )
            }

        }

    }
}

@Composable
fun NavigationButtons(
    state: LearningModuleScreenStates,
    checkAnswers: () -> Unit,
) {
    // Navigation Buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Previous Button
        Button(
            onClick = { /* TODO: Handle Previous Click */ },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color(0xFFE91E63)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier
                .width(148.dp)
                .height(52.dp)
        ) {
            Text("Previous", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Check Answer Button (Gradient)
        Button(
            onClick = { checkAnswers.invoke() },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.White.copy(alpha = 0.7f)
            ),
           enabled = state.questions[state.currentQuestionIndex].options.size == state.userAnswers.size,
            modifier = Modifier
                .width(148.dp)
                .height(52.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFE91E63), Color(0xFFF44336))
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text("Check Answer", fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Next Button
        Button(
            onClick = { /* TODO: Handle Next Click */ },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(2.dp, Color(0xFFE91E63)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier
                .width(148.dp)
                .height(52.dp)
        ) {
            Text("Next", fontSize = 14.sp)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableOptionButton(option: String) {
    Box(
        modifier = Modifier
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = { offset ->
                        startTransfer(
                            transferData = DragAndDropTransferData(
                                clipData = ClipData.newPlainText(
                                    "text",
                                    option
                                )
                            )
                        )
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF1E272E), RoundedCornerShape(12.dp))
                .height(40.dp)
                .wrapContentWidth()
                .border(
                    BorderStroke(0.5.dp, Color.White.copy(.5f)), RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 8.dp), contentAlignment = Alignment.Center
        ) {
            Text(option, color = Color.White, fontSize = 16.sp, maxLines = 1)
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableOptions(
    state: LearningModuleScreenStates,
) {
    val options = state.questions[state.currentQuestionIndex].options.values

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(options.toList()) { option ->
            DraggableOptionButton(option)
        }
    }
}


fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)

@Composable
fun LearningModuleTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Back Button",
                tint = Color.White
            )
        }


        Text(
            text = "Interactive learning",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.5f),
                    shape = MaterialTheme.shapes.medium,
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {},
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Biology", fontSize = 18.sp, color = Color.White)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Back Button",
                    tint = Color.White
                )
            }
        }
    }
}