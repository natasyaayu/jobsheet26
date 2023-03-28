package com.example.natasya26

import Siswa
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
  class SiswaAdapter(private val data: ArrayList<Siswa>?): RecyclerView.Adapter<SiswaAdapter.siswaViewHolder>() {

    class siswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nama = itemView.findViewById<TextView>(R.id.namaSiswa)
        private val nis = itemView.findViewById<TextView>(R.id.nisSiswa)
        private val editbtn = itemView.findViewById<TextView>(R.id.btnEdit)
        private val hapusbtn = itemView.findViewById<TextView>(R.id.btnHapus)
        fun bind(get: Siswa) {
            nama.text = get.nama
            nis.text = get.nis

            editbtn.setOnClickListener() {
                val intent = Intent(itemView.context, editactivity::class.java)
                intent.putExtra("id", get.id)
                intent.putExtra("nama", get.nama)
                intent.putExtra("nis", get.nis)
                itemView.context.startActivity(intent)
            }

            hapusbtn.setOnClickListener() {
                val dialogBuilder = AlertDialog.Builder(itemView.context)
                dialogBuilder.setTitle("Hapus Data")
                dialogBuilder.setMessage("Hapus Siswa " + get.nama)
                dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                    val db = DBHelper(itemView.context, null)
                    val status = db.deleteSiswa(get.id)
                    if (status > -1) Toast.makeText(
                        itemView.context,
                        "data dihapus",
                        Toast.LENGTH_LONG
                    ).show()
                })
                dialogBuilder.setNegativeButton("cencel", DialogInterface.OnClickListener { _, _ ->
                })

                dialogBuilder.create()
                dialogBuilder.show()
            }
        }
    }

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): siswaViewHolder {
          return siswaViewHolder (LayoutInflater.from(parent.context).inflate(R.layout.list_siswa, parent, false))
      }

      override fun onBindViewHolder(holder: siswaViewHolder, position: Int) {
          holder.bind(data?.get(position) ?: Siswa("", "", ""))
      }

      override fun getItemCount(): Int {
          return data?.size ?: 0

      }
}



