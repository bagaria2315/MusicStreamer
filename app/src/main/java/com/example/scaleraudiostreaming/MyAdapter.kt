package com.example.scaleraudiostreaming

import android.app.Activity
import android.media.Image
import android.media.MediaPlayer
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList:List<Data>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create the view in case the layout manager fails to create view for the data
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return  MyViewHolder(itemView);
    }

    override fun getItemCount(): Int {
        return dataList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // populate the data into the view
        val currentData = dataList[position]

//        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri());
//        holder.musicTtl.text = currentData.title;
        Picasso.get().load(currentData.album.cover).into(holder.musicImg);

        holder.musicTtl.text = currentData.title

        // check if this item is currently playing or not
        if (position == currentPlayingPosition) {
            updateButtonState(holder.pause)
        } else {
            holder.pause.setImageResource(android.R.drawable.ic_media_play)
        }

        holder.previous.setOnClickListener {
            currentPlayingPosition?.let {
                val previousPosition = if (it > 0) it -1 else dataList.size - 1
                stopMediaPlayer(it)
                startMediaPlayer(previousPosition, holder.pause)
                notifyItemChanged(previousPosition)
            }
        }

        holder.pause.setOnClickListener {
            if (position == currentPlayingPosition) {
                // Toggle play/pause
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                } else {
                    mediaPlayer?.start()
                }
                updateButtonState(holder.pause)
            } else {
                // Start playing new audio
                currentPlayingPosition?.let { stopMediaPlayer(it) }
                startMediaPlayer(position, holder.pause)
                updateButtonState(holder.pause)
            }
        }

        holder.next.setOnClickListener {
            currentPlayingPosition?.let{
                val nextPosition = (it + 1) % dataList.size
                stopMediaPlayer(it)
                startMediaPlayer(nextPosition, holder.pause)
                notifyItemChanged(nextPosition)
            }
        }
    }

    private fun stopMediaPlayer(position: Int) {
        mediaPlayer?.release()
        mediaPlayer = null
        currentPlayingPosition = null
        notifyItemChanged(position)
    }

    private fun updateButtonState(button: ImageButton){
        if(mediaPlayer?.isPlaying == true){
            button.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            button.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    private fun startMediaPlayer(position: Int, button: ImageButton){
        mediaPlayer = MediaPlayer.create(context, dataList[position].preview.toUri())
        mediaPlayer?.start()
        currentPlayingPosition = position
        mediaPlayer?.setOnCompletionListener {
            // Reset UI when playback completes
            button.setImageResource(android.R.drawable.ic_media_play)
            currentPlayingPosition = null
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val musicImg: ImageView
        val musicTtl: TextView
//        val play: ImageButton
        val previous: ImageButton
        val pause: ImageButton
        val next: ImageButton

        init{
            musicImg = itemView.findViewById(R.id.musicImage);
            musicTtl = itemView.findViewById(R.id.musicTitle);
            previous = itemView.findViewById(R.id.btnPrev)
//            play = itemView.findViewById(R.id.btnPlay);
            pause = itemView.findViewById(R.id.btnPause);
            next = itemView.findViewById(R.id.btnNext);
        }
    }
}