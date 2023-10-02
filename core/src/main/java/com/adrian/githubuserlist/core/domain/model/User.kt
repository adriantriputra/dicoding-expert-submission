package com.adrian.githubuserlist.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String,
    val avatarUrl: String,
    val isFavorite: Boolean,
) : Parcelable
