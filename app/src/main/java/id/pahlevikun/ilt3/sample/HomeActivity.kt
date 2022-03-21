package id.pahlevikun.ilt3.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.pahlevikun.ilt3.sample.architecture.ArchitectureMainActivity
import id.pahlevikun.ilt3.sample.database.presentation.list.DatabaseMainActivity
import id.pahlevikun.ilt3.sample.databinding.ActivityHomeBinding
import id.pahlevikun.ilt3.sample.preference.PreferenceMainActivity
import id.pahlevikun.ilt3.sample.retrofit.presentation.list.UserListActivity

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnNetworking.setOnClickListener {
            open(UserListActivity::class.java)
        }

        binding.btnArchitecture.setOnClickListener {
            open(ArchitectureMainActivity::class.java)
        }

        binding.btnPreference.setOnClickListener {
            open(PreferenceMainActivity::class.java)
        }

        binding.btnDatabase.setOnClickListener {
            open(DatabaseMainActivity::class.java)
        }
    }

    private fun <T> open(clazz: Class<T>) = startActivity(Intent(this, clazz))
}