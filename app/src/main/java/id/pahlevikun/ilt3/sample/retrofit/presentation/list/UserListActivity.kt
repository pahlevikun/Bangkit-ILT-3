package id.pahlevikun.ilt3.sample.retrofit.presentation.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.pahlevikun.ilt3.sample.BaseChildActivity
import id.pahlevikun.ilt3.sample.databinding.ActivityUserListBinding
import id.pahlevikun.ilt3.sample.getIntOrZero
import id.pahlevikun.ilt3.sample.getJsonArrayOrEmpty
import id.pahlevikun.ilt3.sample.getStringOrEmpty
import id.pahlevikun.ilt3.sample.retrofit.network.ApiConfig
import id.pahlevikun.ilt3.sample.retrofit.network.model.ResponseUser
import id.pahlevikun.ilt3.sample.retrofit.presentation.details.UserDetailsActivity
import id.pahlevikun.ilt3.sample.retrofit.presentation.model.DataItem
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : BaseChildActivity() {

    private val binding: ActivityUserListBinding by lazy {
        ActivityUserListBinding.inflate(layoutInflater)
    }
    private val userAdapter: UserAdapter by lazy {
        UserAdapter(mutableListOf()) { item ->
            val intent = Intent(this, UserDetailsActivity::class.java)
                .apply { putExtra(UserDetailsActivity.EXTRA_KEY, item) }
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        fetchUsersAndParseWithGson()
        fetchUsersAndParseManually()
    }

    private fun initRecyclerView() {
        binding.rvUsers.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UserListActivity)
            adapter = userAdapter
        }
    }

    private fun fetchUsersAndParseWithGson() {
        showLoader()
        val client = ApiConfig
            .getApiService(this)
            .fetchUsersAndParseWithGson("1")
        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                hideLoader()
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data as List<DataItem>
                    for (data in dataArray) userAdapter.addUser(data)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                hideLoader()
                Toast.makeText(this@UserListActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d("SampleRetrofit",
                    "${UserListActivity::class.java.simpleName} >>> ${t.message}")
            }
        })
    }

    private fun fetchUsersAndParseManually() {
        showLoader()
        val client = ApiConfig
            .getApiService(this)
            .fetchUsersAndParseManually("2")
        client.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                hideLoader()
                if (response.isSuccessful) {
                    val rawJson = JSONObject(response.body()?.string().orEmpty())
                    val dataArray = rawJson.getJsonArrayOrEmpty("data")
                    for (i in 0 until dataArray.length()) {
                        val dataObject = dataArray.getJSONObject(i)
                        val data = DataItem(
                            id = dataObject.getIntOrZero("id"),
                            email = dataObject.getStringOrEmpty("email"),
                            firstName = dataObject.getStringOrEmpty("first_name"),
                            lastName = dataObject.getStringOrEmpty("last_name"),
                            avatar = dataObject.getStringOrEmpty("avatar"),
                        )
                        userAdapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                hideLoader()
                Toast.makeText(this@UserListActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d("SampleRetrofit",
                    "${UserListActivity::class.java.simpleName} >>> ${t.message}")
            }
        })
    }

    private fun showLoader() {
        binding.loader.visibility = View.VISIBLE
        binding.rvUsers.visibility = View.GONE
    }

    private fun hideLoader() {
        binding.loader.visibility = View.GONE
        binding.rvUsers.visibility = View.VISIBLE
    }
}
