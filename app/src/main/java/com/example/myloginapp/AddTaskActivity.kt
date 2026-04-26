package com.example.myloginapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDesc = findViewById<EditText>(R.id.etDesc)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        val oldTask = intent.getStringExtra("task")
        val position = intent.getIntExtra("position", -1)

        if (oldTask != null) {
            etTitle.setText(oldTask)
        }

        if (oldTask != null) {
            btnDelete.visibility = Button.VISIBLE
        } else {
            btnDelete.visibility = Button.GONE
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val desc = etDesc.text.toString()

            if (title.isEmpty()) {
                Toast.makeText(this, "Isi task dulu!", Toast.LENGTH_SHORT).show()
            } else {
                val task = "$title\n$desc"

                val intent = Intent()
                intent.putExtra("task", task)
                intent.putExtra("position", position)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        btnDelete.setOnClickListener {
            val intent = Intent()
            intent.putExtra("delete", true)
            intent.putExtra("position", position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}