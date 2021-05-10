package org.sopt.androidseminar.api.github.repository

import org.sopt.androidseminar.data.response.ResponseRepoData
import org.sopt.androidseminar.GITHUB_PERSONAL_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RepoService {
    @Headers("Authorization: $GITHUB_PERSONAL_KEY")
    @GET("/users/WonJoongLee/repos")
    fun getRepos(): Call<List<ResponseRepoData>>
}