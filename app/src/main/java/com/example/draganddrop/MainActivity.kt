package com.example.draganddrop

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPointer()
    }

    private fun setPointer() {
        RecyclerViewinit()
    }

    private fun RecyclerViewinit() {
        //set layout manager
        val manager  = LinearLayoutManager(this)
        MyRecycleView.layoutManager = manager
        //set adapter
        val adapter = RecycleAdapter(itemdata.ItemSupplier.itemlist)
        MyRecycleView.adapter = adapter

        //todo check what this do
        val dividerItemDecoration = DividerItemDecoration(this , manager.orientation)
        MyRecycleView.addItemDecoration(dividerItemDecoration)


        // Setup ItemTouchHelper todo learn it
        val callback = DragManageAdapter(adapter, this,
            ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT))
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(MyRecycleView)
        

    }
}
