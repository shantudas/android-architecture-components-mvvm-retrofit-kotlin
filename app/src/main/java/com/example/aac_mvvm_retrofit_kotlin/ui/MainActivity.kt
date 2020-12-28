package com.example.aac_mvvm_retrofit_kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.repository.ArticleRepository_Impl
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.ArticleDtoMapper
import com.example.aac_mvvm_retrofit_kotlin.network.retrofit.RetrofitService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()

        setupBottomNavigation()

        //fetchData();


    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
        val mapper: ArticleDtoMapper= ArticleDtoMapper()
        val repository: ArticleRepository_Impl = ArticleRepository_Impl(retrofit,mapper)

        CoroutineScope(IO).launch {


            val articles = repository.getArticles("us", "494d8b052f544a4e9848574a6c4930bc")

            /*val response = retrofit.getHeadlines("us", "494d8b052f544a4e9848574a6c4930bc")
            Log.i(TAG, "response :: ${response.articles}")
            val articles: List<HeadlineDto> = response.articles*/

            for (item in articles) {
                Log.i(TAG, "article :: ${item.title}")
            }
        }

    }


    private fun setupBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_headline, R.id.navigation_interests
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    private fun init() {
        navView = findViewById(R.id.nav_view)
    }
}
