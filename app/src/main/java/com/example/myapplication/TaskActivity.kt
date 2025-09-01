package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.TaskAdapter
import com.example.myapplication.model.Task

class TaskActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtDesc: EditText
    private lateinit var btnAdd: Button
    private lateinit var rvTasks: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var btnBack: Button

    private val tasks = mutableListOf(
        Task("Estudar POO", "Revisar classes e herança"),
        Task("Fazer relatório", "Capítulo 2 do PBL"),
        Task("Treinar SQL", "SELECT com JOIN")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        // Liga os componentes da tela
        edtName = findViewById(R.id.edtTaskName)
        edtDesc = findViewById(R.id.edtTaskDesc)
        btnAdd = findViewById(R.id.btnAddTask)
        rvTasks = findViewById(R.id.rvTasks)
        btnBack = findViewById(R.id.btnBackMovies)

        // Configura o adapter do RecyclerView
        adapter = TaskAdapter(tasks) { task, pos ->
            task.done = !task.done
            adapter.notifyChangedAt(pos)
        }

        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = adapter
        rvTasks.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        // Ação do botão de adicionar
        btnAdd.setOnClickListener {
            val name = edtName.text.toString().trim()
            val desc = edtDesc.text.toString().trim()
            if (name.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Preencha nome e descrição", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            adapter.add(Task(name, desc, false))
            edtName.text.clear()
            edtDesc.text.clear()
            rvTasks.smoothScrollToPosition(adapter.itemCount - 1)
        }

        // Ação do botão de voltar
        btnBack.setOnClickListener {
            finish() // Fecha TaskActivity e volta para MainActivity
        }
    }
}
