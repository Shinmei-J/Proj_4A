package com.firstest.kotapp.presentation.List

import android.os.Bundle
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstest.kotapp.R
import com.firstest.kotapp.data.local.models.GenshinChar
import com.firstest.kotapp.presentation.Status.ApiFailure
import com.firstest.kotapp.presentation.Status.ApiSuccess
import org.koin.android.ext.android.inject


class ListActivity : AppCompatActivity() {

    val listViewModel: ListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listpage)

        listViewModel.makeApiCall()

        listViewModel.apiLiveData.observe(this, Observer {
            when(it){
                is ApiSuccess -> showList(it.genshinList)
                ApiFailure -> Toast.makeText(this@ListActivity, "Failure", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun showList (genshinList: List<GenshinChar>){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val mAdapter = ListAdapter(genshinList)
        recyclerView.adapter = mAdapter

    }
}