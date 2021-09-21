package nsd.practice.notetaker.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import nsd.practice.notetaker.R
import nsd.practice.notetaker.entity.Notes

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var listener: OnItemClickListener? = null
    var notesList = ArrayList<Notes>()

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    fun setData(arrNotesList: List<Notes>) {
        notesList = arrNotesList as ArrayList<Notes>

    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.itemTitle.text = notesList[position].title
        holder.itemView.itemSubtitle.text = notesList[position].subTitle
        holder.itemView.itemDate.text = notesList[position].dateTime
        if (notesList[position].color != null) {
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(notesList[position].color))
        } else {
            holder.itemView.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context, R.color.ColorLightBlack
                )
            )
        }
        if (notesList[position].imgPath != null) {
            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(notesList[position].imgPath))
            holder.itemView.imgNote.visibility = View.VISIBLE
        } else {
            holder.itemView.imgNote.visibility = View.GONE
        }
        if (notesList[position].webLink != "") {
            holder.itemView.tvWebLink.text = notesList[position].webLink
            holder.itemView.tvWebLink.visibility = View.VISIBLE
        } else {
            holder.itemView.tvWebLink.visibility = View.GONE
        }
        holder.itemView.cardView.setOnClickListener {
            listener!!.onClicked(notesList[position].id!!)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    interface OnItemClickListener {
        fun onClicked(noteId: Int)
    }

}