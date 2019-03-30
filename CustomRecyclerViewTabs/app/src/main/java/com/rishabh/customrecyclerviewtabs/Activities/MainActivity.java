package com.rishabh.customrecyclerviewtabs.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.rishabh.customrecyclerviewtabs.Adapters.PagerAdapter;
import com.rishabh.customrecyclerviewtabs.Adapters.TabsAdapter;
import com.rishabh.customrecyclerviewtabs.Adapters.TabsAdapter.OnItemClickListener;
import com.rishabh.customrecyclerviewtabs.DataModels.TabItem;
import com.rishabh.customrecyclerviewtabs.Fragments.SampleFragmentOne;
import com.rishabh.customrecyclerviewtabs.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

  RecyclerView tabsRecyclerView;
  ViewPager viewPager;
  TabsAdapter tabsAdapter;
  PagerAdapter pagerAdapter;
  ArrayList<TabItem> tabItems = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tabsRecyclerView = findViewById(R.id.tab_recyclerView);
    viewPager = findViewById(R.id.viewPager);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    tabsRecyclerView.setLayoutManager(linearLayoutManager);
    tabsAdapter = new TabsAdapter(tabItems, this, new OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        viewPager.setCurrentItem(position);
      }
    }, linearLayoutManager);
    tabsRecyclerView.setAdapter(tabsAdapter);
    initTabsAdapter();
    viewPager.addOnPageChangeListener(this);
  }


  private void initTabsAdapter(){
    tabItems.add(new TabItem("Rob", R.drawable.pic1, true));
    tabItems.add(new TabItem("Rachael", R.drawable.pic6, false));
    tabItems.add(new TabItem("Jack", R.drawable.pic2, false));
    tabItems.add(new TabItem("Sumi", R.drawable.pic7, false));
    tabItems.add(new TabItem("John", R.drawable.pic3, false));
    tabItems.add(new TabItem("Cindy", R.drawable.pic8, false));
    tabItems.add(new TabItem("Ryan", R.drawable.pic4, false));
    tabItems.add(new TabItem("Julie", R.drawable.pic9, false));
    tabItems.add(new TabItem("Jeremy", R.drawable.pic5, false));
    tabItems.add(new TabItem("Summer", R.drawable.pic10, false));
    tabsAdapter.notifyDataSetChanged();
    setupViewPager(viewPager);
  }


  private void setupViewPager(ViewPager viewPager) {
    pagerAdapter = new PagerAdapter(getSupportFragmentManager());
    //adding dummy fragment to the viewpager
    for(int i=0;i<10;i++){
      Bundle bundle = new Bundle();
      bundle.putInt("key", i);
      Fragment fragment = new SampleFragmentOne();
      fragment.setArguments(bundle);
      pagerAdapter.addFragment(fragment, "");
    }
    viewPager.setAdapter(pagerAdapter);
  }


  @Override
  public void onPageScrolled(int i, float v, int i1) {

  }


  @Override
  public void onPageSelected(int i) {
    tabsAdapter.setCurrentSelected(i);
    tabsRecyclerView.scrollToPosition(i);
  }


  @Override
  public void onPageScrollStateChanged(int i) {

  }


}