package com.example.gatsby.ui.eventCreation;

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

public class EventCreationFragment extends Fragment {

    private EventCreationModel eventCreationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventCreationViewModel =
                ViewModelProviders.of(this).get(EventCreationModel.class);
        View root = inflater.inflate(R.layout.fragment_event_creation, container, false);
        final TextView textView = root.findViewById(R.id.text_event_creation);
        eventCreationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}