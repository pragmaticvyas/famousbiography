package com.example.rahul.famousbiography.fragments;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import  android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.rahul.famousbiography.R;
import com.example.rahul.famousbiography.interfaces.BioDetailClickView;
import com.example.rahul.famousbiography.model.FamousPeople;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment  implements View.OnClickListener {

    TextView textView;
    ImageView imageView;
    Button QuickfactsBtn;
    Button BiographyBtn;
    Button QuotesBtn;
     private BioDetailClickView mListener;
    FamousPeople famousPeople;

    public DetailActivityFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener =
                    (BioDetailClickView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement  IViewClickDetailQuick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getActivity().getIntent().getExtras();
             famousPeople=bundle.getParcelable(".model.FamousPeople");
        textView = (TextView) view.findViewById(R.id.bioname);
        textView.setText(famousPeople.getName());
        imageView = (ImageView) view.findViewById(R.id.imageView);
        int imageResource = getResources().getIdentifier(
                famousPeople.getPhoto(), "drawable", getActivity().getPackageName());
        if (imageResource != 0) {
            imageView.setImageResource(imageResource);
        }

        Log.v("test", "images are coming");

         QuickfactsBtn= (Button) view.findViewById(R.id.button_quickfacts);
        BiographyBtn= (Button) view.findViewById(R.id.biodetailbtn);
        QuotesBtn= (Button) view.findViewById(R.id.quotesbtn);


        QuickfactsBtn.setOnClickListener( this);
        BiographyBtn.setOnClickListener(this);
        QuotesBtn.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_quickfacts:
                mListener.onClickFragmentQuick();
                Log.v("quick facts", "clicked");
                break;
            case  R.id.biodetailbtn:
                mListener.onClickFragmentDetailBio();
                break;
            case R.id.quotesbtn:
                mListener.onClickFragmentQuotes();
                    Log.v("quotes","quotes clicked");
        }
    }
}
