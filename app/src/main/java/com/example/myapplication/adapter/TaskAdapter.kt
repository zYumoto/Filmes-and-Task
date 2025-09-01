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
    private val onToggleDone: (Task, Int) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

    inner class TaskVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvTaskName)
        val tvDesc: TextView = itemView.findViewById(R.id.tvTaskDesc)
        val btnDone: Button = itemView.findViewById(R.id.btnDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskVH(v)

    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = items[position]
        holder.tvName.text = task.name
        holder.tvDesc.text = task.description

        // visual de concluída: tachado
        holder.tvName.paintFlags =
            if (task.done) holder.tvName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else holder.tvName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        holder.tvDesc.paintFlags =
            if (task.done) holder.tvDesc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else holder.tvDesc.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        holder.btnDone.text = if (task.done) "Concluída" else "Concluir"

        holder.btnDone.setOnClickListener {
            onToggleDone(task, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = items.size

    fun add(task: Task) {
        items.add(task)
        notifyItemInserted(items.lastIndex)
    }

    fun notifyChangedAt(pos: Int) {
        if (pos in items.indices) notifyItemChanged(pos)
    }
}
