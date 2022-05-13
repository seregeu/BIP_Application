package com.example.bip.presentation.ui.orderlist

/**
 * @author v.nasibullin
 */

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImage
import com.example.bip.domain.entity.OrderData
import com.example.bip.presentation.interfaces.OrderListController
import com.example.bip.presentation.ui.order.photo.SelectOrderViewModel
import com.example.bip.presentation.utils.composeutils.theme.themesamples.components.Material3Card

/**
 * @author v.nasibullin
 */
@Composable
fun OrderListScreen(orderListLiveData: LiveData<MutableList<OrderData>>, orderListController: OrderListController) {
    val orders = orderListLiveData.observeAsState()
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        VerticalGrid(columns = 2) {
            orders.value?.forEach {
                GridListItem(item = it) { orderData ->
                    orderListController.orderSelect(orderData = orderData)
                }
            }
        }
    }
}

/**
 * Taken from Jetsnack sample. Right now there is no inbuilt grid but we can make
 * Custom layout like below which takes number of items and place them in Grid fashion.

 * A simple grid which lays elements out vertically in evenly sized [columns].
 */
@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        // Keep given height constraints, but set an exact width
        val itemConstraints = constraints.copy(
            minWidth = itemWidth,
            maxWidth = itemWidth
        )
        // Measure each item with these constraints
        val placeables = measurables.map { it.measure(itemConstraints) }
        // Track each columns height so we can calculate the overall height
        val columnHeights = Array(columns) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            columnHeights[column] += placeable.height
        }
        val height = (columnHeights.maxOrNull() ?: constraints.minHeight)
            .coerceAtMost(constraints.maxHeight)
        layout(
            width = constraints.maxWidth,
            height = height
        ) {
            // Track the Y co-ord per column we have placed up to
            val columnY = Array(columns) { 0 }
            placeables.forEachIndexed { index, placeable ->
                val column = index % columns
                placeable.placeRelative(
                    x = column * itemWidth,
                    y = columnY[column]
                )
                columnY[column] += placeable.height
            }
        }
    }
}

@Composable
fun GridListItem(
    item: OrderData,
    modifier: Modifier = Modifier,
    clickListener: (OrderData) -> Unit,
) {
    Material3Card(
        shape = androidx.compose.material.MaterialTheme.shapes.medium,
        modifier = modifier
            .width(190.dp)
            .height(220.dp)
            .padding(8.dp)
            .clickable(onClick = { clickListener(item) })
    ) {
        Column(modifier = Modifier.clickable(onClick = { clickListener(item) })) {
            AsyncImage(
                model = item.userData.avatarUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "${item.userData.firstName} ${item.userData.secondName}",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.orderCost.toString(),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = item.comment,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
