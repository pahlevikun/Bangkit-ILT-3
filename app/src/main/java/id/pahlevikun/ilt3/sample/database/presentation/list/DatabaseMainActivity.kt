package id.pahlevikun.ilt3.sample.database.presentation.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.database.presentation.DatabaseViewModelFactory
import id.pahlevikun.ilt3.sample.database.presentation.details.DatabaseDetailsActivity
import id.pahlevikun.ilt3.sample.databinding.ActivityDatabaseMainBinding

class DatabaseMainActivity : BaseChildActivity() {

    private val view: ActivityDatabaseMainBinding by lazy {
        ActivityDatabaseMainBinding.inflate(layoutInflater)
    }
    private val factory: DatabaseViewModelFactory by lazy {
        DatabaseViewModelFactory.getInstance(this, DatabaseMainViewModel::class.java)
    }
    private val viewModel: DatabaseMainViewModel by viewModels { factory }

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter(mutableListOf()) { item ->
            val intent = Intent(this, DatabaseDetailsActivity::class.java)
                .apply { putExtra(DatabaseDetailsActivity.EXTRA_KEY, item) }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        initRecyclerView()
        view.btnAddNote.setOnClickListener { startActivity(Intent(this, DatabaseDetailsActivity::class.java)) }
        viewModel.getNotes().observe(this) { notes ->
            notesAdapter.setNotes(notes)
        }
    }

    private fun initRecyclerView() {
        view.rvNotes.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DatabaseMainActivity)
            adapter = notesAdapter
        }
    }
}