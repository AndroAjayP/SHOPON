package com.example.shopon_4users.product_categories;


import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopon_4users.R;
import com.example.shopon_4users.Vendor_product_collection.Adapter_view;
import com.example.shopon_4users.Vendor_product_collection.firebase_data_inflator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class categories_fragement extends Fragment {
    private RecyclerView recyclerView;
    private Adapter adapter;
    DatabaseReference mDatabaseRef;
    AppBarLayout ab;
    ProgressBar loading;
    ViewPager viewPager;
    private List<slider_model> slider_modelList;
    private int currentPage=2;
    private Timer timer;
    final private long delay=3000;
    final private long period=3000;


    DatabaseReference mmmDatabaseRef;
    RecyclerView rrrecyclerView;
    ArrayList<firebase_data_inflator> aarrayList;
    Adapter_view aadapter_for_converting;
    AppBarLayout aab;
    ProgressBar lloading;


RecyclerView category_Recycler;

    public categories_fragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /////////////////////////////////product_list
        View view= inflater.inflate(R.layout.fragment_categories_fragement, container, false);
        rrrecyclerView=view.findViewById(R.id.Mmalls_list);
        Toast.makeText(getActivity(), "You are in product list..", Toast.LENGTH_SHORT).show();
        mmmDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Product_catalog").child("6Q7VvqhNK3XmqGZRHxxK0YBiBw23").child("Clothing");
        aarrayList = new ArrayList<firebase_data_inflator>();
        LinearLayoutManager llayoutManager=new LinearLayoutManager(getActivity());
        llayoutManager.setOrientation(LinearLayout.VERTICAL);
        rrrecyclerView.setLayoutManager(llayoutManager);

        aarrayList = new ArrayList<firebase_data_inflator>();
        mmmDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    firebase_data_inflator obj = dataSnapshot1.getValue(firebase_data_inflator.class);
                    aarrayList.add(obj);
                }
                aadapter_for_converting = new Adapter_view(getActivity(), aarrayList);
                rrrecyclerView.setAdapter(aadapter_for_converting);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });


        /////////////////////////////////product_list

        recyclerView=view.findViewById(R.id.categories_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
         final ArrayList<inflator> list=new ArrayList<inflator>();


        ////////////////////////////////banner Slider


        viewPager=view.findViewById(R.id.banner_slidder);



        slider_modelList=new ArrayList<slider_model>();

        slider_modelList.add(new slider_model(R.drawable.off4));
        slider_modelList.add(new slider_model(R.drawable.off5));
        slider_modelList.add(new slider_model(R.drawable.off1));



        slider_modelList.add(new slider_model(R.drawable.off1));
        slider_modelList.add(new slider_model(R.drawable.off2));
        slider_modelList.add(new slider_model(R.drawable.off3));
        slider_modelList.add(new slider_model(R.drawable.off4));
        slider_modelList.add(new slider_model(R.drawable.off5));


        slider_modelList.add(new slider_model(R.drawable.off5));
        slider_modelList.add(new slider_model(R.drawable.off1));
        slider_modelList.add(new slider_model(R.drawable.off2));





        slider_adapter sliderAdapter=new slider_adapter(slider_modelList);
        viewPager.setAdapter(sliderAdapter);

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(20);

        viewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //currentPage=1;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==ViewPager.SCROLL_STATE_IDLE)
                {
                    pageLooper();
                }

            }
        };

       viewPager.addOnPageChangeListener(onPageChangeListener);
       startBannerSlideshow();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //pageLooper();
                stopBannerSlideShow();
                if(motionEvent.getAction()==MotionEvent.ACTION_UP)
                {
                    startBannerSlideshow();
                }
                return false;
            }
        });


        ////////////////////////////////banner slider

        adapter=new Adapter(getActivity(),list);

        //recyclerView.setAdapter(adapter);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Product_catalog").child("6Q7VvqhNK3XmqGZRHxxK0YBiBw23");
        loading=view.findViewById(R.id.loading);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    inflator obj = dataSnapshot1.getValue(inflator.class);
                    list.add(obj);
                }
                adapter = new Adapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
               // loading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }
    /////////////////////////////banner
    private void pageLooper()
    {
        if(currentPage==slider_modelList.size()-2)
        {
            currentPage=2;
            viewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage==1)
        {
            currentPage=slider_modelList.size()-3;
            viewPager.setCurrentItem(currentPage,false);
        }


    }

    private void startBannerSlideshow()
    {
        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentPage>=slider_modelList.size() )
                {
                   //currentPage=1;
                }
                viewPager.setCurrentItem(currentPage++,true);
            }
        };
       timer= new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               handler.post(update);
           }
       },delay,period);

    }
    private void stopBannerSlideShow ()
    {
          timer.cancel();
    }
     ///////////////////////////////banner
}
