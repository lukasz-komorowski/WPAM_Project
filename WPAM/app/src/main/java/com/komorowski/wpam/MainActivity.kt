package com.komorowski.wpam

import android.content.Intent
import com.komorowski.wpam.db.DatabaseHandler
import com.komorowski.wpam.model.Entry
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    companion object {
        var dbHandler: DatabaseHandler? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init db
        dbHandler = DatabaseHandler(this)

        //on Click IN button
        button_in.setOnClickListener(View.OnClickListener {
            newEntry("IN")
        })

        //on Click OUT button
        button_out.setOnClickListener(View.OnClickListener {
            newEntry("OUT")
        })

        //on Click DELETE button
        button_delete.setOnClickListener(View.OnClickListener {
            //switching to another activity (DeleteActivity)
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
        })

        //on Click SMS button
        button_message.setOnClickListener(View.OnClickListener {
            //switching to another activity (DeleteActivity)
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        })

        //on Click show button
        button_show.setOnClickListener(View.OnClickListener {
            var user = dbHandler!!.getAllEntries()
            textView_show.setText(user)
        })

    }
    fun entryTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm:ss")
        val formatted = current.format(formatter).toString()
        return formatted
    }

    fun newEntry(type: String): Boolean{
        val entry: Entry = Entry()
        var success: Boolean = false
        //entry.testText = "puste"
        entry.date = entryTime()
        entry.type = type
        success = dbHandler!!.addEntry(entry)
        if (success){
            val toast = Toast.makeText(this,"Saved " + type + " Successfully", Toast.LENGTH_LONG).show()
        }
        //TODO
        //remove comment (added to make testing easier)
        //editText_firstName.text.clear()

        return true
    }
}
//TODO
// - wysylanie wiadomosci do ludzi
// - GPS???
