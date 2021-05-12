package org.sopt.androidseminar.api

import org.sopt.androidseminar.api.github.RepoService
import org.sopt.androidseminar.api.soptlogin.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val SOPT_BASE_URL = "http://cherishserver.com"
    private const val GITHUB_BASE_URL = "http://api.github.com"
    private val soptRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(SOPT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val githubRetrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val repoService: RepoService = githubRetrofit.create(RepoService::class.java)
}