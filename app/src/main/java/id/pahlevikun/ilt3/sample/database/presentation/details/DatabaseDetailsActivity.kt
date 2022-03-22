package id.pahlevikun.ilt3.sample.database.presentation.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.R
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity
import id.pahlevikun.ilt3.sample.database.presentation.DatabaseViewModelFactory
import id.pahlevikun.ilt3.sample.databinding.ActivityDatabaseDetailsBinding

class DatabaseDetailsActivity : BaseChildActivity() {

    private val view: ActivityDatabaseDetailsBinding by lazy {
        ActivityDatabaseDetailsBinding.inflate(layoutInflater)
    }
    private val factory: DatabaseViewModelFactory = DatabaseViewModelFactory
        .getInstance(this, DatabaseDetailsViewModel::class.java)
    private val viewModel: DatabaseDetailsViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        val notesEntity by lazy { intent.getParcelableExtra<NotesEntity>(EXTRA_KEY) }
        if (notesEntity != null) {
            view.etTitle.setText(notesEntity?.title)
            view.etDescription.setText(notesEntity?.description)
            view.btnAddNote.setText(R.string.database_btn_title_update)
            view.btnDeleteNote.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    viewModel.deleteNote(notesEntity ?: return@setOnClickListener)
                    finish()
                }
            }
        } else {
            view.btnAddNote.setText(R.string.database_btn_title_save)
            view.btnDeleteNote.visibility = View.GONE
        }

        view.btnAddNote.setOnClickListener {
            val newItem = if (notesEntity != null) {
                notesEntity!!.copy(
                    title = view.etTitle.text.toString(),
                    description = view.etDescription.text.toString(),
                )
            } else {
                NotesEntity(
                    title = view.etTitle.text.toString(),
                    description = view.etDescription.text.toString(),
                )
            }
            viewModel.saveNote(newItem)
            finish()
        }
    }

    companion object {
        const val EXTRA_KEY = "intent.extra.key"
    }
}