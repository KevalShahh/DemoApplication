package com.example.demoapplication


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapplication.databinding.ActivityRetrofitBinding
import kotlinx.android.synthetic.main.activity_external_storage.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    lateinit var foodlist:List<FoodModel>
    lateinit var viewBinding:ActivityRetrofitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityRetrofitBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        /*APIObject.instance?.post("keval")?.enqueue(object :Callback<List<FoodModel>>{
            override fun onResponse(
                call: Call<List<FoodModel>>,
                response: Response<List<FoodModel>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })*/
        APIObject.instance?.getMenu()?.enqueue(object : Callback<List<FoodModel>>{
            override fun onResponse(
                call: Call<List<FoodModel>>,
                response: Response<List<FoodModel>>
            ) {
                foodlist= response.body()!!
                Log.d("TAG", "onResponse: "+foodlist.size)
                val adapter=CustomRetrofitRvAdapter(this@RetrofitActivity,foodlist)
                viewBinding.recyclerviewRetrofit.adapter=adapter
                viewBinding.recyclerviewRetrofit.layoutManager=LinearLayoutManager(this@RetrofitActivity)
            }
            override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
            }
        })
    }
}