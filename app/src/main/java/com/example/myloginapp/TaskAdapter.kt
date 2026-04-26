package com.example.myloginapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter

class TaskAdapter(
    context: Context,
    private val taskList: ArrayList<String>
) : ArrayAdapter<String>(context, 0, taskList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_task, parent, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkTask)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        val task = taskList[position]

        val parts = task.split("\n")

        checkBox.text = parts[0]
        if (parts.size > 1) {
            tvDesc.text = parts[1]
        }

        return view
    }
}