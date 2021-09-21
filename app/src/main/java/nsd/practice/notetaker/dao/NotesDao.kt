package nsd.practice.notetaker.dao

import androidx.room.*
import nsd.practice.notetaker.entity.Notes

@Dao
interface NotesDao {
    @Query("Select * from notes order by id desc")
    suspend fun getAllNotes(): List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(note: Notes)

    @Query("SELECT * FROM notes WHERE id =:id")
    suspend fun getNoteById(id: Int): Notes

    @Query("DELETE FROM notes WHERE id =:id")
    suspend fun deleteNoteByID(id: Int)

    @Update
    suspend fun updateNote(note: Notes)

}