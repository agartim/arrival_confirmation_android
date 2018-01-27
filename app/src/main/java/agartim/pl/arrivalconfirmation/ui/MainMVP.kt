package agartim.pl.arrivalconfirmation.ui

/**
 * Created by artur on 26.01.18.
 */
interface MainMVP {

    interface Model {
        fun setMessage(smsMessage: String)
        fun getMessage(): String

        fun addPhoneNumber(phoneNumber: String)
        fun getPhoneNumbers(): List<String>

        fun isMessageValid(): Boolean
        fun isNumbersValid(): Boolean
    }

    interface View {
        fun setMessage(smsMessage: String)
        fun getMessage(): String

        fun setNumbers(phoneNumbers: List<String>)

        fun showNotValidParamsInfo()
        fun sendSmsMessage(phoneNumber: String, smsMessage: String)
    }

    interface Presenter {
        fun start(view: View)
        fun destroyView()

        fun clickSendSms()
        fun addPhoneNumber(phoneNumber: String)
        fun saveMessage(smsMessage: String)
    }
}