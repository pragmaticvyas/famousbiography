package com.example.rahul.famousbiography.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.rahul.famousbiography.R;
import com.example.rahul.famousbiography.model.FamousPeople;


public class QuickFacts extends Fragment {

    TextView detailText;
    FamousPeople famousPeople;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_quick_facts, container, false);
        Log.v("Activity","INSTANCE"+getActivity());
        Bundle bundle = getActivity().getIntent().getExtras();
        famousPeople=bundle.getParcelable(".model.FamousPeople");
        detailText= (TextView) view.findViewById(R.id.quick_detailview);
          detailText.setText(famousPeople.getDetails());
               return view;
    }


}
