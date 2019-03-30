# CustomRecyclerViewTabs
## This is an example Android project which shows how to use RecyclerView as tabs for a Viewpager.
RecyclerView is perfect for showing any set of views in a particular order with a LayoutManager and a custom adapter. Here I have tried to create an application which uses **RecyclerView** as Tabs for a **ViewPager** and **mimicks** the functions of a traditional tab layout.


![](CustomRecyclerViewTabs/screen_shot_2.gif)

Getting straight to the point, we need to complete the following steps.
- Add the design library to your app level build.gradle.
````
  implementation 'com.android.support:design:28.0.0'
````
- Create activity layout with a RecyclerView and ViewPager.
- Make a simple RecyclerView adapter as you always do, just this time with few more methods.
 The constructor would look like:
 ````
  public TabsAdapter(ArrayList<TabItem> data, Context context, OnItemClickListener listener,
      LinearLayoutManager layoutManager) {
    setHasStableIds(true);
    this.dataSet = data;
    this.context = context;
    this.listener = listener;
    this.layoutManager = layoutManager;
  }
 ````
 The method used to get view by position:
 ````
 private View getViewByPosition(int pos) {
    if (layoutManager == null) {
      return null;
    }
    final int firstListItemPosition = layoutManager.findFirstVisibleItemPosition();
    final int lastListItemPosition = firstListItemPosition + layoutManager.getChildCount() - 1;

    if (pos < firstListItemPosition || pos > lastListItemPosition) {
      return null;
    } else {
      final int childIndex = pos - firstListItemPosition;
      return layoutManager.getChildAt(childIndex);
    }
  }
 ````
The select and deselect tab method will change the appearance of the selected item of RecyclerView.
 ````
 private void select(int position) {
    dataSet.get(position).setStatus(true); //updating dataset
    if (currentSelected >= 0) {
      deselect(currentSelected);
    }

    View targetView = getViewByPosition(position);
    if (targetView != null) {
      // change the appearance
      TextView title = targetView.findViewById(R.id.title);
      title.setTextColor(Color.BLUE);
      CardView cardView = targetView.findViewById(R.id.cardView);
      cardView.setElevation(20f);
    }

    if (listener != null) {
      listener.onItemClick(position);
    }

    currentSelected = position;

  }


  private void deselect(int position) {
    dataSet.get(position).setStatus(false); //updating dataset
    if (getViewByPosition(position) != null) {
      View targetView = getViewByPosition(position);
      if (targetView != null) {
        // change the appearance
        TextView title = targetView.findViewById(R.id.title);
        title.setTextColor(Color.BLACK);
        CardView cardView = targetView.findViewById(R.id.cardView);
        cardView.setElevation(0f);
      }
    }

    currentSelected = -1;
  }
 ````
 
 A public method to call from activity:
 ````
 public void setCurrentSelected(int i) {
    select(i);
  }
 ````
 
 An interface which will be implemented in the activity:
 ````
 public interface OnItemClickListener {
    void onItemClick(int position);
  }
 ````
 
 - In activity simply do all the view intialization and everything required to set both the RecyclerView and Viewpager.  
 The constructor of Tab adapter will look like:
 ````
 tabsAdapter = new TabsAdapter(tabItems, this, new OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        viewPager.setCurrentItem(position);
      }
    }, linearLayoutManager);
 ````
 The activity will implement a ViewPager interface which is **OnPageChangeListener**
 ````
 public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
 ````
 
 We need to override the three methods of the above interface like this:
 ````
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
 ````
 
That's it! Now, when the ViewPager page will change, it'll notify the RecyclerView TabAdapter which tab needs to be selected, and when user will click on the Item of the RecyclerView, the implementation of the Interface of TabAdapter in activity will call the method viewpager.setCurrentItem(position);
