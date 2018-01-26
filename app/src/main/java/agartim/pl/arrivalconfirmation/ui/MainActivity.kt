package agartim.pl.arrivalconfirmation.ui

import agartim.pl.arrivalconfirmation.R
import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.R.attr.phoneNumber
import android.telephony.SmsManager


class MainActivity : AppCompatActivity(), MainMVP.View {

    val MY_PERMISSIONS_REQUEST_SEND_SMS = 11

    lateinit var sharedPreferences: SharedPreferences
    val PREFS_MESSAGE: String = "sms_message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        message.setText(sharedPreferences.getString(PREFS_MESSAGE, ""))

        addNumberButton.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        sendButton.setOnClickListener { view ->
            (
                    if (shouldAskSendSMSPermission()) {
                        if (isSendSMSPermissionGranted())
                            sendSmses()
                        else askForSendSMSPermission()
                    } else sendSmses()
                    )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        readAndSaveMessage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_SEND_SMS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    sendSmses()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Sorry, couldn't send sms", Toast.LENGTH_LONG).show()
                }
                return
            }

        // Add other 'when' lines to check for other
        // permissions this app might request.

            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun readAndSaveMessage() = sharedPreferences.edit().putString(PREFS_MESSAGE, message.text.toString()).apply()

    private fun shouldAskSendSMSPermission() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    private fun isSendSMSPermissionGranted() = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)

    private fun askForSendSMSPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.SEND_SMS),
                MY_PERMISSIONS_REQUEST_SEND_SMS)
    }

    private fun sendSmses() {
        Toast.makeText(this, "Send smses", Toast.LENGTH_LONG).show()
        val sms = SmsManager.getDefault()
        val numberAga = "504618756"

        sms.sendTextMessage(numberAga, null, message.text.toString(), null, null)
    }
}
