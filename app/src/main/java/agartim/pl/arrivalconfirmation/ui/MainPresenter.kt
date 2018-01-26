package agartim.pl.arrivalconfirmation.ui

/**
 * Created by artur on 26.01.18.
 */
class MainPresenter : MainMVP.Presenter {

    var view: MainMVP.View? = null;

    override fun start(view: MainMVP.View) {
        this.view = view
    }

    override fun destroyView() {
        view = null
    }
}