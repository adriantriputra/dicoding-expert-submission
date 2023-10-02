package com.adrian.githubuserlist.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.githubuserlist.core.domain.model.User
import com.adrian.githubuserlist.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) = userUseCase.getDetailUser(username).asLiveData()

    fun setFavoriteUser(user: User, newStatus: Boolean) =
        userUseCase.setFavoriteUser(user, newStatus)
}