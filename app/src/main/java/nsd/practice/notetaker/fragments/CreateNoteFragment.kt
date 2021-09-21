package nsd.practice.notetaker.fragments

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import nsd.practice.notetaker.R
import nsd.practice.notetaker.database.NotesDatabase
import nsd.practice.notetaker.entity.Notes
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {

    private var currentDate: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        currentDate = dateFormat.format(Date())
        dateField.text = currentDate
        done.setOnClickListener {
            saveNote()
        }
        back.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(), false)
        }
    }

    private fun saveNote() {
        if (titleField.text.trim().isEmpty()) {
            Toast.makeText(context, "Please enter title", Toast.LENGTH_SHORT).show()
        } else if (subtitleField.text.trim().isEmpty()) {
            Toast.makeText(context, "Please enter subtitle", Toast.LENGTH_SHORT).show()
        } else {
            launch {
                val newNote = Notes(
                )
                newNote.title = titleField.text.toString()
                newNote.subTitle = subtitleField.text.toString()
                newNote.noteText = noteDesc.text.toString()
                newNote.dateTime = currentDate
                context?.let {
                    NotesDatabase.getDatabase(it).noteDao().insertNotes(newNote)
                    titleField.text.clear()
                    noteDesc.text.clear()
                    subtitleField.text.clear()
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment, isTransition: Boolean) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        if (isTransition) {
            fragmentTransaction.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )

        }
        fragmentTransaction.add(R.id.frameLayout, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }
}