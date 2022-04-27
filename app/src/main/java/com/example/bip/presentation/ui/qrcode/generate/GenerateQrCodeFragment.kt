package com.example.bip.presentation.ui.qrcode.generate

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.bip.App
import com.example.bip.domain.entity.CoordinatesData
import com.example.bip.presentation.ui.offers.client.contentView
import com.example.bip.presentation.utils.composeutils.theme.themesamples.ComposeCookBookMaterial3Theme
import com.example.bip.presentation.utils.viewModels
import com.yayandroid.locationmanager.base.LocationBaseFragment
import com.yayandroid.locationmanager.configuration.Configurations
import com.yayandroid.locationmanager.configuration.LocationConfiguration

/**
 * @author v.nasibullin
 */
class GenerateQrCodeFragment : LocationBaseFragment() {

    private val viewModel: GenerateQrCodeViewModel by viewModels {
        App.appComponent.getGenerateQrCodeViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return contentView(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)) {
            ComposeCookBookMaterial3Theme {
                GenerateQrCode(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLocation()
        //viewModel.createOrderState.observe(viewLifecycleOwner) { createOrderState(it) }
    }

    override fun onLocationChanged(location: Location?) {
        location?.run {
            viewModel.setCoordinatesData(CoordinatesData(latitude = latitude.toFloat(), longitude = latitude.toFloat()))
        }
    }

    override fun onLocationFailed(type: Int) {
        return
    }

    override fun getLocationConfiguration(): LocationConfiguration {
        return Configurations.defaultConfiguration("Gimme the permission!", "Would you mind to turn GPS on?");
    }
}
