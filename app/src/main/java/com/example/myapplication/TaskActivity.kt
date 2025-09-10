package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.TaskAdapter
import com.example.myapplication.model.Task

class TaskActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDesc: EditText
    private lateinit var btnAdd: Button
    private lateinit var rv: RecyclerView
    private lateinit var adapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        editTitle = findViewById(R.id.editTaskTitle)
        editDesc  = findViewById(R.id.editTaskDesc)
        btnAdd    = findViewById(R.id.buttonAddTask)
        rv        = findViewById(R.id.recyclerViewTasks)

        adapter = TaskAdapter(tasks) { task, pos ->
            task.isDone = !task.isDone
            adapter.updateAt(pos)
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        btnAdd.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val desc  = editDesc.text.toString().trim()

            if (title.isEmpty()) { editTitle.error = "Informe o título"; return@setOnClickListener }
            if (desc.isEmpty())  { editDesc.error  = "Informe a descrição"; return@setOnClickListener }

            adapter.addTask(Task(title, desc, false))
            editTitle.text.clear()
            editDesc.text.clear()
        }
    }
}
