package com.example.koperasi.ui.simpanan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.koperasi.R;
import com.example.koperasi.model.DataPinjaman;
import com.example.koperasi.model.DataSimpanan;
import com.example.koperasi.model.SimpananResponse;
import com.example.koperasi.network.ApiConnection;
import com.example.koperasi.util.ServiceGenerator;
import com.example.koperasi.view.DetailListDataPinjamanActivity;
import com.example.koperasi.view.DetailListDataSimpananActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpananFragment extends Fragment {

    private Call<SimpananResponse> simpananResponseCall;
    private String accessToken;
    private String username;
    private int id;
    private ListView listview;
    private List<DataSimpanan> dataSimpanans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_global, container, false);
        listview = root.findViewById(R.id.listView_global);

        accessToken = getArguments().getString("access_token");
        username = getArguments().getString("username");
        id = getArguments().getInt("id_anggota", 0);

        String param = "id_anggota";
        String search = String.valueOf(id);
        connectAndGetApiDataSimpanan(param, search);
        getActivity().setTitle("Simpanan");
        return root;
    }

    private void addViewList(Response<SimpananResponse> simpananResponse) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        SimpleAdapter simpleAdapter;

        HashMap<String, String> item;
        for (int i = 0; i < simpananResponse.body().getData().size(); i++) {
            item = new HashMap<String, String>();
            item.put("line1", simpananResponse.body().getData().get(i).getJumlahSaldo());
            item.put("line2", simpananResponse.body().getData().get(i).getTanggalSimpan());
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(getActivity(), list,
                R.layout.listview_simpanan,
                new String[]{"line1", "line2"},
                new int[]{R.id.textViewValue, R.id.textViewValue2});
        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long idPosition) {

                List<DataSimpanan> dataSimpanan = simpananResponse.body().getData();
                dataSimpanans = dataSimpanan;

                Intent intent = new Intent(getContext(), DetailListDataSimpananActivity.class);
                DataSimpanan listDataPinjaman = dataSimpanans.get(position);

                intent.putExtra("tanggal_simpan",listDataPinjaman.getTanggalSimpan());
                intent.putExtra("jumlah_saldo",listDataPinjaman.getJumlahSaldo());
                intent.putExtra("access_token",accessToken);
                intent.putExtra("id_anggota",id);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void connectAndGetApiDataSimpanan(String param, String search) {
        ApiConnection service = ServiceGenerator.getRetrofitInstance(accessToken).create(ApiConnection.class);

        Map<String, Object> data = new HashMap<>();
        data.put("param", param);
        data.put("search", search);

        simpananResponseCall = service.simpananService(data);
        simpananResponseCall.enqueue(new Callback<SimpananResponse>() {

            @Override
            public void onResponse(Call<SimpananResponse> call, Response<SimpananResponse> response) {
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
            public void onFailure(Call<SimpananResponse> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}