package com.example.gatsby.ui.userinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gatsby.R;

public class UserInfoFragment extends Fragment {

    private UserInfoViewModel UserInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserInfoViewModel =
                ViewModelProviders.of(this).get(UserInfoViewModel.class);
        View root = inflater.inflate(R.layout.user_info, container, false);
        return root;
    }
}