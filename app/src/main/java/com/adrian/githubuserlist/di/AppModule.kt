package com.adrian.githubuserlist.di

import com.adrian.githubuserlist.core.domain.usecase.UserInteractor
import com.adrian.githubuserlist.core.domain.usecase.UserUseCase
import com.adrian.githubuserlist.detail.DetailViewModel
import com.adrian.githubuserlist.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}