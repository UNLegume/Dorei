package com.example.dorei_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewHolder.ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hoges = resources.getStringArray(R.array.hoges).toMutableList()

        mainRecyclerView.adapter = RecyclerAdapter(this, this, hoges)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

                val fromPosition = viewHolder.adapterPosition ?: 0
                val toPosition = target.adapterPosition ?: 0

                mainRecyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                hoges.removeAt(viewHolder.adapterPosition)
                viewHolder.let {
                    mainRecyclerView.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }
        })
        itemTouchHelper.attachToRecyclerView(mainRecyclerView)
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(applicationContext, "position $position was tapped", Toast.LENGTH_SHORT).show()
    }


}