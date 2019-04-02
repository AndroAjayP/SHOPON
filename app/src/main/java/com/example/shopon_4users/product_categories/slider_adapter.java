package com.example.shopon_4users.product_categories;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopon_4users.R;

import java.util.List;

public class slider_adapter extends PagerAdapter {

    private List<slider_model> slider_modelList;

    public slider_adapter(List<slider_model> slider_modelList) {
        this.slider_modelList = slider_modelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.banner_slider_layout,container,false);
        ImageView banner=view.findViewById(R.id.benner_slide);
        banner.setImageResource(slider_modelList.get(position).getBanner());
        container.addView(view,0);
        return view ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return slider_modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
