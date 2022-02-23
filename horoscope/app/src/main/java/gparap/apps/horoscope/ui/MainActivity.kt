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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import gparap.apps.horoscope.R
import gparap.apps.horoscope.adapters.SpinnerAdapter
import gparap.apps.horoscope.data.HoroscopeModel
import gparap.apps.horoscope.utils.Utils
import gparap.apps.horoscope.viewmodels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create the ViewModel and setup what we will need for this activity
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.setRequestedDay(resources.getString(R.string.text_today))
        viewModel.setZodiacSign(this.resources.getString(R.string.text_prompt_select_spinner))
        viewModel.createWebService()

        //setup spinner with adapter
        val spinner = findViewById<Spinner>(R.id.spinner_zodiac_signs)
        spinner.adapter = SpinnerAdapter.create(this, R.array.zodiac_signs)
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //do nothing if nothing is selected
        if (parent?.getItemAtPosition(position).toString()
            == this.resources.getString(R.string.text_prompt_select_spinner)
        ) return

        //set the selected zodiac sign
        viewModel.setZodiacSign(parent?.getItemAtPosition(position).toString())

        //get the horoscope
        createWebServiceRequest(viewModel.getRequestedDay(), viewModel.getZodiacSign())
        requestHoroscopeFromWebService()

        //hide prompt text
        findViewById<TextView>(R.id.text_view_prompt_select_sign).apply {
            visibility = View.GONE
        }

        //show the horoscope view
        findViewById<ScrollView>(R.id.scroll_view_horoscope).apply {
            visibility = View.VISIBLE
        }

        //set the zodiac sign drawable
        findViewById<ImageView>(R.id.image_view_zodiac_sign).apply {
            this.setImageResource(
                Utils.getZodiacSignDrawableResourceIdByName(
                    this@MainActivity,
                    parent?.getItemAtPosition(position).toString()
                )
            )
        }

        //set the zodiac sign date range
        findViewById<TextView>(R.id.text_view_date_range).apply {
            this.setText(
                Utils.getZodiacSignDateRangeStringByName(
                    this@MainActivity,
                    parent?.getItemAtPosition(position).toString()
                )
            )
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_day, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //get the requested day for the horoscope
        viewModel.setRequestedDay(Utils.getRequestedDayById(this, item.itemId))

        //get the horoscope only if a zodiac sign is actually selected
        if (viewModel.getZodiacSign() != this.resources.getString(R.string.text_prompt_select_spinner)) {
            createWebServiceRequest(viewModel.getRequestedDay(), viewModel.getZodiacSign())
            requestHoroscopeFromWebService()
        }

        return super.onOptionsItemSelected(item)
    }

    //create a web service request based on selected day and zodiac sign
    private fun createWebServiceRequest(day: String?, sign: String?) {
        when (day) {
            //get today's horoscope
            resources.getString(R.string.text_today) ->
                viewModel.setApiResponse(viewModel.getWebService()?.getHoroscopeForToday(sign)
                )

            //get tomorrow's horoscope
            resources.getString(R.string.text_tomorrow) ->
                viewModel.setApiResponse(viewModel.getWebService()?.getHoroscopeForTomorrow(sign))

            //get yesterdays's horoscope
            resources.getString(R.string.text_yesterday) ->
                viewModel.setApiResponse(viewModel.getWebService()?.getHoroscopeForYesterday(sign))
        }
    }

    //call web service and get horoscope
    private fun requestHoroscopeFromWebService() {
        viewModel.getApiResponse()?.enqueue(object : Callback<HoroscopeModel> {
            override fun onResponse(
                call: Call<HoroscopeModel>,
                response: Response<HoroscopeModel>,
            ) {
                displayHoroscope(response.body() as HoroscopeModel)
            }

            override fun onFailure(call: Call<HoroscopeModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    //display the full horoscope details
    private fun displayHoroscope(model: HoroscopeModel) {
        val date = findViewById<TextView>(R.id.text_view_date)
        date.text = model.date
        val horoscope = findViewById<TextView>(R.id.text_view_horoscope)
        horoscope.text = model.horoscope
        val number = findViewById<TextView>(R.id.text_view_lucky_number)
        number.text = model.luckyNumber
        val time = findViewById<TextView>(R.id.text_view_lucky_time)
        time.text = model.luckyTime
        val color = findViewById<TextView>(R.id.text_view_lucky_color)
        color.text = model.luckyColor
        val pair = findViewById<TextView>(R.id.text_view_pair_sign)
        pair.text = model.pair
    }
}