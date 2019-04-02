package com.example.shopon_4users.Vendor_product_collection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopon_4users.R;
import com.example.shopon_4users.cart_and_payement.Cart_items;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class product_collection extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference mDatabaseRef ;
    private EditText mSearchField;
    RecyclerView recyclerView;
    ArrayList<firebase_data_inflator> arrayList;
    Adapter_view adapter_for_converting;
    TextView name_of_user,Email_id_value;
    static  String orderBy="brand";
    static String searchText="";
    TextView user_name;
    ImageView user_image;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_collection);

        setTitle("Welcome");
        NavigationView navigationView=findViewById(R.id.nav_view);
        LinearLayout navHeader=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_header_product_collection, null);
        navigationView.addHeaderView(navHeader);
        TextView mName   = (TextView)navigationView.getHeaderView(0).findViewById(R.id.user_name);
        ImageView  mPic = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.user_image);
        mName.setText("Ajay kumar pandey");
        mPic.setImageResource(R.drawable.boy);
        recyclerView = findViewById(R.id.Malls_list);
        mSearchField = findViewById(R.id.search_field);


        loading=findViewById(R.id.loading);




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(getIntent().getStringExtra("uid")).child(getIntent().getStringExtra("data"));

        arrayList = new ArrayList<firebase_data_inflator>();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    firebase_data_inflator obj = dataSnapshot1.getValue(firebase_data_inflator.class);
                    arrayList.add(obj);
                }
                adapter_for_converting = new Adapter_view(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter_for_converting);
                loading.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(product_collection.this, "oops,Daya Kuch To Ganban hai", Toast.LENGTH_SHORT).show();
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Opening Cart Items", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivity(new Intent(product_collection.this,Cart_items.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }


    @Override
    protected void onStart() {
        super.onStart();
        //mSearchBtn.performClick();
    }

    private void firebaseUserSearch(String searchText) {

        mDatabaseRef=FirebaseDatabase.getInstance().getReference().child("VendorGoods").child(getIntent().getStringExtra("uid")).child(getIntent().getStringExtra("data"));
        Toast.makeText(product_collection.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mDatabaseRef.orderByChild(orderBy).startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<firebase_data_inflator, product_collection.UsersViewHolder> FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<firebase_data_inflator, product_collection.UsersViewHolder>(

                firebase_data_inflator.class,
                R.layout.vendor_product_collection_design,
                product_collection.UsersViewHolder.class,
                firebaseSearchQuery

        ) {

            @Override
            protected void populateViewHolder(product_collection.UsersViewHolder viewHolder, final firebase_data_inflator model, final int position) {
                viewHolder.setDetails(getApplicationContext(),model.getBrand(),model.getImagurl(),model.getPrice(),model.getDescription());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // firebase_data_inflator record = this.get(position);
                        String brandNM = model.getBrand();
                        String priceNM = model.getPrice();
                        String imageNM=model.getImagurl();
                        String vendor_uid=model.getUid();
                        Intent i = new Intent(product_collection.this, Product_Summary.class);
                        i.putExtra("desc", brandNM);
                        i.putExtra("vendor_uid",vendor_uid);
                        i.putExtra("price", priceNM);
                        i.putExtra("image", imageNM );
                        product_collection.this.startActivity(i);
                    }
                });
            }
        };

        recyclerView.setAdapter(FirebaseRecyclerAdapter);


    }
    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }
        public void setDetails(Context ctx,  String brand , String pro_image,String price,String description){
            TextView  Brand,Description,Price;
            ImageView Pro_image;

            Brand = mView.findViewById(R.id.brand);
            Pro_image= mView.findViewById(R.id.pro_image);
            Price=mView.findViewById(R.id.price);
            Description=mView.findViewById(R.id.description);

            Brand.setText(brand);
            Price.setText(price);
            Description.setText(description);

            Glide.with(ctx).load(pro_image).into(Pro_image);


        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_collection, menu);
        MenuItem item = menu.findItem(R.id.orders_product_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                firebaseUserSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.user_name) {
            String str="ajay";

            name_of_user.setText(str);


        }
        if( id== R.id.user_image)
        {

        }
        if(id==R.id.Email_id)
        {

        }
        /*if(id==R.id.SortByprice)
        {
            orderBy="price";

        }*/


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.View_your_detail) {

        } else if (id == R.id.cart_items) {

        } else if (id == R.id.Home) {

        } else if (id == R.id.Setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.Rating) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
