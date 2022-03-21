package id.pahlevikun.ilt3.sample.database.presentation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.pahlevikun.ilt3.sample.database.data.entity.NotesEntity
import id.pahlevikun.ilt3.sample.databinding.LayoutItemRowNotesBinding

class NotesAdapter(
    private val users: MutableList<NotesEntity>,
    private val onItemClicked: ((data: NotesEntity) -> Unit),
) : RecyclerView.Adapter<NotesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = LayoutItemRowNotesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding, onItemClicked)
    }

    fun addNote(newUsers: NotesEntity) {
        users.add(newUsers)
        notifyItemInserted(users.lastIndex)
    }

    fun setNotes(newUsers: List<NotesEntity>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataItem = users[position]
        holder.bind(dataItem)
    }

    class ListViewHolder(
        private val itemBinding: LayoutItemRowNotesBinding,
        private val onItemClicked: ((data: NotesEntity) -> Unit),
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(dataItem: NotesEntity) {
            itemBinding.tvTitle.text = dataItem.title
            itemBinding.tvDescription.text = dataItem.description
            itemBinding.cvRoot.setOnClickListener { onItemClicked.invoke(dataItem) }
        }
    }

}