package com.example.myapplication  // ajuste se necessário

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.meusfilmes.adapter.MovieAdapter
import com.example.meusfilmes.model.Movie
import android.content.Intent



class MainActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDirector: EditText
    private lateinit var buttonAdd: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var checkAction: CheckBox
    private lateinit var checkComedy: CheckBox
    private lateinit var checkDrama: CheckBox
    private lateinit var checkRomance: CheckBox
    private lateinit var checkTerror: CheckBox
    private lateinit var checkAnimacao: CheckBox

    private lateinit var seekBarRating: SeekBar
    private lateinit var textRatingValue: TextView

    private val movieList = mutableListOf<Movie>()
    private var currentRating = 5 // default do SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views
        editTitle = findViewById(R.id.editTitle)
        editDirector = findViewById(R.id.editDirector)
        buttonAdd = findViewById(R.id.buttonAdd)
        recyclerView = findViewById(R.id.recyclerViewMovies)
        checkAction = findViewById(R.id.checkAction)
        checkComedy = findViewById(R.id.checkComedy)
        checkDrama = findViewById(R.id.checkDrama)
        checkRomance = findViewById(R.id.checkRomance)
        checkTerror = findViewById(R.id.checkTerror)
        checkAnimacao = findViewById(R.id.checkAnimacao)
        seekBarRating = findViewById(R.id.seekBarRating)
        textRatingValue = findViewById(R.id.textRatingValue)

        // RecyclerView
        adapter = MovieAdapter(movieList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // SeekBar listener
        seekBarRating.max = 10
        seekBarRating.progress = currentRating
        textRatingValue.text = "Avaliação: ${seekBarRating.progress}/10"

        seekBarRating.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                currentRating = progress
                textRatingValue.text = "Avaliação: $progress/10"
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        // Botão adicionar
        buttonAdd.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val director = editDirector.text.toString().trim()

            // Validação simples
            if (title.isEmpty()) {
                editTitle.error = "Informe o título"
                return@setOnClickListener
            }
            if (director.isEmpty()) {
                editDirector.error = "Informe o diretor"
                return@setOnClickListener
            }

            val genres = mutableListOf<String>()
            if (checkAction.isChecked) genres.add("Ação")
            if (checkComedy.isChecked) genres.add("Comédia")
            if (checkDrama.isChecked) genres.add("Drama")
            if (checkDrama.isChecked) genres.add("Romance")
            if (checkDrama.isChecked) genres.add("Terror")
            if (checkDrama.isChecked) genres.add("Animação")

            val movie = Movie(
                title = title,
                director = director,
                genres = genres,
                rating = currentRating
            )

            adapter.addMovie(movie)

            // Limpa campos
            editTitle.text.clear()
            editDirector.text.clear()
            checkAction.isChecked = false
            checkComedy.isChecked = false
            checkDrama.isChecked = false
            seekBarRating.progress = 5
        }

        val buttonGoToTasks: Button = findViewById(R.id.buttonGoToTasks)
        buttonGoToTasks.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }

    }
}
