/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongkir;

/**
 *
 * @author Asus
 */
public class Barang implements Volume{
    private String pengirim, petugas;
    private double panjang, lebar, tinggi, berat;

    public String getPengirim() {
        return pengirim;
    }

    public String getPetugas() {
        return petugas;
    }

    public double getPanjang() {
        return panjang;
    }

    public double getTinggi() {
        return tinggi;
    }

    public String getMuatan() {
        return muatan;
    }

    public String getHarga() {
        return harga;
    }
    String muatan, harga ;

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public void setMuatan(String muatan) {
        this.muatan = muatan;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Barang(String pengirim, String petugas, double panjang,double lebar,double tinggi){
        //Konstruktor
        this.pengirim = pengirim;
        this.petugas = petugas;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
    }
    public double getpanjang(){
        return panjang;
    }
    public double getLebar(){
        return lebar;
    }
    public double tinggi(){
        return tinggi;
    }
    public double getBerat(){
        return getPanjang()*getLebar()*getTinggi()/4000;
    }
    @Override
    public String getMuatanBrg(double berat) {
        if (berat<4)
            muatan="Ringan";
        else if (berat<=6)
            muatan="Sedang";
        else if(berat<=9)
            muatan="Berat";
        else
            muatan="Expert";
        return muatan;
    }

    @Override
    public String getHarga(String muatan) {
        switch(muatan){
            case "Ringan": harga="Rp. 21.000";
            break;
            case "Sedang": harga="Rp. 35.000";
            break;
            case "Berat": harga="Rp. 56.000";
            break;
            default:harga="Harga disesuaikan";
        }
        return harga;
    }
    
}
