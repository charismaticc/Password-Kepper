package com.sharipov.passwordkeeper.Presentation.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.MainActivity;
import com.sharipov.passwordkeeper.Presentation.View.Adapters.PasswordListAdapter;
import com.sharipov.passwordkeeper.Presentation.ViewModel.PasswordListViewModel;
import com.sharipov.passwordkeeper.R;
import com.sharipov.passwordkeeper.databinding.PasswordListFragmentBinding;

import java.util.List;

public class PasswordList extends Fragment {
    private PasswordListViewModel viewModel;
    private PasswordListFragmentBinding binding;

    public static PasswordList newInstance() {
        return new PasswordList();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PasswordListFragmentBinding.inflate(getLayoutInflater(), container, false);
        binding.passwordRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.passwordRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.addPasswordButton.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_passwordFragment_to_addPasswordFragment));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                viewModel.deletePassword(((PasswordListAdapter) binding.passwordRecyclerView.getAdapter()).getmValues().get(position));
            }
        }).attachToRecyclerView(binding.passwordRecyclerView);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PasswordListViewModel.class);

        viewModel.getAllPasswords().observe(getViewLifecycleOwner(), (List<Password> applianceList) -> {
            binding.passwordRecyclerView.setAdapter(new PasswordListAdapter(applianceList, (MainActivity) requireActivity()));
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        viewModel = null;
    }
}
