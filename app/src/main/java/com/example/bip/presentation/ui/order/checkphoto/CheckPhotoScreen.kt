package com.example.bip.presentation.ui.order.checkphoto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bip.domain.entity.PhotoData
import com.example.bip.presentation.interfaces.CheckPhotoController
import com.example.bip.presentation.ui.order.client.HorizontalDottedProgressBar
import com.example.bip.presentation.utils.composeutils.lottie.LottieLoadingView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.helpers.TextFieldDefaultsMaterial
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author v.nasibullin
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckPhotoScreen(checkPhotoController: CheckPhotoController, viewModel: CheckPhotoViewModel) {
    //TextFields
    val url = viewModel.photoUrl.observeAsState()

    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(20.dp)) }
            item {
                LottieLoadingView(
                    context = LocalContext.current,
                    file = "profile.json",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
            item {
                Text(
                    text = "Фотограф прислал фотки! Осталось совсем немного",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Text(
                    text = "Проверьте качество фотографий, если оно вас устраивает заплатите фотографу",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                Text(
                    text = url.value ?: "",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.clickable {
                        checkPhotoController.checkPhoto(url.value ?: "")
                    }
                )
            }
            item {
                val swipeButtonState = remember {
                    mutableStateOf(SwipeButtonState.INITIAL)
                }
                val coroutineScope = rememberCoroutineScope()
                SwipeButton(
                    onSwiped = {
                        swipeButtonState.value = SwipeButtonState.SWIPED
                        viewModel.finishOrder()
                        coroutineScope.launch {
                            delay(2000)
                            swipeButtonState.value = SwipeButtonState.COLLAPSED
                        }
                    },
                    swipeButtonState = swipeButtonState.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(60.dp),
                    iconPadding = PaddingValues(4.dp),
                    shape = CircleShape,
                ) {
                    Text(text = "Заплатить", style = typography.h6.copy(fontSize = 16.sp))
                }
            }
        }
    }
}
