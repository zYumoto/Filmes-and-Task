package com.example.myapplication.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Task

class TaskAdapter(
    private val items: MutableList<Task>,
    private val onToggle: (Task, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    class TaskVH(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitle: TextView = v.findViewById(R.id.tvTaskTitle)
        val tvDesc: TextView = v.findViewById(R.id.tvTaskDesc)
        val tvStatus: TextView = v.findViewById(R.id.tvTaskStatus)
        val btnToggle: Button = v.findViewById(R.id.btnToggleDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskVH(v)
    }

    override fun onBindViewHolder(h: TaskVH, position: Int) {
        val t = items[position]
        h.tvTitle.text = t.title
        h.tvDesc.text = t.description
        h.tvStatus.text = if (t.isDone) "Status: Concluída" else "Status: Pendente"
        h.btnToggle.text = if (t.isDone) "Desfazer" else "Concluir"

        // Estilo riscado quando concluída
        h.tvTitle.paintFlags =
            if (t.isDone) h.tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else h.tvTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        h.btnToggle.setOnClickListener { onToggle(t, position) }
    }

    override fun getItemCount(): Int = items.size

    fun addTask(t: Task) {
        items.add(t)
        notifyItemInserted(items.size - 1)
    }

    fun updateAt(position: Int) {
        notifyItemChanged(position)
    }
}
