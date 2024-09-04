package gparap.apps.cryptocurrency.data

import com.google.gson.annotations.SerializedName

/** Model data class for a cryptocurrency. */
data class CoinModel(
    var id: String,
    var symbol: String,
    var name: String,
    var color: String,
    @SerializedName("icon_url") var iconUrl: String,
    @SerializedName("market_cap") var marketCap: String,
    var price: String,
    var change: String,
    var rank: String,
    @SerializedName("volume_24h") var volume24h: String,
)
