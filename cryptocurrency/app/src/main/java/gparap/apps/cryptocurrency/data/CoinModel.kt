package gparap.apps.cryptocurrency.data

/** Model data class for a cryptocurrency. */
data class CoinModel(
    var id: String,
    var symbol: String,
    var name: String,
    var color: String,
    var iconUrl: String,
    var marketCap: String,
    var price: String,
    var change: String,
    var rank: String,
    var volume24h: String,
)
