package com.vipulasri.jetinstagram.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vipulasri.jetinstagram.model.Favorite
import com.vipulasri.jetinstagram.model.Post

object FavoriteRepository {

    private val favorite = mutableStateOf<List<Favorite>>(emptyList())
    val posts: State<List<Favorite>> = favorite


    private fun populatePosts() {
        val posts = ArrayList<Favorite>()
        (0..9).forEach { index ->
            val favorite1 = Favorite(
                name = "Name $index",
                image = "https://source.unsplash.com/random/400x300?$index",
            )
            posts.add(favorite1)
        }
        this.favorite.value = posts

    }

    init {
        populatePosts()
    }

}