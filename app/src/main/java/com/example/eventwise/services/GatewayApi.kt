package com.example.eventwise.services

import com.example.eventwise.models.ErrorResponse
import com.example.eventwise.models.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GatewayApi {
    val gatewayService: GatewayService by lazy {
        retrofit.create(GatewayService::class.java)
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

    val errorConverter: Converter<ResponseBody, ErrorResponse> =
        retrofit.responseBodyConverter(ErrorResponse::class.java, arrayOfNulls<Annotation>(0))

    val responseConverter: Converter<ResponseBody, Response> =
        retrofit.responseBodyConverter(Response::class.java, arrayOfNulls<Annotation>(0))
}
