package com.sharipov.passwordkeeper.Presentation.View;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.sharipov.passwordkeeper.Presentation.ViewModel.AddPasswordViewModel;
import com.sharipov.passwordkeeper.databinding.AddPasswordFragmentBinding;
import java.util.Locale;
import java.util.Objects;

public class AddPassword extends Fragment {
    AddPasswordFragmentBinding binding;
    AddPasswordViewModel viewModel;

    private final String errorText = "Неверные или отсутствующие данные";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddPasswordViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel = null;
        binding = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddPasswordFragmentBinding.inflate(getLayoutInflater(), container, false);

        binding.saveButton.setOnClickListener(view -> {
            if (!Objects.requireNonNull(binding.editTextWebsiteAddress.getText()).toString().isEmpty() && !Objects.requireNonNull(binding.editTextWebsiteName.getText()).toString().isEmpty() &&
                    !Objects.requireNonNull(binding.editTextLogin.getText()).toString().isEmpty() && !Objects.requireNonNull(binding.editTextPassword.getText()).toString().isEmpty()) {
                String httpsUrl = String.format("https://www.%s", binding.editTextWebsiteAddress.getText().toString()).toLowerCase(Locale.ROOT);
                String httpUrl = String.format("http://www.%s", binding.editTextWebsiteAddress.getText().toString()).toLowerCase(Locale.ROOT);
                if(Patterns.WEB_URL.matcher(httpsUrl).matches()){
                    binding.editTextWebsiteAddress.setText(httpsUrl);
                }else if(Patterns.WEB_URL.matcher(httpUrl).matches()){
                    binding.editTextWebsiteAddress.setText(httpUrl);
                }
                viewModel.AddPassword(
                        binding.editTextWebsiteAddress.getText().toString(),
                        binding.editTextWebsiteName.getText().toString(),
                        binding.editTextLogin.getText().toString(),
                        binding.editTextPassword.getText().toString(),
                        Objects.requireNonNull(binding.editTextDescription.getText()).toString());
                Navigation.findNavController(view).popBackStack();
            } else {
                Toast.makeText(getContext(), errorText, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}
