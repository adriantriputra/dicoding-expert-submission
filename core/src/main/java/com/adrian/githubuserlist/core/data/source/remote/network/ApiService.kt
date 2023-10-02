package com.adrian.githubuserlist.core.data.source.remote.network

import com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse
import com.adrian.githubuserlist.core.data.source.remote.response.GithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("search/users?q=adrian")
    suspend fun getList(): GithubUserResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): DetailResponse
}