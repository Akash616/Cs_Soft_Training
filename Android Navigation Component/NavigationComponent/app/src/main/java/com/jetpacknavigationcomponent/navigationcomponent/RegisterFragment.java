package com.jetpacknavigationcomponent.navigationcomponent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RegisterFragment extends Fragment {

    NavHostFragment navHostFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        TextView tv_regFrag = view.findViewById(R.id.tv_regFrag); //use view binding

        navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navHostFragment.getNavController();

        tv_regFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment); //1 way
                navController.navigate(R.id.action_registerFragment_to_loginFragment);  //2 way
            }
        });
        return view;
    }
}