package com.rijaldev.herbalplants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rijaldev.herbalplants.room.HerbalDB
import com.rijaldev.herbalplants.room.HerbalR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteListActivity : AppCompatActivity() {

    val db by lazy { HerbalDB(this) }
    lateinit var listFavoriteAdapter: ListFavoriteAdapter
    private lateinit var rvList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        rvList = findViewById(R.id.rv_fav)

        showFavList()
        showRecyclerList()
    }


    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val herbals = db.herbalDao().getHerbals()
            Log.e("FavouriteListActivity", "dbRespon :$herbals")
            withContext(Dispatchers.Main) {
                listFavoriteAdapter.setData(herbals)
            }
        }
    }


    private fun showRecyclerList(){

        listFavoriteAdapter = ListFavoriteAdapter(arrayListOf())
        rvList.apply {
            layoutManager = LinearLayoutManager(this@FavoriteListActivity)
            adapter = listFavoriteAdapter
        }
    }

    fun showFavList() {
        val actionBar = supportActionBar

        actionBar!!.title = "Favorite"
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}