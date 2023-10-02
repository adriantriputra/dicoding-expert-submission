package com.adrian.githubuserlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class Items(
    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)
