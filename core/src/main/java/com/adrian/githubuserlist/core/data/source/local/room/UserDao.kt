package com.adrian.githubuserlist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.adrian.githubuserlist.core.data.source.local.entity.UserListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<UserListEntity>>

    @Query("SELECT * FROM users WHERE isFavorite = 1")
    fun getFavoriteUser(): Flow<List<UserListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserListEntity>)

    @Update
    fun updateFavoriteUser(user: UserListEntity)
}