package com.example.bip.presentation.ui.order.client

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
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.bip.domain.entity.CreateOrderData
import com.example.bip.presentation.interfaces.CreateOrderController
import com.example.bip.presentation.utils.composeutils.lottie.LottieLoadingView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.helpers.TextFieldDefaultsMaterial

/**
 * @author v.nasibullin
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderScreen(createOrderController: CreateOrderController) {
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
                    text = "Create order!",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                Text(
                    text = "We have missed you, Let's start by create order!",
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
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaultsMaterial.outlinedTextFieldColors(),
                    label = { Text(text = "Prise") },
                    placeholder = { Text(text = "500") },
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
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    label = { Text(text = "Comments") },
                    placeholder = { Text(text = "Describe what you would like at the photo session") },
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
                        val orderData = CreateOrderData(comment = commentStr, orderCost = costStr.toInt())
                        createOrderController.createOrder(orderData)
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
                        Text(text = "Create order")
                    }
                }
            }
        }
    }
}
