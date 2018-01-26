package agartim.pl.arrivalconfirmation.ui

/**
 * Created by artur on 26.01.18.
 */
interface MainMVP {

    interface Model {

    }

    interface View {

    }

    interface Presenter {
        fun start(view :View)
        fun destroyView()
    }
}