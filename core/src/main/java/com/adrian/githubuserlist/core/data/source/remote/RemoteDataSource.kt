package com.adrian.githubuserlist.core.data.source.remote

import android.util.Log
import com.adrian.githubuserlist.core.data.source.remote.network.ApiResponse
import com.adrian.githubuserlist.core.data.source.remote.network.ApiService
import com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse
import com.adrian.githubuserlist.core.data.source.remote.response.Items
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getUserInit(): Flow<ApiResponse<List<Items>>> {
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.items
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.items))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailUser(username: String): Flow<ApiResponse<DetailResponse>> {
        return flow {
            try {
                val response = apiService.getDetailUser(username)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}