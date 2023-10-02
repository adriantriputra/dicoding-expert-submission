package com.adrian.githubuserlist.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.githubuserlist.core.ui.UserAdapter
import com.adrian.githubuserlist.detail.DetailActivity
import com.adrian.githubuserlist.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Favorite Users"

        val favoriteAdapter = UserAdapter()
        favoriteAdapter.onItemClick = { favoriteUser ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, favoriteUser)
            startActivity(intent)
        }

        favoriteViewModel.favoriteUser.observe(this) { favoriteUser ->
            favoriteAdapter.setData(favoriteUser)
        }

        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }

}