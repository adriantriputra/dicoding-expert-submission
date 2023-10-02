package com.adrian.githubuserlist.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.githubuserlist.core.data.source.local.entity.UserListEntity

@Database(entities = [UserListEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}