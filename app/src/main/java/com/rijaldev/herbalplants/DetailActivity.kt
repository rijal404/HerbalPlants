/* package com.rijaldev.herbalplants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_IMG_URL = "extra_img_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showDetail()
    }

    private fun showDetail() {

        val actionBar = supportActionBar
        val imgDetail: ImageView = findViewById(R.id.detail_image)
        val textDetail: TextView = findViewById(R.id.detail_text)
        val actionTitle: ActionBar? = actionBar
        val bundle: Bundle? = intent.extras
        val resiId : Int = bundle!!.getInt(EXTRA_IMG_URL)

        actionBar!!.title = "Detail"
        actionBar.setDisplayHomeAsUpEnabled(true)

        imgDetail.setImageResource(resiId)
        textDetail.text = intent.getStringExtra(EXTRA_DETAIL)
        actionTitle?.title = intent.getStringExtra(EXTRA_NAME)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

 */
package com.rijaldev.herbalplants

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.rijaldev.herbalplants.room.HerbalDB
import com.rijaldev.herbalplants.room.HerbalR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_IMG_URL = "extra_img_url"
    }

    val db by lazy { HerbalDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showDetail()
        setFavorite()
    }

    private fun showDetail() {

        val actionBar = supportActionBar
        val imgDetail: ImageView = findViewById(R.id.detail_image)
        val textDetail: TextView = findViewById(R.id.detail_text)
        val shareButton: ImageButton = findViewById(R.id.btn_share)
        val actionTitle: ActionBar? = actionBar
        val bundle: Bundle? = intent.extras
        val resiId : Int = bundle!!.getInt(EXTRA_IMG_URL)

        actionBar!!.title = "Detail"
        actionBar.setDisplayHomeAsUpEnabled(true)

        imgDetail.setImageResource(resiId)
        textDetail.text = intent.getStringExtra(EXTRA_DETAIL)
        actionTitle?.title = intent.getStringExtra(EXTRA_NAME)

        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, intent.getStringExtra(EXTRA_DETAIL))
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivity(shareIntent)
        }
    }

    fun setFavorite() {
        val btnAddFav: CheckBox = findViewById(R.id.btn_fav)
        val name = intent.getStringExtra(EXTRA_NAME)
        val detail = intent.getStringExtra(EXTRA_DETAIL)
        val bundle: Bundle? = intent.extras
        val resiId : Int = bundle!!.getInt(EXTRA_IMG_URL)

        btnAddFav.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.herbalDao().addHerbal(
                    HerbalR(0,name.toString() , detail.toString(), resiId )
                )
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}