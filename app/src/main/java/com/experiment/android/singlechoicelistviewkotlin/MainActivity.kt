package com.experiment.android.singlechoicelistviewkotlin

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var itemListView: ListView
    private lateinit var btnConfirm: Button
    private lateinit var itemsList: MutableList<ItemsModel>
    private lateinit var listAdapter: ItemsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListView = findViewById(R.id.lv_list)
        btnConfirm = findViewById(R.id.btn_confirm)

        createDataSet()

        listAdapter = ItemsListAdapter(this, itemsList)
        listAdapter.selectedPosition = 1

        itemListView.adapter = listAdapter

        btnConfirm.setOnClickListener {
            println("Selected Item Position ---> ${listAdapter.selectedPosition}")
            println("Selected Item ---> " + (listAdapter.getItem(listAdapter.selectedPosition) as ItemsModel).name)
        }

        itemListView.onItemClickListener = onItemClickListener()
    }

    private fun onItemClickListener(): OnItemClickListener {
        return OnItemClickListener { parent, _, position, _ ->
            listAdapter.selectedPosition = position
            listAdapter.notifyDataSetChanged()

            println("Selected item position onItemClickListener ---> $position")
            println("Selected item onItemClickListener ---> " + (parent.getItemAtPosition(position) as ItemsModel).name)
        }
    }

    private fun createDataSet() {
        itemsList = ArrayList<ItemsModel>()
        itemsList.apply {
            add(ItemsModel("Tamil Nadu", "TN"))
            add(ItemsModel("Telangana", "TS"))
            add(ItemsModel("Andhra Pradesh", "AP"))
        }
    }
}