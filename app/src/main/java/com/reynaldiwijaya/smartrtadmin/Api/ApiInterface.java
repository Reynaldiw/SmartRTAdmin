package com.reynaldiwijaya.smartrtadmin.Api;

import com.reynaldiwijaya.smartrtadmin.Model.Agenda.ResponseAgenda;
import com.reynaldiwijaya.smartrtadmin.Model.Agenda.ResponseUpdateAgenda;
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.ResponseKonfirmasiNews;
import com.reynaldiwijaya.smartrtadmin.Model.Informasi.ResponseNews;
import com.reynaldiwijaya.smartrtadmin.Model.Login.ResponseLogin;
import com.reynaldiwijaya.smartrtadmin.Model.Register.ResponseRegister;
import com.reynaldiwijaya.smartrtadmin.Model.Report.ResponseReply;
import com.reynaldiwijaya.smartrtadmin.Model.Report.ResponseReport;
import com.reynaldiwijaya.smartrtadmin.Model.Store.ResponseStore;
import com.reynaldiwijaya.smartrtadmin.Model.Store.ResponseUpdateStore;
import com.reynaldiwijaya.smartrtadmin.Model.User.ResponseDetailUser;
import com.reynaldiwijaya.smartrtadmin.Model.User.ResponseUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("loginAdmin.php")
    Call<ResponseLogin> login(
            @Field("edtusername") String username,
            @Field("edtpassword") String password
    );

    @GET("konfirmasiagenda.php")
    Call<ResponseAgenda> getAgenda();

    @FormUrlEncoded
    @POST("updateagenda.php")
    Call<ResponseUpdateAgenda> updateAgenda(
            @Field("id") String id
    );

    @GET("konfirmasiuser.php")
    Call<ResponseUser> getUser();

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<ResponseDetailUser> updateUser(
            @Field("id") String id
    );

    @GET("getlaporan.php")
    Call<ResponseReport> getReport();

    @GET("getnews.php")
    Call<ResponseNews> getNews();

    @FormUrlEncoded
    @POST("updatenews.php")
    Call<ResponseKonfirmasiNews> updateNews(
            @Field("id") String id
    );

    @GET("getstore.php")
    Call<ResponseStore> getStore();

    @FormUrlEncoded
    @POST("updatestore.php")
    Call<ResponseUpdateStore> updateStore(
            @Field("id") String id
    );


    @FormUrlEncoded
    @POST("reply.php")
    Call<ResponseReply> reply(
            @Field("id_kritik") String id,
            @Field("reply") String reply
    );
}
