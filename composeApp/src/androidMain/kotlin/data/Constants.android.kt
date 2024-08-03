package data

import com.moksh.moviemania.BuildConfig

actual fun getAuthKey(): String {
    return BuildConfig.API_KEY
}

actual fun getBaseUrl(): String {
    return BuildConfig.BASE_URL
}

actual fun getImageURl(): String {
    return BuildConfig.IMAGE_URL
}