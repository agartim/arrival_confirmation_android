package agartim.pl.arrivalconfirmation

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by artur on 27.01.18.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }

}