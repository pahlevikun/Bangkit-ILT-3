package id.pahlevikun.ilt3.sample.retrofit.presentation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import id.pahlevikun.ilt3.sample.R
import id.pahlevikun.ilt3.sample.databinding.LayoutItemRowUserBinding
import id.pahlevikun.ilt3.sample.retrofit.presentation.model.DataItem

class UserAdapter(
    private val users: MutableList<DataItem>,
    private val onItemClicked: ((data: DataItem) -> Unit),
) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = LayoutItemRowUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding, onItemClicked)
    }

    fun addUser(newUsers: DataItem) {
        users.add(newUsers)
        notifyItemInserted(users.lastIndex)
    }

    fun clear() {
        users.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataItem = users[position]
        holder.bind(dataItem)
    }

    class ListViewHolder(
        private val itemBinding: LayoutItemRowUserBinding,
        private val onItemClicked: ((data: DataItem) -> Unit),
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(dataItem: DataItem) {
            Glide.with(itemView.context)
                .load(dataItem.avatar)
                .apply(RequestOptions().override(AVATAR_SIZE, AVATAR_SIZE)
                    .placeholder(R.drawable.ic_avatar))
                .transform(CircleCrop())
                .into(itemBinding.ivAvatar)
            itemBinding.tvName.text = "${dataItem.firstName} ${dataItem.lastName}"
            itemBinding.tvEmail.text = dataItem.email
            itemBinding.cvRoot.setOnClickListener { onItemClicked.invoke(dataItem) }
        }
    }

    private companion object {
        const val AVATAR_SIZE: Int = 80
    }
}