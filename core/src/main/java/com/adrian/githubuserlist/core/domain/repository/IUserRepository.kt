package com.adrian.githubuserlist.core.domain.repository

import com.adrian.githubuserlist.core.data.Resource
import com.adrian.githubuserlist.core.domain.model.Detail
import com.adrian.githubuserlist.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUserInit(): Flow<Resource<List<User>>>

    fun getDetailUser(username: String): Flow<Resource<Detail>>

    fun getFavoriteUser(): Flow<List<User>>

    fun setFavoriteUser(user: User, newState: Boolean)
}