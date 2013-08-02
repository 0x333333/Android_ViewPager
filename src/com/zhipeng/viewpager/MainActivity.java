package com.zhipeng.viewpager;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ViewPager viewPager;
	private ArrayList<View> pageViews; 	// pages view
	private ImageView imageView;        // image => small white and black point
	private ImageView[] imageViews;
	private ViewGroup viewPics;         // layout for view group
	private ViewGroup viewPoints;       // layout for small points

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // save views into array
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<View>();
        pageViews.add(inflater.inflate(R.layout.page01, null));
        pageViews.add(inflater.inflate(R.layout.page02, null));
        pageViews.add(inflater.inflate(R.layout.page03, null));
        
        // save small points into array
        imageViews = new ImageView[pageViews.size()];
        viewPics = (ViewGroup) inflater.inflate(R.layout.view_pics, null);
        
        // find view by id
        viewPoints = (ViewGroup) viewPics.findViewById(R.id.viewGroup);
        viewPager = (ViewPager) viewPics.findViewById(R.id.guidePages);
        
        // set images
        for(int i=0;i<pageViews.size();i++){
        	imageView = new ImageView(MainActivity.this);
        	imageView.setLayoutParams(new LayoutParams(20,20));//创建一个宽高均为20 的布局
        	imageView.setPadding(20, 0, 20, 0);
        	imageViews[i] = imageView;
        	
        	if(i==0){
        		imageViews[i].setBackgroundResource(R.drawable.circle_white);
        	}else{
        		imageViews[i].setBackgroundResource(R.drawable.circle_grey);
        	}
        	
        	viewPoints.addView(imageViews[i]);
        }
        setContentView(viewPics);
        
        // set page adapter and listener
        viewPager.setAdapter(new GuidePageAdapter());
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }
    
    
    class GuidePageAdapter extends PagerAdapter{

		@Override
		public void destroyItem(View v, int position, Object arg2) {
			((ViewPager)v).removeView(pageViews.get(position));
			
		}

		@Override
		public void finishUpdate(View arg0) {
		}
		
		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public Object instantiateItem(View v, int position) {
			((ViewPager) v).addView(pageViews.get(position));  
            return pageViews.get(position);  
		}

		@Override
		public boolean isViewFromObject(View v, Object arg1) {
			return v == arg1;
		}

		@Override
		public void startUpdate(View arg0) {
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
    }
    
    
    class GuidePageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			for(int i=0;i<imageViews.length;i++){
				imageViews[position].setBackgroundResource(R.drawable.circle_white);
				if(position !=i){
					imageViews[i].setBackgroundResource(R.drawable.circle_grey);
				}
			}
		}
    }
}