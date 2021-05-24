package gparap.apps.public_holidays.model

data class HolidayModel(
    val countryCode: String,
    val date: String,
    val localName: String,
    val name: String,
    val isDateFixed: Boolean,
    val isGlobal: Boolean,
    val launchYear: Int?
)
