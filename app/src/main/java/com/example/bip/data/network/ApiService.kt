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
import retrofit2.http.*

interface ApiService {

    @POST("api/registration")
    fun registerUser(@Body userBody: UserBody): Completable

    @POST("api/auth")
    fun authUser(@Body authBody: AuthBody): Single<AuthResponseFirst>

    @POST("api/auth2fa")
    fun auth2Fa(@Body authSecondEntity: AuthSecondEntity): Single<AuthResponse>

    @GET("api/profile")
    fun getUserProfile(@Query("id_user") userId: String): Single<UserDto>

    @POST("api/client/create-order")
    fun createOrder(@Body orderBody: OrderBody): Single<OrderDto>

    @GET("api/ph/orders")
    fun getOrders(): Single<OrderListResponse>

    @GET("api/client/offer")
    fun getOffers(@Query("id_order") idOrder: Int): Single<OfferListResponse>

    @PATCH("api/ph/select")
    fun selectOrder(@Query("id_order") idOrder: Int): Completable

    @PATCH("api/client/accept")
    fun acceptOffer(
        @Query("id_order") idOrder: Int,
        @Query("id_photographer") idPhotographer: Int,
        @Query("is_accept") isAccept: Boolean,
    ): Completable

    @PATCH("api/ph/confirm-qrcode")
    fun confirmQrCode(@Query("qrcode") qrCode: String): Completable

    @GET("api/client/qrcode")
    fun getQrCode(
        @Query("id_order") idOrder: Int,
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
    ): Single<String>

    @GET("api/client/all-orders")
    fun getAllOrders(): Single<AllOrdersResponse>

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
