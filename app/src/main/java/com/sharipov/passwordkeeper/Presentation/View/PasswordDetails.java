package com.sharipov.passwordkeeper.Presentation.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Domain.Model.PasswordUtilities;
import com.sharipov.passwordkeeper.Presentation.ViewModel.PasswordDetailsViewModel;
import com.sharipov.passwordkeeper.databinding.PasswordDetailsFragmentBinding;

public class PasswordDetails extends Fragment {

    private PasswordDetailsViewModel viewModel;
    private PasswordDetailsFragmentBinding binding;

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
            binding.textViewPossword.setText(viewModel.getPassword().getPassword());
            binding.textViewDescription.setText(viewModel.getPassword().getDescription());
        }
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PasswordDetailsViewModel.class);
    }
}