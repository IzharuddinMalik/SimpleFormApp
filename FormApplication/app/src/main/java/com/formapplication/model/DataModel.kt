package com.formapplication.model

data class DataModel(
    var noPendaftaran : String?,
    var programStudi : String?,
    var namaSiswa : String?,
    var jenisKelamin : String?,
    var tempatLahir : String?,
    var tanggalLahir : String?,
    var agama : String?,
    var alamatLengkap : String?,
    var noTelepon : String?,
    var emailSiswa : String?
) {
    constructor() : this(null, null, null, null, null, null,null,
    null, null, null)
}