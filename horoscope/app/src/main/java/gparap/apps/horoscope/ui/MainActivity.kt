/*
 * Copyright (c) 2022 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.horoscope.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.horoscope.R
import gparap.apps.horoscope.adapters.SpinnerAdapter
import gparap.apps.horoscope.data.HoroscopeModel
import gparap.apps.horoscope.services.AztroService
import gparap.apps.horoscope.services.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var service: AztroService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup spinner with adapter
        val spinner = findViewById<Spinner>(R.id.spinner_zodiac_signs)
        spinner.adapter = SpinnerAdapter.create(this, R.array.zodiac_signs)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)

        //create web service
        service = RetrofitClient.build().create(AztroService::class.java)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //do nothing if nothing is selected
        if (parent?.getItemAtPosition(position).toString()
            == this.resources.getString(R.string.text_prompt_select_spinner)
        ) {
            return
        }

        //call web service and get horoscope based on selected zodiac sign
        service.getHoroscopeForToday(parent?.getItemAtPosition(position).toString())
            .enqueue(object : Callback<HoroscopeModel> {
                override fun onResponse(
                    call: Call<HoroscopeModel>,
                    response: Response<HoroscopeModel>,
                ) {
                    //get horoscope object
                    val horoscopeObj = response.body() as HoroscopeModel

                    //display horoscope details
                    val date = findViewById<TextView>(R.id.text_view_date)
                    date.text = horoscopeObj.date
                    val horoscope = findViewById<TextView>(R.id.text_view_horoscope)
                    horoscope.text = horoscopeObj.horoscope
                    val number = findViewById<TextView>(R.id.text_view_lucky_number)
                    number.text = horoscopeObj.luckyNumber
                    val time = findViewById<TextView>(R.id.text_view_lucky_time)
                    time.text = horoscopeObj.luckyTime
                    val color = findViewById<TextView>(R.id.text_view_lucky_color)
                    color.text = horoscopeObj.luckyColor
                    val pair = findViewById<TextView>(R.id.text_view_pair_sign)
                    pair.text = horoscopeObj.pair
                }

                override fun onFailure(call: Call<HoroscopeModel>, t: Throwable) {
                    //inform the user that something went wrong
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            })

        //hide prompt text and show the horoscope view
        findViewById<TextView>(R.id.text_view_prompt_select_sign).apply {
            visibility = View.GONE
        }
        findViewById<ScrollView>(R.id.scroll_view_horoscope).apply {
            visibility = View.VISIBLE
        }

        //set the zodiac sign and its date range
        when (parent?.getItemAtPosition(position).toString()) {
            //Aries
            this.resources.getString(R.string.text_zodiac_Aries) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.aries)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Aries)
                }
            }
            //Taurus
            this.resources.getString(R.string.text_zodiac_Taurus) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.taurus)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Taurus)
                }
            }
            //Gemini
            this.resources.getString(R.string.text_zodiac_Gemini) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.gemini)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Gemini)
                }
            }
            //Cancer
            this.resources.getString(R.string.text_zodiac_Cancer) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.cancer)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Cancer)
                }
            }
            //Leo
            this.resources.getString(R.string.text_zodiac_Leo) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.leo)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Leo)
                }
            }
            //Virgo
            this.resources.getString(R.string.text_zodiac_Virgo) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.virgo)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Virgo)
                }
            }
            //Libra
            this.resources.getString(R.string.text_zodiac_Libra) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.libra)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Libra)
                }
            }
            //Scorpio
            this.resources.getString(R.string.text_zodiac_Scorpio) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.scorpio)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Scorpio)
                }
            }
            //Sagittarius
            this.resources.getString(R.string.text_zodiac_Sagittarius) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.sagittarius)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Sagittarius)
                }
            }
            //Capricorn
            this.resources.getString(R.string.text_zodiac_Capricorn) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.capricorn)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Capricorn)
                }
            }
            //Aquarius
            this.resources.getString(R.string.text_zodiac_Aquarius) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.aquarius)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Aquarius)
                }
            }
            //Pisces
            this.resources.getString(R.string.text_zodiac_Pisces) -> {
                findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
                    this.setImageResource(R.drawable.pisces)
                }
                findViewById<TextView>(R.id.text_view_date_range).apply {
                    this.setText(R.string.date_range_Pisces)
                }
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}