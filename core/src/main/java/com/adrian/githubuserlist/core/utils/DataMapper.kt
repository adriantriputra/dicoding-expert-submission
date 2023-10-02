package com.adrian.githubuserlist.core.utils

import com.adrian.githubuserlist.core.data.source.local.entity.UserListEntity
import com.adrian.githubuserlist.core.data.source.remote.response.DetailResponse
import com.adrian.githubuserlist.core.data.source.remote.response.Items
import com.adrian.githubuserlist.core.domain.model.Detail
import com.adrian.githubuserlist.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<Items>): List<UserListEntity> {
        val userList = ArrayList<UserListEntity>()
        input.map {
            val user = UserListEntity(
                username = it.login,
                avatarUrl = it.avatarUrl,
                isFavorite = false
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserListEntity>): List<User> =
        input.map {
            User(
                username = it.username,
                avatarUrl = it.avatarUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: User) = UserListEntity(
        username = input.username,
        avatarUrl = input.avatarUrl,
        isFavorite = input.isFavorite
    )

    fun mapDetailResponseToDomain(input: DetailResponse) = Detail(
        login = input.login,
        avatarUrl = input.avatarUrl,
        name = input.name,
        followers = input.followers,
        following = input.following,
        location = input.location
    )
}