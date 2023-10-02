package com.adrian.githubuserlist.core.domain.usecase

import com.adrian.githubuserlist.core.data.Resource
import com.adrian.githubuserlist.core.domain.model.Detail
import com.adrian.githubuserlist.core.domain.model.User
import com.adrian.githubuserlist.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor(private val userRepository: IUserRepository) : UserUseCase {
    override fun getUserInit(): Flow<Resource<List<User>>> = userRepository.getUserInit()

    override fun getDetailUser(username: String): Flow<Resource<Detail>> =
        userRepository.getDetailUser(username)

    override fun getFavoriteUser(): Flow<List<User>> = userRepository.getFavoriteUser()

    override fun setFavoriteUser(user: User, newState: Boolean) =
        userRepository.setFavoriteUser(user, newState)
}