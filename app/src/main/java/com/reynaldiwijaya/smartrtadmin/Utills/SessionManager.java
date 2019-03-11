package com.reynaldiwijaya.smartrtadmin.Utills;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.reynaldiwijaya.smartrtadmin.Ui.MainActivity;
import com.reynaldiwijaya.smartrtadmin.View.LoginActivity;


public class SessionManager  {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String KEY_USERNAME = "username";
    private static final String is_login = "loginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager(Context context) {
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    /**
     * Membuat method yang berfungsi untuk menyimpan data username dan *****
     */

    public void storeLogin(String user) {
        editor.putBoolean(is_login, true);
        editor.putString(KEY_USERNAME, user);
        editor.commit();
    }

    public void setImageUser(String imageUser) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.IMAGE_USER, imageUser);
        editor.commit();
    }

    public String getImageUser() {
        return sp.getString(Constants.IMAGE_USER, "");
    }

    public void setEmailUser(String emailUser) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.EMAIL, emailUser);
        editor.commit();
    }

    public String getEmailUser() {
        return sp.getString(Constants.EMAIL, "");
    }

    public void setNama(String nama) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.NAMA, nama);
        editor.commit();
    }

    public String getNama() {
        return sp.getString(Constants.NAMA, "");
    }

    public void setNoKtp(String no_ktp) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.NO_KTP, no_ktp);
        editor.commit();
    }

    public String getNoKtp() {
        return sp.getString(Constants.NO_KTP, "");
    }

    public void setAlamat(String alamat) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.ALAMAT, alamat);
        editor.commit();
    }

    public String getAlamat() {
        return sp.getString(Constants.ALAMAT, "");
    }

    public void setStatus(String status) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.STATUS, status);
        editor.commit();
    }

    public String getStatus() {
        return sp.getString(Constants.STATUS, "");
    }

    public void setTglLahir(String tglLahir) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.TGL_LAHIR, tglLahir);
        editor.commit();
    }

    public String getTglLahir() {
        return sp.getString(Constants.TGL_LAHIR, "");
    }

    public void setJenkel(String jenkel) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.JENKEL, jenkel);
        editor.commit();
    }

    public String getJenkel() {
        return sp.getString(Constants.JENKEL, "");
    }

    public void setProfesi(String profesi) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.PROFESI, profesi);
        editor.commit();
    }

    public String getProfesi() {
        return sp.getString(Constants.PROFESI, "");
    }

    public void setNoTlp(String noTlp) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.NO_TLP, noTlp);
        editor.commit();
    }

    public String getNoTlp() {
        return sp.getString(Constants.NO_TLP, "");
    }

    public void setLevel(String level) {
        editor.putBoolean(is_login, true);
        editor.putString(Constants.LEVEL, level);
        editor.commit();
    }

    public String getLevel() {
        return sp.getString(Constants.LEVEL, "");
    }


    public void setIdUser(String idUser) {
        editor.putBoolean(is_login, true);
        editor.putString("iduser", idUser);
        editor.commit();
    }

    public String getIdUser() {
        return sp.getString("iduser", "");
    }


    public void checkLogin() {
        if (!this.Login()) {
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        } else {
            Intent intent = new Intent(_context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }

    }

    public Boolean Login() {
        return sp.getBoolean(is_login, false);
    }

    public void Logout(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

}
