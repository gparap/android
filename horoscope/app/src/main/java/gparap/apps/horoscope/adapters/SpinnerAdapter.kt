package gparap.apps.horoscope.adapters

import android.content.Context
import android.widget.ArrayAdapter
import gparap.apps.horoscope.R

object SpinnerAdapter {
    /**
     * Creates an ArrayAdapter using the string array and a default spinner layout
     */
    fun create(context: Context, textArrayResId: Int)
            : ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            context, textArrayResId, R.layout.spinner_dropdown_item)
            .also {
                it.setDropDownViewResource(R.layout.spinner_dropdown_item)
            }
    }
}