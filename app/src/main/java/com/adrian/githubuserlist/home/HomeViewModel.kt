package com.adrian.githubuserlist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adrian.githubuserlist.core.domain.usecase.UserUseCase

class HomeViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    val user = userUseCase.getUserInit().asLiveData()
}
