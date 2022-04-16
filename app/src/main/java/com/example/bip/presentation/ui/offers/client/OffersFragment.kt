package com.example.bip.presentation.ui.offers.client

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.composeutils.theme.themesamples.typography

/**
 * @author v.nasibullin
 */
class OffersFragment : Fragment() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme(false) {
                Scaffold(
                    topBar = { DatingHomeAppbar() }
                ) {
                    DatingHomeScreen()
                }
            }
        }
    }
}


@Composable
fun DatingHomeAppbar() {
    val title = "Discover"
    SmallTopAppBar(
        title = { Text(title, style = typography.h6) },
        actions = {
            IconButton(onClick = {}) {
                Icons.Default.MyLocation
            }
        },
    )
}

fun Fragment.contentView(
    compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
    context: Context? = getContext(),
    content: @Composable () -> Unit
): ComposeView? {
    context ?: return null
    val view = ComposeView(context)
    view.setViewCompositionStrategy(compositionStrategy)
    view.setContent(content)
    return view
}

