package com.adrian.githubuserlist.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.githubuserlist.core.domain.usecase.UserUseCase

class FavoriteViewModel(userUseCase: UserUseCase): ViewModel() {
    val favoriteUser = userUseCase.getFavoriteUser().asLiveData()
}