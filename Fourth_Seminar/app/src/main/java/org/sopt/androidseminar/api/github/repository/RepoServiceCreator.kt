package org.sopt.androidseminar.api.github.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepoServiceCreator {
    private const val BASE_URL = "http://api.github.com"
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    val repoService: RepoService = retrofit.create(RepoService::class.java)
}