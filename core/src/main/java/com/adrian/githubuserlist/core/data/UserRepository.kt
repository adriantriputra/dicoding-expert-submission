package com.adrian.githubuserlist.core.data

import android.util.Log
import com.adrian.githubuserlist.core.data.source.local.LocalDataSource
import com.adrian.githubuserlist.core.data.source.remote.RemoteDataSource
import com.adrian.githubuserlist.core.data.source.remote.network.ApiResponse
import com.adrian.githubuserlist.core.data.source.remote.response.Items
import com.adrian.githubuserlist.core.domain.model.Detail
import com.adrian.githubuserlist.core.domain.model.User
import com.adrian.githubuserlist.core.domain.repository.IUserRepository
import com.adrian.githubuserlist.core.utils.AppExecutors
import com.adrian.githubuserlist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {
    override fun getUserInit(): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<Items>>(appExecutors) {
            override fun loadFromDB(): Flow<List<User>> {
                Log.d("UserRepository", "Loading from DB...")
                return localDataSource.getAllUser().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<Items>>> {
                Log.d("UserRepository", "API call...")
                return remoteDataSource.getUserInit()
            }

            override suspend fun saveCallResult(data: List<Items>) {
                Log.d("UserRepository", "Saving data to DB...")
                Log.d("UserRepository", "data: $data")
                val userList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(userList)
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                Log.d("UserRepository", "Should fetch: ${data.isNullOrEmpty()}")
                return data.isNullOrEmpty()
            }
        }.asFlow()

    override fun getDetailUser(username: String): Flow<Resource<Detail>> = flow {
        emit(Resource.Loading())

        try {
            val detailResponse = remoteDataSource.getDetailUser(username).first()
            if (detailResponse is ApiResponse.Success) {
                val detail = DataMapper.mapDetailResponseToDomain(detailResponse.data)
                emit(Resource.Success(detail))
            } else if (detailResponse is ApiResponse.Error) {
                emit(Resource.Error(detailResponse.errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getFavoriteUser().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteUser(user: User, newState: Boolean) {
        val userListEntity = DataMapper.mapDomainToEntities(user)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteUser(userListEntity, newState)
        }
    }
}