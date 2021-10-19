package com.sharipov.passwordkeeper.Presentation.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sharipov.passwordkeeper.API.Service.PasswordResponse;
import com.sharipov.passwordkeeper.API.Service.PasswordService;
import com.sharipov.passwordkeeper.Presentation.Repository.Network.GeneratePassword;
import com.sharipov.passwordkeeper.databinding.FragmentPasswordGeneratorBinding;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PasswordGenerator extends Fragment {
    private FragmentPasswordGeneratorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPasswordGeneratorBinding.inflate(inflater, container, false);
        binding.generaitPasswordButton.setOnClickListener(view -> {
            final GeneratePassword service;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://makemeapassword.ligos.net/")
                    .build();
            service = retrofit.create(GeneratePassword.class);
            int len = 12;
            if(binding.editTextNumber.getText().length()>0){
                len = Integer.parseInt(binding.editTextNumber.getText().toString());
            }
            char symbol = 'n';
            if(binding.checkBox.isChecked()){
                symbol = 'y';
            }
            Call<ResponseBody> call = service.generatePassword(1, len, symbol);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful() && response.body() != null){
                        try {
                            String password = response.body().string();
                            binding.passwordGeneratorTextView.setText(password.split("\"")[3]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        });

        binding.copyGeneraitPasswordButton.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", binding.passwordGeneratorTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Пароль скопирован!", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }
}