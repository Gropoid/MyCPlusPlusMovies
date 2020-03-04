package fr.geraud.mycplusplusmovies.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.geraud.mycplusplusmovies.Movie
import fr.geraud.mycplusplusmovies.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(val onItemClicked: (name: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = mutableListOf<Movie>()

    fun setData(movies: List<Movie>) {
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
            .let { MovieViewHolder(it) }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        with(data[position]) {
            holder.itemView.movieItemName.text = name
            holder.itemView.movieItemLastUpdated.text = lastUpdated.toString()
            holder.itemView.setOnClickListener { onItemClicked(name) }
        }

    class MovieViewHolder(movieView: View) : RecyclerView.ViewHolder(movieView)
}