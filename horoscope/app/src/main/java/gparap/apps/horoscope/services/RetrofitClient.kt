package gparap.apps.horoscope.services

import gparap.apps.horoscope.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    /** Creates the Retrofit client instance. */
    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_AZTRO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Creates the Retrofit client instance for MyMemory web service. */
    fun buildTranslationService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_MY_MEMORY)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}