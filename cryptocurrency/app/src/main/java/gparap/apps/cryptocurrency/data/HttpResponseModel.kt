package gparap.apps.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/** Model data class for the cryptocurrency API http response. */
data class HttpResponseModel(
    @SerializedName("code") var statusCode: String,
    @SerializedName("data") var cryptoData: List<CoinModel>
)
