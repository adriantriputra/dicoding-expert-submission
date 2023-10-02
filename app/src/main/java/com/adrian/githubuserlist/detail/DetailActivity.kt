package com.adrian.githubuserlist.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adrian.githubuserlist.R
import com.adrian.githubuserlist.core.data.Resource
import com.adrian.githubuserlist.core.domain.model.User
import com.adrian.githubuserlist.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailUser = intent.getParcelableExtra<User>(EXTRA_DATA)
        showDetailUser(detailUser)
    }

    private fun showDetailUser(detailUser: User?) {
        if (detailUser != null) {
            val detailLiveData = detailViewModel.getDetailUser(detailUser.username)

            detailLiveData.observe(this){ detail ->
                when (detail) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val detailData = detail.data
                        with (binding) {
                            supportActionBar?.title = detailData?.login

                            if (detailData != null) {
                                Glide.with(this@DetailActivity)
                                    .load(detailData.avatarUrl)
                                    .into(ivProfile)

                                tvName.text = detailData.name
                                tvFollowers.text = detailData.followers.toString()
                                tvFollowing.text = detailData.following.toString()
                                tvLocation.text = detailData.location
                            }
                        }
                    }
                    else -> {
                        binding.progressBar.visibility = View.GONE
                        detail.message?.let { Log.e("DetailActivity", it) }
                    }
                }
            }

            var statusFavorite = detailUser.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteUser(detailUser, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}