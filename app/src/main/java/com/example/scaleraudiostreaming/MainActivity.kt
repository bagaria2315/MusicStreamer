package com.example.scaleraudiostreaming

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
//    lateinit var myRecyclerView: RecyclerView
//    lateinit var myAdapter: MyAdapter

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. declare and initialise a bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // 3. set onItemSelected listener
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_search -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.bottom_library -> {
                    replaceFragment(LibraryFragment())
                    true
                }
                R.id.Profile -> {
                    Toast.makeText(this, "Welcome to your Profile!", Toast.LENGTH_SHORT).show()
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // 4. Create an initial fragment
        replaceFragment(HomeFragment())
    }

    // 2. Create a replace method
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()

    //        myRecyclerView = findViewById(R.id.recyclerView);
//
//        val retrofitBuilder = Retrofit.Builder()
//            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitBuilder.getData("arijit")
//
//        retrofitData.enqueue(object : Callback<MyData?> {
//            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
//                val dataList = response.body()?.data!!
////                val textView = findViewById<TextView>(R.id.helloText)
////                textView.text = dataList.toString()
//
//                myAdapter= MyAdapter(this@MainActivity, dataList)
//                myRecyclerView.adapter = myAdapter
//                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                Log.d("TAG: onResponse", "onResponse: " + response.body())
//            }
//
//            override fun onFailure(call: Call<MyData?>, t: Throwable) {
//                Log.d("TAG: onFailure", "onFailure: "+ t.message)
//            }
//        })
    }
}