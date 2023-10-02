package com.adrian.githubuserlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GithubUserResponse(

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<Items>
)