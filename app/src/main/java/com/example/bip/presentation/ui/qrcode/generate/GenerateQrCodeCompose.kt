package com.example.bip.presentation.ui.qrcode.generate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography

/**
 * @author v.nasibullin
 */
@OptIn(ExperimentalAnimationApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun GenerateQrCode(viewModel: GenerateQrCodeViewModel) {
    val qrCode = viewModel.qrCodeLiveData.observeAsState("")
    val expanded = viewModel.expand.observeAsState(false)
    Scaffold {
        LazyColumn(
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                SubtitleText(subtitle = "Click on the icon if you met with a photographer and he is ready to take a picture of you")
                IconButton(
                    onClick = {
                        if (expanded.value) {
                            viewModel.collapse()
                        } else {
                            viewModel.generateQrCode()
                        }
                    },
                ) {
                    Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")
                }
                AnimatedVisibility(visible = expanded.value) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Image(
                            bitmap = QrCodeGeneratorUtil.encodeAsBitmap(qrCode.value, WIDTH = 300, HEIGHT = 300).asImageBitmap(),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .size(300.dp)
                                .padding(2.dp)
                                .animateEnterExit(
                                    enter = slideInHorizontally { it },
                                    exit = ExitTransition.None
                                )
                        )
                        Button(
                            onClick = {
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6495ED)
                            ),
                            modifier = Modifier
                                .width(160.dp)
                                .height(60.dp)
                                .padding(vertical = 2.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(CircleShape)
                                .animateEnterExit(
                                    enter = slideInHorizontally { it },
                                    exit = ExitTransition.None
                                )
                        ) {
                            Text(text = "Ready")
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(
        text = subtitle,
        textAlign = TextAlign.Start,
        style = typography.subtitle2,
        fontSize = 20.sp,
        color = Color.Black,
        modifier = modifier.padding(8.dp)
    )
}
