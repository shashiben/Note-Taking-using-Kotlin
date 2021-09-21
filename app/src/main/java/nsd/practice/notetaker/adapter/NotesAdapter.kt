package nsd.practice.notetaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import nsd.practice.notetaker.R
import nsd.practice.notetaker.entity.Notes

class NotesAdapter(val notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.itemTitle.text = notesList[position].title
        holder.itemView.itemSubtitle.text = notesList[position].subTitle
        holder.itemView.itemDate.text = notesList[position].dateTime
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}