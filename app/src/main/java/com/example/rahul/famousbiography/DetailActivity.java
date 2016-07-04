package com.example.rahul.famousbiography;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.rahul.famousbiography.fragments.DetailActivityFragment;
import com.example.rahul.famousbiography.fragments.DetailBiographyFragment;
import com.example.rahul.famousbiography.fragments.QuickFacts;
import com.example.rahul.famousbiography.fragments.QuotesFragment;
import com.example.rahul.famousbiography.interfaces.BioDetailClickView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class DetailActivity extends AppCompatActivity implements BioDetailClickView {


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.navigation);
        setSupportActionBar(toolbar);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new DetailActivityFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public void fragment_transaction(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onClickFragmentQuick() {

        Fragment frag= new QuickFacts();
      fragment_transaction(frag);
    }

    @Override
    public void onClickFragmentDetailBio() {

        Fragment fragment =new DetailBiographyFragment();
        fragment_transaction(fragment);

    }

    @Override
    public void onClickFragmentQuotes() {

        Fragment fragment =new QuotesFragment();
        fragment_transaction(fragment);

    }

}
