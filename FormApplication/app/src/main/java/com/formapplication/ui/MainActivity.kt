package com.formapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.formapplication.adapter.AdapterData
import com.formapplication.databinding.ActivityMainBinding
import com.formapplication.model.DataModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    private var agama : String? = ""
    private var alamatLengkap : String? = ""
    private var emailSiswa : String? = ""
    private var jenisKelamin : String? = ""
    private var namaSiswa : String? = ""
    private var noPendaftaran : String? = ""
    private var noTelepon : String? = ""
    private var programStudi : String? = ""
    private var tanggalLahir : String? = ""
    private var tempatLahir : String? = ""

    private var arrDataSiswa : ArrayList<DataModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        arrDataSiswa = ArrayList()

        binding.btnTambahData.setOnClickListener {
            startActivity(Intent(this, FormPendaftaranActivity::class.java))
        }

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference().child("Siswa")
        databaseReference!!.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                appendDataSiswa(dataSnapshot)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                appendDataSiswa(dataSnapshot)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

    private fun appendDataSiswa(dataSnapshot: DataSnapshot) {
        val i: Iterator<*> = dataSnapshot.children.iterator()
        while (i.hasNext()) {
            agama = (i.next() as DataSnapshot).value as String?
            alamatLengkap = (i.next() as DataSnapshot).value as String?
            emailSiswa = (i.next() as DataSnapshot).value as String?
            jenisKelamin = (i.next() as DataSnapshot).value as String?
            namaSiswa = (i.next() as DataSnapshot).value as String?
            noPendaftaran = (i.next() as DataSnapshot).value as String?
            noTelepon = (i.next() as DataSnapshot).value as String?
            programStudi = (i.next() as DataSnapshot).value as String?
            tanggalLahir = (i.next() as DataSnapshot).value as String?
            tempatLahir = (i.next() as DataSnapshot).value as String?
            arrDataSiswa!!.add(DataModel(noPendaftaran, programStudi, namaSiswa, jenisKelamin, tempatLahir, tanggalLahir, agama, alamatLengkap, noTelepon, emailSiswa))
            binding.rvListData.apply {
                binding.rvListData.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.rvListData.adapter = AdapterData(arrDataSiswa!!)
            }
        }
    }

}