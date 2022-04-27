package com.example.bip.presentation.ui.qrcode.generate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography

/**
 * @author v.nasibullin
 */
@Composable
fun GenerateQrCode(viewModel: GenerateQrCodeViewModel) {
    val qrCode = viewModel.qrCodeLiveData.observeAsState("")
    val expanded = viewModel.expand.observeAsState(false)
    SubtitleText(subtitle = "AnimateVisibility() with different child Animations")
    IconButton(onClick = {
        if (expanded.value) {
            viewModel.collapse()
        } else {
            viewModel.generateQrCode()
        }
    }) {
        Icon(imageVector = Icons.Default.RemoveRedEye, contentDescription = "")
    }
    AnimatedVisibility(visible = expanded.value) {
        SubtitleText(subtitle = qrCode.value ?: "")
    }
}

@Composable
fun SubtitleText(subtitle: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(text = subtitle, style = typography.subtitle2, fontSize = 32.sp, modifier = modifier.padding(8.dp))
}
