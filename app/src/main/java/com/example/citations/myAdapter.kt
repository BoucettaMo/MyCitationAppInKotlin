package com.example.citations

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MyAdapter(private val activity: MainActivity,private val list:MutableList<String>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.model,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayColor= arrayOf(R.color.green,R.color.red,R.color.ground,R.color.teal_200,R.color.blue,
        R.color.blue1,R.color.blue2,R.color.teal_200,R.color.yellow,R.color.yellow1,R.color.yellow2,
        R.color.turquoise,R.color.peter,R.color.emerald,R.color.pine,R.color.peach,
        R.color.oasis,R.color.socks)
        holder.textview.text = list[position]
        val m = Random.nextInt(18)
        holder.linearLayout.setBackgroundColor(ContextCompat.getColor(activity,arrayColor[m]))
        holder.button.setOnClickListener {
            val builder = AlertDialog.Builder(activity)

            with(builder) {
                setTitle("Warning")
                setMessage("Do you really want to delete this citation?")
                setPositiveButton("Yes"){
                        _, _ ->
                    val dbManager = DbManager(activity)
                    dbManager.deleteCitation(list[position])
                    activity.recreate()
                }
                setNegativeButton("No") {
                    _,_,->
                }
                show()
            }



        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textview: TextView = itemView.findViewById(R.id.textView)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        val button: Button = itemView.findViewById(R.id.delete)

    }


}
