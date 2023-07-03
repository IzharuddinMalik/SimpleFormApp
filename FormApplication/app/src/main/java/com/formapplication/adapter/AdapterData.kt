package com.formapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formapplication.databinding.ItemListDataBinding
import com.formapplication.model.DataModel

class AdapterData(private var itemCell : ArrayList<DataModel>) : RecyclerView.Adapter<AdapterData.DataViewHolder>() {

    private lateinit var context: Context
    private var _binding : ItemListDataBinding? = null
    private val binding get() = _binding!!

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var view = LayoutInflater.from(parent.context)
        _binding = ItemListDataBinding.inflate(view)
        val ksv = DataViewHolder(binding.root)
        context = parent.context
        return ksv
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        binding.tvListNoPendaftaran.text = itemCell[position].noPendaftaran
        binding.tvListNamaSiswa.text = itemCell[position].namaSiswa
        binding.tvListJenisKelamin.text = itemCell[position].jenisKelamin
        binding.tvListAgama.text = itemCell[position].agama
        binding.tvListTempatLahir.text = itemCell[position].tempatLahir
        binding.tvListTanggalLahir.text = itemCell[position].tanggalLahir
        binding.tvListEmail.text = itemCell[position].emailSiswa
        binding.tvListNoTelepon.text = itemCell[position].noTelepon
        binding.tvListAlamatLengkap.text = itemCell[position].alamatLengkap
        binding.tvListProdi.text = itemCell[position].programStudi
    }

    override fun getItemCount(): Int {
        return itemCell.size
    }

}