package org.sopt.androidseminar.data

import com.google.gson.annotations.SerializedName

data class RepositoryInfo(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("language")
    val repoLang: String,
    @SerializedName("description")
    val repoDescription: String
)