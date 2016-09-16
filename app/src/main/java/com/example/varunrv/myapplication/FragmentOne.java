package com.example.varunrv.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment implements View.OnClickListener {

    Button button;
    int counter;
    Communicator comm;
    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        comm= (Communicator) getActivity();
        button= (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        counter++;
        comm.respond("The button was clicked "+counter+" times");
    }
}
