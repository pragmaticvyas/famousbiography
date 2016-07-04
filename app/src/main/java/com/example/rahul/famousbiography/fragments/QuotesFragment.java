package com.example.rahul.famousbiography.fragments;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.famousbiography.R;
import com.example.rahul.famousbiography.model.FamousPeople;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuotesFragment extends Fragment {

FamousPeople famousPeople;
    TextView quotesText;
    public QuotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_quotes, container, false);
        Bundle bundle = getActivity().getIntent().getExtras();
        famousPeople=bundle.getParcelable("famous");
        quotesText= (TextView) view.findViewById(R.id.quotes);
                 quotesText.setText(famousPeople.getDetails());
        // Inflate the layout for this fragment
        return view;
    }

}
