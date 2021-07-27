package com.example.koperasi.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.koperasi.model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.example.koperasi.R;
import com.example.koperasi.network.ApiConnection;
import com.example.koperasi.util.ServiceGenerator;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private Call<LoginResponse> loginResponseCall;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (validateLogin(username, password)) {
                    doLogin(username, password);
                }
            }
        });
    }

    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username harus diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password harus diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(String username, String password){
        String accessToken = "";
        ApiConnection service = ServiceGenerator.getRetrofitInstance(accessToken).create(ApiConnection.class);

        Map<String,Object> data=new HashMap<>();
        data.put("username",username);
        data.put("password",password);

        loginResponseCall = service.loginService(data);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        String roles = response.body().getRole().trim();

                        if (roles.equals("N")){
                            Intent intent = new Intent(LoginActivity.this, NavigateDrawerActivity.class);
                            intent.putExtra("id_anggota", response.body().getIdAnggota());
                            intent.putExtra("username", response.body().getUserName());
                            intent.putExtra("role", roles);
                            intent.putExtra("access_token", response.body().getAccessToken());

                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Silahkan login sebagai nasabah", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Exception message [" + e.getMessage() + "]");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
