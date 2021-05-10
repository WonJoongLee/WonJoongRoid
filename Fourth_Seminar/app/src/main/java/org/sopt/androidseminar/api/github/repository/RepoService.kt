package org.sopt.androidseminar.api.github.repository

import org.sopt.androidseminar.data.response.ResponseRepoData
import retrofit2.Call
import retrofit2.http.GET

interface RepoService {
    @GET("/users/WonJoongLee/repos")
    fun getRepos(): Call<List<ResponseRepoData>>
}