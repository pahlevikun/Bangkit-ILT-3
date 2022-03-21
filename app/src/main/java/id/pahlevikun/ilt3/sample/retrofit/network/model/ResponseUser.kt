package id.pahlevikun.ilt3.sample.retrofit.network.model

import com.google.gson.annotations.SerializedName
import id.pahlevikun.ilt3.sample.retrofit.presentation.model.DataItem

data class ResponseUser(
	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null
)