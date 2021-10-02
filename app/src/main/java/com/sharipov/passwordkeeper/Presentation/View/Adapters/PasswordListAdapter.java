package com.sharipov.passwordkeeper.Presentation.View.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Domain.Model.PasswordUtilities;
import com.sharipov.passwordkeeper.MainActivity;
import com.sharipov.passwordkeeper.R;
import com.sharipov.passwordkeeper.databinding.PasswordRowBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordViewHolder> {

    private final List<Password> mValues;
    private MainActivity mainActivity;

    public PasswordListAdapter(List<Password> mValues, MainActivity mainActivity) {
        this.mValues = mValues;
        this.mainActivity = mainActivity;
    }

    @Override
    public PasswordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PasswordRowBinding passwordItemBinding = PasswordRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PasswordViewHolder(passwordItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordListAdapter.PasswordViewHolder holder, int position) {
        holder.passwordRowBinding.getRoot().setOnClickListener((View v) -> {
            Bundle bundle = new Bundle();
            String jsonPassword = PasswordUtilities.PasswordToJson(mValues.get(position));
            bundle.putString("PASSWORDS", jsonPassword);
            Navigation.findNavController(mainActivity.mainBinding.fragmentContainerView).navigate(R.id.action_PasswordFragment_to_password, bundle);
        });

        holder.passwordRowBinding.textViewWebSiteAddress.setText(mValues.get(position).getWebsiteAddress());
        holder.passwordRowBinding.textViewWebsiteName.setText(mValues.get(position).getWebsiteName());
        Picasso.with(holder.passwordRowBinding.getRoot().getContext())
                .load("https://www." + mValues.get(position).getWebsiteAddress() + "/favicon.ico")
                .error(R.drawable.ic_key).into(holder.passwordRowBinding.imageViewWebsiteIcon);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {
        PasswordRowBinding passwordRowBinding;

        public PasswordViewHolder(PasswordRowBinding binding) {
            super(binding.getRoot());

            this.passwordRowBinding = binding;
        }
    }

    public List<Password> getmValues() {
        return mValues;
    }

}