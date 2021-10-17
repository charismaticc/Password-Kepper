package com.sharipov.passwordkeeper.Presentation.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.sharipov.passwordkeeper.Presentation.Repository.Network.GeneratePassword;
import com.sharipov.passwordkeeper.Presentation.ViewModel.PasswordDetailsViewModel;
import com.sharipov.passwordkeeper.databinding.FragmentPasswordGeneratorBinding;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PasswordGeneratorFragment extends Fragment {
    private PasswordDetailsViewModel viewModel;
    private FragmentPasswordGeneratorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PasswordDetailsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPasswordGeneratorBinding.inflate(inflater, container, false);
        binding.generaitPasswordButton.setOnClickListener(view -> {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://makemeapassword.ligos.net/")
                    .build();
            GeneratePassword service = retrofit.create(GeneratePassword.class);
            Call<ResponseBody> call = service.generatePassword(1, 12);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful() && response.body() != null){
                        String password = null;
                        try {
                            password = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        assert password != null;
                        binding.passwordGeneratorTextView.setText(password.split("\"")[3]);
                    }
                    else {
                        Toast.makeText(getContext(), Toast.LENGTH_SHORT, Integer.parseInt("Проблемы с сетью")).show();
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