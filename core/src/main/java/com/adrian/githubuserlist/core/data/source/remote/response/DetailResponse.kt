package com.adrian.githubuserlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("location")
    val location: String
)