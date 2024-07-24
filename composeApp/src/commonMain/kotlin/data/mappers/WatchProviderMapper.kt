package data.mappers

import data.model.FlatRate
import domain.model.WatchProvider

fun FlatRate.toWatchProvider(): WatchProvider {
    return WatchProvider(
        logoPath,
        providerName,
    )
}