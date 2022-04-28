package com.example.bip.presentation.ui.order.addphoto

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.domain.entity.PhotoData
import com.example.bip.domain.repository.AddPhotoController
import com.example.bip.presentation.interfaces.CreateOrderController
import com.example.bip.presentation.ui.order.client.HorizontalDottedProgressBar
import com.example.bip.presentation.utils.composeutils.lottie.LottieLoadingView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.helpers.TextFieldDefaultsMaterial

/**
 * @author v.nasibullin
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderScreen(addPhotoController: AddPhotoController) {
    //TextFields
    var costField by remember { mutableStateOf(TextFieldValue("")) }
    var commentField by remember { mutableStateOf(TextFieldValue("")) }
    var costStr = ""
    var commentStr = ""
    var hasError by remember { mutableStateOf(false) }
    val passwordInteractionState = remember { MutableInteractionSource() }
    val emailInteractionState = remember { MutableInteractionSource() }

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
                    text = "Отправьте фотки клиенту!",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Text(
                    text = "Добавьте ссылку на папку с фотками с вотермаркой и без вотремарки",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = costField,
                    maxLines = 1,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    label = { Text(text = "С вотермаркой") },
                    placeholder = { Text(text = "Вотермака") },
                    onValueChange = {
                        costStr = it.text
                        costField = it
                    },
                    interactionSource = emailInteractionState,
                )
            }
            item {
                OutlinedTextField(
                    value = commentField,
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    maxLines = 10,
                    isError = hasError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Без вотермарки") },
                    placeholder = { Text(text = "Оригинальные") },
                    onValueChange = {
                        commentStr = it.text
                        commentField = it
                    },
                    interactionSource = passwordInteractionState,
                )
            }
            item {
                var loading by remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        val addPhotoData = PhotoData(urlOrigin = commentStr, waterMark = costStr)
                        addPhotoController.addPhoto(addPhotoData)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6495ED)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    if (loading) {
                        HorizontalDottedProgressBar()
                    } else {
                        Text(text = "Отправить фотки")
                    }
                }
            }
        }
    }
}
