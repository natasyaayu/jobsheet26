package com.example.natasya26

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        var id = intent?.getStringExtra("id")
         val editbtn = findViewById<TextView>(R.id.btnEdit)
         val hapusbtn = findViewById<TextView>(R.id.btnHapus)
         val namaSiswa = findViewById<TextView>(R.id.namaSiswa)
         val nisSiswa = findViewById<TextView>(R.id.nisSiswa)

        namaSiswa.setText(intent?.getStringExtra("nama"))
        nisSiswa.setText(intent?.getStringExtra("nis"))

        editbtn.setOnClickListener() {
            val intent = Intent(this, editactivity::class.java)
            namaSiswa.setText(intent?.getStringExtra("nama"))
            nisSiswa.setText(intent?.getStringExtra("isi"))
            startActivity(intent)
        }

        hapusbtn.setOnClickListener() {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Hapus Data")
            dialogBuilder.setMessage("Hapus Siswa " + namaSiswa.text)
            dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                val db = DBHelper(this, null)
                val status = db.deleteSiswa(id!!)
                if (status > -1) Toast.makeText(
                    this,
                    "data dihapus",
                    Toast.LENGTH_LONG
                ).show()
            })
            dialogBuilder.setNegativeButton("cencel", DialogInterface.OnClickListener { dialog, which ->  } )
            dialogBuilder.create()
            dialogBuilder.show()
        }
    }
}
