package gparap.apps.horoscope.adapters

import android.content.Context
import android.widget.ArrayAdapter

object SpinnerAdapter {
    /**
     * Creates an ArrayAdapter using the string array and a default spinner layout
     */
    fun create(context: Context, textArrayResId: Int)
            : ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(
            context, textArrayResId, android.R.layout.simple_spinner_dropdown_item)
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
    }
}