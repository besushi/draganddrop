package com.example.draganddrop

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card.view.*



class RecycleAdapter(var items : MutableList<itemdata>):  RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {





    private var swipedPosition: Int = 0
    private var swipedItem: itemdata? = null

    override fun onCreateViewHolder(viewgroup: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(viewgroup.context).inflate(R.layout.card, viewgroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
       val item = items[position]
        viewholder.setDataToRecycleViewItems(item,position)
    }


    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                items.set(i, items.set(i+1, items.get(i)));
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                items.set(i, items.set(i-1, items.get(i)));
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }



    fun removeItem(position: Int, viewHolder: RecyclerView.ViewHolder) {
        swipedItem = items[position]
        swipedPosition = position

        items.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(viewHolder.itemView, "${swipedItem!!.title} removed", Snackbar.LENGTH_LONG).setAction("UNDO") {
            items.add(swipedPosition, swipedItem!!)
            notifyItemInserted(swipedPosition)

        }.show()
    }


     fun StartnewActivity(context: Context,position: Int) {

         swipedPosition = position
         notifyItemChanged(swipedPosition)

        val intent = Intent(context, Activity2::class.java)
        context.startActivity(intent)


    }


    



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var currentitem: itemdata? = null
        var currentPosition: Int = 0




        fun setDataToRecycleViewItems(item: itemdata, position: Int) {

                     itemView.title.text = item.title


            this.currentitem = item
            this.currentPosition = position
        }
    }
    
}