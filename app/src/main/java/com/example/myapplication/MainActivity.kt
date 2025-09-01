package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var edtTitle: EditText
    private lateinit var edtDirector: EditText
    private lateinit var btnAdd: Button
    private lateinit var rvMovies: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var btnGoTasks: Button

    private val movies = mutableListOf(
        Movie("Interstellar", "Christopher Nolan"),
        Movie("Whiplash", "Damien Chazelle"),
        Movie("Cidade de Deus", "Fernando Meirelles")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTitle = findViewById(R.id.edtTitle)
        edtDirector = findViewById(R.id.edtDirector)
        btnAdd = findViewById(R.id.btnAdd)
        rvMovies = findViewById(R.id.rvMovies)
        btnGoTasks = findViewById(R.id.btnGoTasks)

        adapter = MovieAdapter(movies)
        rvMovies.layoutManager = LinearLayoutManager(this)
        rvMovies.adapter = adapter
        rvMovies.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        btnGoTasks.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }

        btnAdd.setOnClickListener {
            val title = edtTitle.text.toString().trim()
            val director = edtDirector.text.toString().trim()
            if (title.isEmpty() || director.isEmpty()) {
                Toast.makeText(this, "Preencha título e diretor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            adapter.add(Movie(title, director))
            edtTitle.text.clear()
            edtDirector.text.clear()
            rvMovies.smoothScrollToPosition(adapter.itemCount - 1)
        }
    }
}
