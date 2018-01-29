package agartim.pl.arrivalconfirmation.ui

import android.content.SharedPreferences
import kotlin.collections.ArrayList

/**
 * Created by artur on 26.01.18.
 */
class MainModel(val prefs: SharedPreferences) : MainMVP.Model {

    private val PREFS_MESSAGE = "message"
    private val PREFS_NUMBERS = "numbers"
    private val NUMBERS_SEPARATOR: String = ";"

    private var message = ""

    override fun setMessage(smsMessage: String) {
        prefs.edit().putString(PREFS_MESSAGE, smsMessage).apply()
        message = smsMessage
    }

    override fun getMessage(): String {
        message = prefs.getString(PREFS_MESSAGE, "")
        return message
    }

    override fun addPhoneNumber(phoneNumber: String) {
        var phoneNumbers = ArrayList<String>()
        phoneNumbers.add(phoneNumber)

        phoneNumbers.addAll(getPhoneNumbers())
        prefs.edit().putString(PREFS_NUMBERS, phoneNumbers.joinToString(NUMBERS_SEPARATOR)).apply()
    }

    override fun getPhoneNumbers(): List<String> {
        val prefsNumbersString = prefs.getString(PREFS_NUMBERS, "");
        if (prefsNumbersString.isEmpty()) {
            return emptyList()
        }
        return prefsNumbersString.split(NUMBERS_SEPARATOR)
    }

    override fun isMessageValid() = message.length >= 3

    override fun isNumbersValid(): Boolean {
        if (getPhoneNumbers().isEmpty())
            return false

        var isValid = true
        getPhoneNumbers().forEach({ it -> isValid = it.length >= 9 })
        return isValid
    }

    override fun removeAllPhoneNumbers() {
        prefs.edit().putString(PREFS_NUMBERS, "").apply()
    }
}