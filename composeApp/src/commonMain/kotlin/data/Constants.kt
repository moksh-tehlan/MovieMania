package data


val AUTH_KEY: String
    get() = getAuthKey()
val BASE_URL: String
    get() = getBaseUrl()
val IMAGE_URL: String
    get() = getImageURl()

expect fun getAuthKey(): String
expect fun getBaseUrl(): String
expect fun getImageURl(): String