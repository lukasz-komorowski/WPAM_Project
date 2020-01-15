package com.komorowski.wpam

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.textView_show
import kotlinx.android.synthetic.main.activity_message.*
import java.util.jar.Manifest


class MessageActivity : AppCompatActivity() {

    private val requestSendSMS: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val actionbar = supportActionBar
        actionbar!!.title = "New Activity"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        //on Click send button
        button_send.setOnClickListener(View.OnClickListener {
//            var user = MainActivity.dbHandler!!.getAllEntries()
//            textView_show.setText(user)

            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS), requestSendSMS)

            }else {
                sendSMS()
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestSendSMS) sendSMS()
    }

    fun sendSMS()
    {
        if(editText_phoneNumber.text.toString() != "") {
            val messageToSend = editText_messageContent.text.toString()
            val number = editText_phoneNumber.text.toString()
            val smsManager = SmsManager.getDefault() as SmsManager
            smsManager.sendTextMessage(number, null, messageToSend, null, null)
            //SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null)
            Toast.makeText(this, "SMS sent", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Please provide phone number", Toast.LENGTH_SHORT).show()
        }

    }
}
