package com.example.koperasi.network;

import com.example.koperasi.model.AnggotaResponse;
import com.example.koperasi.model.AngsuranResponse;
import com.example.koperasi.model.LoginResponse;
import com.example.koperasi.model.PinjamanResponse;
import com.example.koperasi.model.SimpananResponse;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiConnection {
    @POST("/api/auth")
    Call<LoginResponse> loginService(@Body Map<String,Object> loginData);

    @POST("/api/view/angsuran")
    Call<AngsuranResponse> angsuranService(@Body Map<String,Object> angsuranData);

    @POST("/api/view/pinjaman")
    Call<PinjamanResponse> pinjamanService(@Body Map<String,Object> pinjamanData);

    @POST("/api/view/simpanan")
    Call<SimpananResponse> simpananService(@Body Map<String,Object> simpananData);

    @POST("/api/view/anggota")
    Call<AnggotaResponse> userService(@Body Map<String,Object> userData);
}

