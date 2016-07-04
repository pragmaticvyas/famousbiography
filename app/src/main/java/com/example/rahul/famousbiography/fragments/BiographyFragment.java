package com.example.rahul.famousbiography.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahul.famousbiography.DetailActivity;
import com.example.rahul.famousbiography.R;
import com.example.rahul.famousbiography.database.BiographyDataSource;
import com.example.rahul.famousbiography.model.FamousPeople;
import com.example.rahul.famousbiography.xml.FamousPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiographyFragment extends Fragment {

    StaggeredGridLayoutManager staggeredGridLayoutManager;
    List<FamousPeople> famousPeoples;
    BiographyDataSource biographyDataSource;

    public BiographyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        biographyDataSource=new BiographyDataSource(getActivity());
        biographyDataSource.open();
        Log.v("data","database created");
        View view=inflater.inflate(R.layout.fragment_biography, container, false);
        RecyclerView recyclerView= (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        staggeredGridLayoutManager=new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
         famousPeoples=biographyDataSource.findRecords();
        if(famousPeoples.size()==0){
             createdata();
            famousPeoples=biographyDataSource.findRecords();


        }
        RecyclerViewAdapter recyclerViewAdapter=
                new RecyclerViewAdapter(getActivity(),famousPeoples);
        recyclerView.setAdapter(recyclerViewAdapter);


        return view;

    }
    private void createdata(){

        FamousPullParser famousPullParser=new FamousPullParser();
        List<FamousPeople> famousPeopleList=famousPullParser.parseXML(getContext());
        for (FamousPeople famousPeople : famousPeopleList) {
            biographyDataSource.create(famousPeople);
        }

    }
// inner class recyler view
    public class RecyclerViewAdapter extends RecyclerView.Adapter<BiographyViewHolder> {

        private List<FamousPeople> famousPeoples = new ArrayList<>();
        private Context context;

        public RecyclerViewAdapter(Context context, List<FamousPeople> famousPeoples) {

            this.famousPeoples = famousPeoples;
            this.context = context;

        }


        @Override
        public BiographyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutview = LayoutInflater.
                    from(parent.getContext()).inflate(R.layout.biography_grid, null);
            BiographyViewHolder biographyViewHolder = new BiographyViewHolder(layoutview);
            return biographyViewHolder;
        }

        @Override
        public void onBindViewHolder(BiographyViewHolder holder, int position) {

            FamousPeople famousPeople=famousPeoples.get(position);
            holder.textView.setText(famousPeoples.get(position).getName());
            int image=  holder.imageView.getResources().getIdentifier(famousPeople.getPhoto(),
                    "drawable",context.getPackageName());
            // image source does nt have value

            if(image!=0) {
                holder.imageView.setImageResource(image);
            }  }

        @Override
        public int getItemCount() {

            return this.famousPeoples.size();
        }
    }
    //inner clas view holder
    class BiographyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;




        public BiographyViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            textView= (TextView) itemView.findViewById(R.id.country_name);
            imageView= (ImageView) itemView.findViewById(R.id.country_photo);


        }

        @Override
        public void onClick(View view) {
            Bundle bundle=new Bundle();
             bundle.putParcelable(".model.FamousPeople",
                     famousPeoples.get(getAdapterPosition()));
            Intent intent=new Intent(view.getContext(),DetailActivity.class);
            intent.putExtras(bundle);

            view.getContext().startActivity(intent);

        }

    }




}


