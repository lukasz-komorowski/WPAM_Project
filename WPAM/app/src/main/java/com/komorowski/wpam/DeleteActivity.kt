package com.komorowski.wpam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_main.button_delete
import kotlinx.android.synthetic.main.activity_main.button_show
import kotlinx.android.synthetic.main.activity_main.textView_show

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val actionbar = supportActionBar
        actionbar!!.title = "Delete entry"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        //initial show
        var user = MainActivity.dbHandler!!.getAllEntries()
        textView_show.setText(user)
        scrollView_show.fullScroll(View.FOCUS_DOWN)



        //on Click DELETE button
        button_delete.setOnClickListener(View.OnClickListener {
            // checking input text should not be null
            if (!editText_id_del.text.toString().equals("")){
                var success: Boolean = false
                val deleteID = editText_id_del.text.toString()

                success = MainActivity.dbHandler!!.deleteRow(deleteID)

                if (success){
                    val toast = Toast.makeText(this,"Deleted Successfully " + deleteID, Toast.LENGTH_LONG).show()
                    editText_id_del.text.clear()
                    var user = MainActivity.dbHandler!!.getAllEntries()
                    textView_show.setText(user)
                    scrollView_show.fullScroll(View.FOCUS_DOWN)
                }
            }
        })

        //on Click show button
        button_show.setOnClickListener(View.OnClickListener {
            var user = MainActivity.dbHandler!!.getAllEntries()
            textView_show.setText(user)
            scrollView_show.fullScroll(View.FOCUS_DOWN)
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
