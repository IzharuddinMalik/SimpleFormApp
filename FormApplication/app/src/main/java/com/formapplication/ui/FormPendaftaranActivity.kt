package com.formapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.formapplication.R
import com.formapplication.databinding.ActivityFormPendaftaranBinding
import com.formapplication.model.DataModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FormPendaftaranActivity : AppCompatActivity() {

    private var _binding : ActivityFormPendaftaranBinding? = null
    private val binding get() = _binding!!

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    private var dataModel : DataModel? = null
    private var arrProdi = arrayListOf<String>("Informatika", "Psikologi", "Ekonomi", "Kedokteran", "PGSD")
    private var arrAgama = arrayListOf<String>("Islam", "Kristen", "Katholik", "Budha", "Hindu", "Konghucu")
    private var strGender : String? = ""
    private var strProdi : String? = ""
    private var strAgama : String? = ""

    private var temp_key: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFormPendaftaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataModel = DataModel()

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.getReference("Siswa")

        binding.rbLakilaki.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                strGender = "Laki-laki"
                binding.rbPerempuan.isChecked = false
            }
        }

        binding.rbPerempuan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                strGender = "Perempuan"
                binding.rbLakilaki.isChecked = false
            }
        }

        var adapter = this?.let { ArrayAdapter(this, R.layout.item_list_spin, arrProdi) }
        adapter?.setDropDownViewResource(R.layout.item_list_spin)
        binding.spinProdi.setAdapter(adapter)

        binding.spinProdi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                strProdi = arrProdi[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        var adapterAgama = this?.let { ArrayAdapter(this, R.layout.item_list_spin, arrAgama) }
        adapterAgama?.setDropDownViewResource(R.layout.item_list_spin)
        binding.spinAgama.setAdapter(adapterAgama)

        binding.spinAgama.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                strAgama = arrAgama[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        binding.btnDaftar.setOnClickListener {

            var noPendaftaran = binding.edtNoPendaftaran.text.toString()
            var namaSiswa = binding.edtNamaSiswa.text.toString()
            var tempatLahir = binding.edtTempatLahir.text.toString()
            var tanggalLahir = binding.edtTanggalLahir.text.toString()
            var alamatLengkap = binding.edtAlamatLengkap.text.toString()
            var noTelepon = binding.edtNoTelepon.text.toString()
            var email = binding.edtEmail.text.toString()

            tambahDataKeFirebase(noPendaftaran, strProdi!!, namaSiswa, strGender!!, tempatLahir, tanggalLahir, strAgama!!, alamatLengkap, noTelepon, email)
        }
    }

    fun tambahDataKeFirebase(noPendataran : String, prodi : String, namaSiswa : String, gender : String, tempatLahir : String, tanggalLahir : String, agama : String, alamatLengkap : String, noTelepon : String, email : String) {
        val map: Map<String, Any> = HashMap()
        temp_key = databaseReference!!.push().key
        databaseReference!!.updateChildren(map)

        val message_root = databaseReference!!.child(temp_key!!)
        val map2: MutableMap<String, Any> = HashMap()
        map2["noPendaftaran"] = noPendataran
        map2["programStudi"] = prodi
        map2["namaSiswa"] = namaSiswa
        map2["jenisKelamin"] = gender
        map2["tempatLahir"] = tempatLahir
        map2["tanggalLahir"] = tanggalLahir
        map2["agama"] = agama
        map2["alamatLengkap"] = alamatLengkap
        map2["noTelepon"] = noTelepon
        map2["emailSiswa"] = email

        message_root.updateChildren(map2)

        startActivity(Intent(this, MainActivity::class.java))
    }
}