package com.example.koperasi.ui.angsuran;

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
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import com.example.koperasi.R;
import com.example.koperasi.model.AngsuranResponse;
import com.example.koperasi.model.DataAngsuran;
import com.example.koperasi.model.DataSimpanan;
import com.example.koperasi.model.SimpananResponse;
import com.example.koperasi.network.ApiConnection;
import com.example.koperasi.util.ServiceGenerator;
import com.example.koperasi.view.DetailListDataAngsuranActivity;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AngsuranFragment extends Fragment {
    private Call<AngsuranResponse> angsuranResponseCall;
    private String accessToken;
    private int id;
    private ListView listview;
    private List<DataAngsuran> dataAngsurans;
    private String username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_global, container, false);
        listview = root.findViewById(R.id.listView_global);

        accessToken = getArguments().getString("access_token");
        username = getArguments().getString("username");
        id = getArguments().getInt("id_anggota",0);

        String param = "id_anggota";
        String search = String.valueOf(id);
        connectAndGetApiDataAngsuran(param, search);
        getActivity().setTitle("Tagihan");
        return root;
    }

    private void addViewList(Response<AngsuranResponse> angsuranResponse) {
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        SimpleAdapter simpleAdapter;

        HashMap<String,String> item;
        for(int i=0;i<angsuranResponse.body().getData().size();i++){
            item = new HashMap<String,String>();
            item.put( "line1", angsuranResponse.body().getData().get(i).getJumlahAngsuran());
            item.put( "line2", angsuranResponse.body().getData().get(i).getSisa());
            list.add( item );
        }

        String bodyJson = new Gson().toJson(angsuranResponse.body());


        simpleAdapter = new SimpleAdapter(getActivity(), list,
                R.layout.listview_angsuran,
                new String[] { "line1","line2" },
                new int[] {R.id.textViewValue, R.id.textViewValue2});
        listview.setAdapter(simpleAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long idPosition) {

                List<DataAngsuran> dataAngsuran = angsuranResponse.body().getData();
                dataAngsurans = dataAngsuran;

                Intent intent = new Intent(getContext(), DetailListDataAngsuranActivity.class);
                DataAngsuran listDataAngsuran = dataAngsurans.get(position);

                intent.putExtra("jumlah_angsuran",listDataAngsuran.getJumlahAngsuran());
                intent.putExtra("tanggal_bayar",listDataAngsuran.getTanggalBayar());
                intent.putExtra("periode_angsuran",listDataAngsuran.getPeriodeAngsuran());
                intent.putExtra("sisa",listDataAngsuran.getSisa());
                intent.putExtra("denda",listDataAngsuran.getDenda());
                intent.putExtra("access_token",accessToken);
                intent.putExtra("id_anggota",id);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void connectAndGetApiDataAngsuran(String param, String search) {
        ApiConnection service = ServiceGenerator.getRetrofitInstance(accessToken).create(ApiConnection.class);

        Map<String,Object> data=new HashMap<>();
        data.put("param", param);
        data.put("search",search);

        angsuranResponseCall = service.angsuranService(data);
        angsuranResponseCall.enqueue(new Callback<AngsuranResponse>() {

            @Override
            public void onResponse(Call<AngsuranResponse> call, Response<AngsuranResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        addViewList(response);

                    } else {
                        System.out.println("FAILED TO GET DATA ANGSURAN");
                    }
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Exception message [" + e.getMessage() + "]");
                }
            }

            @Override
            public void onFailure(Call<AngsuranResponse> call, Throwable t) {
                Log.e("Error", t.toString());
            }
        });
    }
}