package com.example.bip.presentation.ui.offers.client

/**
 * @author v.nasibullin
 */

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bip.R
import com.example.bip.domain.entity.UserData
import com.example.bip.presentation.utils.composeutils.canvas.MultiStateAnimationCircleFilledCanvas
import com.example.bip.presentation.utils.composeutils.theme.themesamples.modifiers.verticalGradientBackground
import com.example.bip.presentation.utils.composeutils.theme.themesamples.purple
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography

@Composable
fun OfferHomeScreen(viewModel: OfferHomeViewModel) {
    val photographers = viewModel.offerLiveData.observeAsState()
    val mutablePhotographers = remember { photographers }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val cardHeight = screenHeight - 200.dp
    var currentItem: UserData? = photographers.value?.firstOrNull()

    Surface(modifier = Modifier.fillMaxSize()) {
        val boxModifier = Modifier

        Box(
            modifier = boxModifier.verticalGradientBackground(
                listOf(
                    Color.White,
                    purple.copy(alpha = 0.2f)
                )
            )
        ) {
            val listEmpty = remember { mutableStateOf(false) }
            DatingLoader(modifier = boxModifier)
            mutablePhotographers.value?.forEachIndexed { index, photographer ->
                DraggableCard(
                    item = photographer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .padding(
                            top = 16.dp + (index + 2).dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    onSwiped = { _, swipedAlbum ->
                        if (mutablePhotographers.value?.isNotEmpty() == true) {
                            mutablePhotographers.value?.remove(swipedAlbum)
                            if (photographers.value?.isEmpty() == true) {
                                listEmpty.value = true
                            }
                        }
                    }
                ) {
                    CardContent(photographer)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = cardHeight)
                    .alpha(animateFloatAsState(if (listEmpty.value) 0f else 1f).value)
            ) {
                AnimatedVisibility(visible = listEmpty.value) {
                    IconButton(
                        onClick = {
                            if (currentItem != null) {
                                viewModel.selectPhotographer(currentItem, false)
                            }
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            tint = Color.Gray,
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
                AnimatedVisibility(visible = listEmpty.value) {
                    IconButton(
                        onClick = {
                            if (currentItem != null) {
                                viewModel.selectPhotographer(currentItem, true)
                            }
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CardContent(userData: UserData) {
    Column {
        AsyncImage(
            model = userData.avatarUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Text(
                text = "${userData.firstName} ${userData.secondName}",
                style = typography.h6,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                textAlign = TextAlign.Start
            )
            Icon(
                imageVector = Icons.Outlined.Place,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                tint = purple,
                contentDescription = null
            )
            Text(
                text = userData.phoneNumber,
                style = typography.body2,
                color = purple
            )
        }
        Text(
            text = userData.mail,
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 4.dp, start = 16.dp, end = 16.dp)
        )
        Text(
            text = "Saint-Petersburg",
            style = typography.subtitle2,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
fun DatingLoader(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .fillMaxSize()
            .clip(CircleShape)
    ) {
        MultiStateAnimationCircleFilledCanvas(purple, 400f)
        Image(
            painter = painterResource(id = R.drawable.ic_qr_code),
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}
