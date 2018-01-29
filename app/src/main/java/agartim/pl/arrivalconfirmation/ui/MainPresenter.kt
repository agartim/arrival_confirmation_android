package agartim.pl.arrivalconfirmation.ui

import android.content.SharedPreferences

/**
 * Created by artur on 26.01.18.
 */
class MainPresenter(val prefs: SharedPreferences) : MainMVP.Presenter {

    var view: MainMVP.View? = null;
    val model = MainModel(prefs)

    override fun start(view: MainMVP.View) {
        this.view = view
        view.setNumbers(model.getPhoneNumbers())
        view.setMessage(model.getMessage())
    }

    override fun destroyView() {
        view = null
    }

    override fun clickSendSms() {
        if (model.isMessageValid() && model.isNumbersValid()) {
            val smsMessage = model.getMessage()
            model.getPhoneNumbers().forEach({ it -> view?.sendSmsMessage(it, smsMessage) })
        } else {
            view?.showNotValidParamsInfo()
        }
    }

    override fun addPhoneNumber(phoneNumber: String) {
        if (!isNumberPresentInList(phoneNumber)) {
            model.addPhoneNumber(phoneNumber)
            view?.setNumbers(model.getPhoneNumbers())
        }
    }

    override fun saveMessage(smsMessage: String) {
        model.setMessage(smsMessage)
    }

    override fun clearAllNumbers() {
        model.removeAllPhoneNumbers()
        view?.setNumbers(model.getPhoneNumbers())
    }

    fun isNumberPresentInList(phoneNumber: String) = model.getPhoneNumbers().contains(phoneNumber)
}