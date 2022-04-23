package com.example.bip.presentation.utils

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author v.nasibullin
 */

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline viewModelProducer: () -> VM
): Lazy<VM> {
    return viewModels { ViewModelFactory(viewModelProducer) }
}

class ViewModelFactory<VM : ViewModel> @Inject constructor(
    private val viewModelProducer: () -> VM
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelProducer() as T
    }
}
