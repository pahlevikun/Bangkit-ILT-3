package id.pahlevikun.ilt3.sample.architecture

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.R

class ArchitectureMainActivity : BaseChildActivity() {

    private val viewModel by viewModels<ArchitectureMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_architecture_main)
        viewModel.data.observe(this) {
            showToast("Value from observer $it")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        viewModel.update()
        showToast(viewModel.test())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}