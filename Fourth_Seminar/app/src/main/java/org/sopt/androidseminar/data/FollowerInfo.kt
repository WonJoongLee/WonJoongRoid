package org.sopt.androidseminar.data

import com.google.gson.annotations.SerializedName

data class FollowerInfo(
    @SerializedName("login")
    val followerName: String,
    val viewType : Int = 0
    ){
    companion object{
        const val NORMAL_CONTENT = 0
        const val AD_CONTENT = 1
    }
}