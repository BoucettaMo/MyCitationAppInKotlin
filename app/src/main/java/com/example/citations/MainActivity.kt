package com.example.citations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var addCitation: Button
    private lateinit var panorama: Button
    private lateinit var adapter: MyAdapter
    private lateinit var list: MutableList<String>
    private lateinit var dbManager: DbManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         title="Citations"
        recyclerView = findViewById(R.id.recyclerview)
        editText = findViewById(R.id.citation)
        addCitation = findViewById(R.id.addCitation)
        panorama = findViewById(R.id.panorama)
        dbManager = DbManager(this)
        list = dbManager.readDb()

        adapter = MyAdapter(this,list)
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        recyclerView.adapter = adapter

        addCitation.setOnClickListener {
            if (editText.text.isEmpty()) {
                Toast.makeText(this,"Please enter a citation",Toast.LENGTH_LONG).show()
            }
            else {
                dbManager.addCitation(editText.text.toString())
                editText.setText("")
                this.recreate()
            }
        }

        panorama.setOnClickListener {
            val array = ArrayList<String>()
            array.addAll(dbManager.readDb())
            val intent = Intent(this@MainActivity,PanoramaActivity:: class.java)
            intent.putStringArrayListExtra("list",array)
            startActivity(intent)

        }


    }
}