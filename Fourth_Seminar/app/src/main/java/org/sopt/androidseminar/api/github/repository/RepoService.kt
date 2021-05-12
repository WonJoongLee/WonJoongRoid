package org.sopt.androidseminar.api.github.repository

import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.RepositoryInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RepoService {
    // Gihtub에서 하루 사용량을 넘으면 추가로 인증을 해야 해서 Authorization을 추가 했음
    @Headers("Authorization: ${R.string.github_private_Key}")
    @GET("/users/WonJoongLee/repos")
    fun getRepos(): Call<List<RepositoryInfo>>
}