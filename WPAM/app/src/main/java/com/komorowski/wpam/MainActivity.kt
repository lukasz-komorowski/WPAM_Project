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

    var dbHandler: DatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init db
        dbHandler = DatabaseHandler(this)

        //on Click Save button
        button_save.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            val entry: Entry = Entry()
            var success: Boolean = false
            entry.testText = editText_firstName.text.toString()
            entry.date = editText_firstName.text.toString()
            entry.type = "SAVED"
            success = dbHandler!!.addEntry(entry)
            if (success){
                val toast = Toast.makeText(this,"Saved Successfully", Toast.LENGTH_LONG).show()
            }
            //TODO
            //remove comment (added to make testing easier)
            //editText_firstName.text.clear()
        })

        //on Click IN button
        button_in.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            val entry: Entry = Entry()
            var success: Boolean = false
            entry.testText = editText_firstName.text.toString()
            entry.date = editText_firstName.text.toString()
            entry.type = "IN"
            success = dbHandler!!.addEntry(entry)
            if (success){
                val toast = Toast.makeText(this,"Saved IN Successfully", Toast.LENGTH_LONG).show()
            }
            //TODO
            //remove comment (added to make testing easier)
            //editText_firstName.text.clear()
        })

        //on Click OUT button
        button_out.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            val entry: Entry = Entry()
            var success: Boolean = false
            entry.testText = editText_firstName.text.toString()
            entry.date = editText_firstName.text.toString()
            entry.type = "OUT"
            success = dbHandler!!.addEntry(entry)
            if (success){
                val toast = Toast.makeText(this,"Saved OUT Successfully", Toast.LENGTH_LONG).show()
            }
            //TODO
            //remove comment (added to make testing easier)
            //editText_firstName.text.clear()
        })

        //on Click DELETE button
        button_delete.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            if (!editText_id.text.toString().equals("")){
                var success: Boolean = false
                val deleteID = editText_id.text.toString()

                success = dbHandler!!.deleteRow(deleteID)

                if (success){
                    val toast = Toast.makeText(this,"Deleted Successfully " + deleteID, Toast.LENGTH_LONG).show()
                    editText_id.text.clear()
                    var user = dbHandler!!.getAllEntries()
                    textView_show.setText(user)
                }
            }

            //TODO
            //add delete activity
            //val intent = Intent(this, Delete::class.java)
            //startActivity(intent)

        })

        //on Click show button
        button_show.setOnClickListener(View.OnClickListener {
            var user = dbHandler!!.getAllEntries()
            textView_show.setText(user)
        })

    }
//    fun time(): String{

//        val current = LocalDateTime.now()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        val formatted = current.format(formatter).toString()
//    }
}