package com.example.bip.presentation.ui.mainscreen

import android.content.res.Resources
import com.example.bip.R
import io.reactivex.Single
import javax.inject.Inject

class GetNeedConfigurations @Inject constructor() : (Resources, Boolean) -> Single<List<String>> {
    override fun invoke(resources: Resources, isPhotographer: Boolean): Single<List<String>> {
        return Single.fromCallable {
            getNeedStrings(resources, isPhotographer)
        }
    }

    private fun getNeedStrings(resources: Resources, isPhotographer: Boolean): List<String> {
        return if (isPhotographer) {
            listOf(
                resources.getString(R.string.select_photo),
                resources.getString(R.string.scan_qr_code),
                resources.getString(R.string.send_photo)
            )
        } else {
            listOf(
                resources.getString(R.string.make_photo),
                resources.getString(R.string.generate_qr_code),
                resources.getString(R.string.check_photo)
            )
        }
    }
}
