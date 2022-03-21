package id.pahlevikun.ilt3.sample.retrofit.presentation.details

import android.annotation.SuppressLint
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.R
import id.pahlevikun.ilt3.sample.databinding.ActivityUserDetailsBinding
import id.pahlevikun.ilt3.sample.retrofit.presentation.model.DataItem

class UserDetailsActivity : BaseChildActivity() {

    private val binding: ActivityUserDetailsBinding by lazy {
        ActivityUserDetailsBinding.inflate(layoutInflater)
    }

    private val item by lazy {
        (intent.extras?.getParcelable(EXTRA_KEY) as? DataItem) ?: DataItem()
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this)
            .load(item.avatar)
            .apply(RequestOptions()
                .override(AVATAR_SIZE, AVATAR_SIZE)
                .placeholder(R.drawable.ic_avatar)
            ).transform(CircleCrop()).into(binding.ivAvatar)
        binding.tvEmail.text = item.email
        binding.tvName.text = "${item.firstName} ${item.lastName}"
    }

    companion object {
        const val EXTRA_KEY = "intent.extra.key"
        private const val AVATAR_SIZE: Int = 80
    }
}
