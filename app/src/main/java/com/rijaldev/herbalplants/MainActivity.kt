package com.rijaldev.herbalplants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvHerbals: RecyclerView
    private var list: ArrayList<Herbal> = arrayListOf()
    private var listHerbalAdapter: ListHerbalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_HerbalPlants)
        setContentView(R.layout.activity_main)

        rvHerbals = findViewById(R.id.rv_herbals)

        showRecyclerList()
        moveToDetailActivity()
    }

    private fun showRecyclerList(){

        list.addAll(HerbalsData.listData)
        listHerbalAdapter = ListHerbalAdapter(list)

        rvHerbals.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listHerbalAdapter
            setHasFixedSize(true)
        }
    }

    private fun moveToDetailActivity(){
       
        listHerbalAdapter?.setOnItemCallback(object : ListHerbalAdapter.OnItemClickCallback {
            override fun onItemClick(herbal: Herbal) {
                val manageDetailActivity = Intent(this@MainActivity, DetailActivity::class.java)
                    .apply {
                        putExtra(DetailActivity.EXTRA_NAME, herbal.name)
                        putExtra(DetailActivity.EXTRA_DETAIL, herbal.detail)
                        putExtra(DetailActivity.EXTRA_IMG_URL, herbal.photo)
                    }
                startActivity(manageDetailActivity)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when(selectedMode){
            R.id.about -> {
                val pindahKeAboutMenuActivity =  Intent(this@MainActivity, AboutMenuActivity::class.java)
                startActivity(pindahKeAboutMenuActivity)
            }

            R.id.fav_list -> {
                val pindahKeFavoriteListActivity = Intent(this@MainActivity, FavoriteListActivity::class.java)
                startActivity(pindahKeFavoriteListActivity)
            }
        }
    }
}