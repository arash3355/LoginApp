package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private val taskList = ArrayList<String>()
    private val REQUEST_CODE = 1

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listView = findViewById(R.id.listTask)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        adapter = TaskAdapter(this, taskList)
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra("task", taskList[position])
            intent.putExtra("position", position)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val task = data?.getStringExtra("task")
            val position = data?.getIntExtra("position", -1) ?: -1

            if (task != null) {
                if (position != -1) {
                    taskList[position] = task
                } else {
                    taskList.add(task)
                }
                adapter.notifyDataSetChanged()
            }
        }

        val isDelete = data?.getBooleanExtra("delete", false)

        if (isDelete == true) {
            val pos = data.getIntExtra("position", -1)
            if (pos != -1) {
                taskList.removeAt(pos)
                adapter.notifyDataSetChanged()
            }
        }
    }
}