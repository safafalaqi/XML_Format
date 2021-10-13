package com.example.xmlformat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var myRv: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myRv  = findViewById(R.id.rvStudent)
        var students: List<Student>? = null

        try{
            val parser = MyXmlPullParserHandler()
            val iStream = assets.open("studentdetails.xml")
            students = parser.parse(iStream)

        }catch (e: IOException) {
            println("ISSUE: $e")
        }

        rvAdapter=RVAdapter(students!!)
        myRv.adapter = rvAdapter
        myRv.layoutManager = LinearLayoutManager(applicationContext)

    }
}