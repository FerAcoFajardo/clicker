package acosta.fernando.clicker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var counter: Int = 0
    var text: String = ""
    lateinit var num: TextView
    lateinit var field: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlus: ImageButton = findViewById(R.id.btnPlus)
        val btnMinus: ImageButton = findViewById(R.id.btnMinus)
        val btnRestore: ImageButton = findViewById(R.id.btnRestore)

        field = findViewById(R.id.editTextField)
        num = findViewById(R.id.textViewCounter)


        btnMinus.setOnClickListener{
            counter--
            num.setText("$counter")
        }

        btnPlus.setOnClickListener{
            counter++
            num.setText("$counter")
        }

        btnRestore.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("Clean",
                        DialogInterface.OnClickListener{ dialog, i ->
                            counter = 0
                            num.setText("$counter")
                            field.setText("")
                        })
                    setNegativeButton("Cancel",
                        DialogInterface.OnClickListener{ dialog: DialogInterface, i: Int -> }
                    )
                }

                builder?.setMessage("¿Seguro desea reninicar el contador?").setTitle("Confirmar")
                builder.create()
            }
            alertDialog?.show()
        }


    }

    override fun onPause() {
        super.onPause()

        val sharedPref2 = this?.getPreferences(Context.MODE_PRIVATE)?: return
        var editor2 = sharedPref2.edit()
        text = field.text.toString()

        editor2.putString("text", text)
        editor2.putInt("counter", counter)
        editor2.commit()
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        text = sharedPref.getString("text", "").toString()
        counter = sharedPref.getInt("counter", 0)
        num.setText("$counter")
        field.setText("$text")
    }

}