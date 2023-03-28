package com.example.natasya26

import Siswa
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class editactivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editactivity)

        var id = intent?.getStringExtra("id")
        val simpanbtn = findViewById<Button>(R.id.simpanbtn)
        val namaSiswa = findViewById<EditText>(R.id.namaEdit)
        val nisSiswa = findViewById<EditText>(R.id.nisEdit)

        namaSiswa.setText(intent?.getStringExtra("nama"))
        nisSiswa.setText(intent?.getStringExtra("nis"))

        simpanbtn.setOnClickListener() {
            val db = DBHelper(this, null)
            if (id == null) {
                db.addSiswa(namaSiswa.text.toString(), nisSiswa.text.toString())
            } else {
                db.updateSiswa(Siswa(id, namaSiswa.text.toString(), nisSiswa.text.toString()))
            }
            db.close()
            finish()
        }
    }
}