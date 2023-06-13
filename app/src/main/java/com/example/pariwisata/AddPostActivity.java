package com.example.pariwisata;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pariwisata.databinding.ActivityAddPostBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.etContent.getText().toString();
                String foto = binding.etFoto.getText().toString();

                boolean bolehPost = true;

                if (TextUtils.isEmpty(content)){
                    bolehPost = false;
                    binding.etContent.setError("Konten Tidak Boleh kosong");
                }

                if (bolehPost) {
                    String user_id = Utility.getValue(AddPostActivity.this, "xUsername");
                    addPost(user_id, foto, content);
                }
            }
        });
    }

    private void addPost(String user_id, String foto, String content) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueData> call = api.addPost( user_id, foto, content);
        call.enqueue(new Callback<ValueData>() {
            @Override
            public void onResponse(Call<ValueData> call, Response<ValueData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1 ) {
                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddPostActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData> call, Throwable t) {
                 binding.progressBar.setVisibility(View.GONE);
                 System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(AddPostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}