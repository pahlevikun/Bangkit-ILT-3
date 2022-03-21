package id.pahlevikun.ilt3.sample.retrofit.network

import id.pahlevikun.ilt3.sample.retrofit.network.model.ResponseUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/users")
    fun fetchUsersAndParseWithGson(@Query("page") page: String): Call<ResponseUser>

    @GET("api/users")
    fun fetchUsersAndParseManually(@Query("page") page: String): Call<ResponseBody>
}