package com.adrian.githubuserlist.core.data.source.local

import com.adrian.githubuserlist.core.data.source.local.entity.UserListEntity
import com.adrian.githubuserlist.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val userDao: UserDao) {
    fun getAllUser(): Flow<List<UserListEntity>> = userDao.getAllUser()

    fun getFavoriteUser(): Flow<List<UserListEntity>> = userDao.getFavoriteUser()

    suspend fun insertUser(userList: List<UserListEntity>) = userDao.insertUser(userList)

    fun setFavoriteUser(user: UserListEntity, newState: Boolean){
        user.isFavorite = newState
        userDao.updateFavoriteUser(user)
    }
}