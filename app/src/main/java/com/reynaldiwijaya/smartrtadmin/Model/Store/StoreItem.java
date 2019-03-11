package com.reynaldiwijaya.smartrtadmin.Model.Store;

import com.google.gson.annotations.SerializedName;

public class StoreItem{

	@SerializedName("no_tlp_store")
	private String no_tlp_store;

	@SerializedName("nama_lengkap")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("foto_toko")
	private String foto_toko;

	public String getFoto_toko() {
		return foto_toko;
	}

	@SerializedName("no_tlp")
	private String noTlp;

	@SerializedName("alamat_toko")
	private String alamatToko;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("konfirmasi")
	private String konfirmasi;

	@SerializedName("nama_toko")
	private String namaToko;

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNoTlp(String noTlp){
		this.noTlp = noTlp;
	}

	public String getNoTlp(){
		return noTlp;
	}

	public void setAlamatToko(String alamatToko){
		this.alamatToko = alamatToko;
	}

	public String getAlamatToko(){
		return alamatToko;
	}

	public void setIdToko(String idToko){
		this.idToko = idToko;
	}

	public String getIdToko(){
		return idToko;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setKonfirmasi(String konfirmasi){
		this.konfirmasi = konfirmasi;
	}

	public String getKonfirmasi(){
		return konfirmasi;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNo_tlp_store() {
		return no_tlp_store;
	}

	public void setNo_tlp_store(String no_tlp_store) {
		this.no_tlp_store = no_tlp_store;
	}
}