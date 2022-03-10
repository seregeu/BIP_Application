package com.example.bip.data.network

import com.example.bip.data.db.dao.AuthDao
import com.example.bip.data.entity.*
import com.example.bip.data.network.utils.NetworkConstants.BASE_URL
import com.example.bip.data.network.utils.addJsonConverter
import com.example.bip.data.network.utils.setClient
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/registration")
    fun registerUser(@Body userEntity: UserEntity): Completable

    @POST("api/auth")
    fun authUser(@Body authBody: AuthBody): Single<AuthResponseFirst>

    @POST("api/auth2fa")
    fun auth2Fa(@Body authSecondEntity: AuthSecondEntity): Single<AuthResponse>

    companion object {
        fun create(authDao: AuthDao): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient(authDao)
                .addJsonConverter()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
                .create(ApiService::class.java)
        }
    }
}
