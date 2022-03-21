package id.pahlevikun.ilt3.sample.preference

import android.os.Bundle
import androidx.activity.viewModels
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.R
import id.pahlevikun.ilt3.sample.databinding.ActivityPreferenceMainBinding

class PreferenceMainActivity : BaseChildActivity() {

    private val view: ActivityPreferenceMainBinding by lazy {
        ActivityPreferenceMainBinding.inflate(layoutInflater)
    }
    private val factory: PreferenceViewModelFactory = PreferenceViewModelFactory.getInstance(this)
    private val viewModel: PreferenceViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        viewModel.color.observe(this) { color ->
            view.cvRoot.setBackgroundResource(color)
        }

        view.btnPrefBlack.setOnClickListener {
            viewModel.updateColor(R.color.black)
        }
        view.btnPrefWhite.setOnClickListener {
            viewModel.updateColor(R.color.white)
        }
    }
}