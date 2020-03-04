package fr.geraud.mycplusplusmovies.moviedetail

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import fr.geraud.mycplusplusmovies.Actor
import fr.geraud.mycplusplusmovies.R
import kotlinx.android.synthetic.main.actor.view.*
import timber.log.Timber

class ActorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val data = mutableListOf<Actor>()

    fun setData(newData : Collection<Actor>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.actor, parent, false)
            .let {
                ActorViewHolder(it)
            }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(data[position]) {
            holder.itemView.actorCaption.text = "$name, $age"
            loadImage(imageUrl, holder.itemView.actorImageView, holder.itemView.actorShimmer)
        }
    }

    private fun loadImage(imageUrl: String?, imageView: ImageView, shimmer: View) {
        shimmer.visibility = View.VISIBLE
        val options = RequestOptions()
            .error(R.drawable.actor_placeholder)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(imageView.context)
            .load(imageUrl)
            .listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.d("loadFailed()")
                    shimmer.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.d("resourceReady()")
                    shimmer.visibility = View.GONE
                    return false
                }
            })
            .apply(options)
            .into(imageView)
    }

    class ActorViewHolder(actorView: View) : RecyclerView.ViewHolder(actorView)
}