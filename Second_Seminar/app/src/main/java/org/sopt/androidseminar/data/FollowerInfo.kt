package org.sopt.androidseminar.data

data class FollowerInfo(val followerName: String, val followerGithubId: String, val viewType : Int){
    companion object{
        const val NORMAL_CONTENT = 0
        const val AD_CONTENT = 1
    }
}