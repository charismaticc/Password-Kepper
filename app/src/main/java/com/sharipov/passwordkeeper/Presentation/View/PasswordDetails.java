package com.sharipov.passwordkeeper.Presentation.View;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Domain.Model.PasswordUtilities;
import com.sharipov.passwordkeeper.Presentation.ViewModel.PasswordDetailsViewModel;
import com.sharipov.passwordkeeper.R;
import com.sharipov.passwordkeeper.databinding.PasswordDetailsFragmentBinding;

public class PasswordDetails extends Fragment {

    private PasswordDetailsViewModel viewModel;
    private PasswordDetailsFragmentBinding binding;
    private Boolean isVisibility = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(PasswordDetailsViewModel.class);

        if (getArguments() != null) {
            Password password = PasswordUtilities.JsonToPassword(getArguments().getString("PASSWORDS"));
            viewModel.setPassword(password);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PasswordDetailsFragmentBinding.inflate(inflater, container, false);
        if (viewModel.getPassword() != null) {
            binding.textViewWebsiteAddress.setText(viewModel.getPassword().getWebsiteAddress());
            binding.textViewWebsiteName.setText(viewModel.getPassword().getWebsiteName());
            binding.textViewLogin.setText(viewModel.getPassword().getLogin());
            binding.textViewPassword.setText(viewModel.getPassword().getPassword());
            binding.textViewPassword.setTransformationMethod(new PasswordTransformationMethod());
            binding.textViewDescription.setText(viewModel.getPassword().getDescription());
        }

        binding.imageButtonCopyWebsiteAddress.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", binding.textViewWebsiteAddress.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Адрес сайта скопирован!", Toast.LENGTH_SHORT).show();
        });

        binding.imageButtonCopyLogin.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", binding.textViewLogin.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Логин скопирован!", Toast.LENGTH_SHORT).show();
        });

        binding.imageButtonCopyPassword.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", binding.textViewPassword.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Пароль скопирован!", Toast.LENGTH_SHORT).show();
        });

        binding.buttonOpenInBrowser.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.textViewWebsiteAddress.getText().toString()));
            startActivity(browserIntent);
        });

        binding.imageButtonVisibility.setOnClickListener(view -> {
            if(isVisibility){
                binding.imageButtonVisibility.setBackgroundResource(R.drawable.ic_visibility_on);
                binding.textViewPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
            else {
                binding.imageButtonVisibility.setBackgroundResource(R.drawable.ic_visibility_off);
                binding.textViewPassword.setTransformationMethod(binding.textViewLogin.getTransformationMethod());
            }
            isVisibility = !isVisibility;
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PasswordDetailsViewModel.class);
    }
}