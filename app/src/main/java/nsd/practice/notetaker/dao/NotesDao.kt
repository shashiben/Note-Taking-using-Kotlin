package nsd.practice.notetaker.dao

import androidx.room.*
import nsd.practice.notetaker.entity.Notes

@Dao
interface NotesDao {
    @get:Query("Select * from notes order by id desc")
    val getAllNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Notes)

    @Delete
    fun deleteNote(note: Notes)

}