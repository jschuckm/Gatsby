package com.example.gatsby.ui.Signup;

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
import com.example.gatsby.ui.gallery.GalleryViewModel;

public class SignupFragment extends Fragment {

    private SignUpViewModel signupViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        signupViewModel =
                ViewModelProviders.of(this).get(SignUpViewModel.class);
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        signupViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
