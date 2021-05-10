package org.sopt.androidseminar.data.response

import com.google.gson.annotations.SerializedName

data class ResponseRepoData(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("language")
    val repoLang: String,
    @SerializedName("description")
    val repoDescription: String
)