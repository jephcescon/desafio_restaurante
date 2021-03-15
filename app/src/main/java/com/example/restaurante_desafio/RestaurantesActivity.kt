package com.example.restaurante_desafio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RestaurantesActivity : AppCompatActivity() {
    val recycler by lazy { findViewById<RecyclerView>(R.id.recycler_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)

        val alunos = getAlunos()

        recycler.layoutManager = LinearLayoutManager(this)

        //adapter with lambda function
        val adapter = AlunosAdapter(alunos) { aluno, position ->
            Toast.makeText(this@RestaurantesActivity, "$aluno, position = $position", Toast.LENGTH_LONG)
                .show()
        }

        //adapter with interface
        val adapterInterface = AlunosAdapterWithInterface(alunos, object : OnAlunoSelected{
            override fun retrieveAlunoAndPosition(aluno: Aluno, position: Int) {
                Toast.makeText(this@RestaurantesActivity, "$aluno, position = $position", Toast.LENGTH_LONG)
                    .show()
            }
        })

        recycler.adapter = adapter
    }

    private fun getAlunos(): MutableList<Aluno> {
        val alunosList = mutableListOf<Aluno>()

        for (index in 0..30) {
            val aluno = Aluno("Aluno $index", "Matricula $index")
            alunosList.add(aluno)
        }

        return alunosList
    }
}