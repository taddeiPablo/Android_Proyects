package com.example.mymovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.databinding.ViewMovieItemBinding

// Aqui en este ejemplo creo un adapter para poder utilizarlo en el RecyclerView
// implementamos los metodos necesarios para utilizarlo asi como la definicion de un
// ViewHolder.
class MoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    // aqui declaro el ViewHolder necesario para trabajar, este objecto
    // es el componente dentro del adapter que va a contener la vista y a medida
    // que esta se redibuje se ira redibujando ViewHolder.
    //class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    //
    class ViewHolder(binding: ViewMovieItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            // aqui nos falta realizar el banding con el layout
        }
    }

    // aqui se va a crear una nueva vista, cuando el recyclerview se lo pida
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")

        // esta es una forma de inflar una view pero tambien se puede realizar de otra forma
        /*val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.view_movie_item, parent, false);
        return ViewHolder(view); */

        //
        val binding = ViewMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    // aqui devuelve el numero de elementos que contiene el adapter
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return movies.size;
    }
    // aqui se va a actualizar la vista cuando el adapter se lo pida
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
        holder.bind(movies[position])
    };
}