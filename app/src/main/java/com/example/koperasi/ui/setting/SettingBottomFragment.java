package com.example.koperasi.ui.setting;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.koperasi.R;
import com.example.koperasi.model.AnggotaResponse;
import com.example.koperasi.model.DataAnggota;
import com.example.koperasi.network.ApiConnection;
import com.example.koperasi.util.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingBottomFragment extends Fragment {

    private Call<AnggotaResponse> anggotaResponseCall;
    private String accessToken;
    private int id;
    private ListView listview;
    private View root;

    private String username;
    private String nama = "";
    private String nik = "";
    private String nomorAnggota = "";
    private String alamatAnggota = "";
    private String noHp = "";
    private String pekerjaan = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_bottom_setting, container, false);

        accessToken = getArguments().getString("access_token");
        username = getArguments().getString("username");
        id = getArguments().getInt("id_anggota", 0);

        String param = "id";
        String search = String.valueOf(id);
        connectAndGetApiDataSimpanan(param, search);
        getActivity().setTitle("Profile");
        return root;
    }

    private void addViewList(Response<AnggotaResponse> simpananResponse) {

        String responsJson = new Gson().toJson(simpananResponse.body().getData());

        List<DataAnggota> users = new Gson().fromJson(responsJson, new TypeToken<List<DataAnggota>>() {}.getType());
        for(int i = 0; i<users.size(); i++){
            nama = users.get(i).getNamaAnggota();
            nik = users.get(i).getNik();
            nomorAnggota = users.get(i).getNomorAnggota();
            alamatAnggota = users.get(i).getAlamatAnggota();
            noHp = users.get(i).getNoHp();
            pekerjaan = users.get(i).getPekerjaan();
        }

        TextView textViewTitle = root.findViewById(R.id.textView);
        TextView textView = root.findViewById(R.id.textView2);
        TextView textView1 = root.findViewById(R.id.textView11);
        TextView textView2 = root.findViewById(R.id.textView14);
        TextView textView3 = root.findViewById(R.id.textView12);
        TextView textView4 = root.findViewById(R.id.textView13);
        TextView textView5 = root.findViewById(R.id.textView15);


        textViewTitle.setText("Data Diri ");
        textView.setText(nama);
        textView1.setText(nik);
        textView2.setText(nomorAnggota);
        textView3.setText(alamatAnggota);
        textView4.setText(noHp);
        textView5.setText(pekerjaan);

    }

    private void connectAndGetApiDataSimpanan(String param, String search) {
        ApiConnection service = ServiceGenerator.getRetrofitInstance(accessToken).create(ApiConnection.class);

        Map<String, Object> data = new HashMap<>();
        data.put("param", param);
        data.put("search", search);

        anggotaResponseCall = service.userService(data);
        anggotaResponseCall.enqueue(new Callback<AnggotaResponse>() {

            @Override
            public void onResponse(Call<AnggotaResponse> call, Response<AnggotaResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        addViewList(response);
                    } else {
                        System.out.println("FAILED TO GET DATA SIMPANAN");
                    }
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Exception message [" + e.getMessage() + "]");
                }
            }

            @Override
            public void onFailure(Call<AnggotaResponse> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}