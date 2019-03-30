package com.rishabh.customrecyclerviewtabs.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.rishabh.customrecyclerviewtabs.Adapters.TabsAdapter.ViewHolder;
import com.rishabh.customrecyclerviewtabs.DataModels.TabItem;
import com.rishabh.customrecyclerviewtabs.R;
import java.util.ArrayList;

public class TabsAdapter extends
    RecyclerView.Adapter<ViewHolder> {

  private Context context;
  private int currentSelected = 0;
  private ArrayList<TabItem> dataSet;
  private OnItemClickListener listener;
  private LinearLayoutManager layoutManager;

  public TabsAdapter(ArrayList<TabItem> data, Context context, OnItemClickListener listener,
      LinearLayoutManager layoutManager) {
    setHasStableIds(true);
    this.dataSet = data;
    this.context = context;
    this.listener = listener;
    this.layoutManager = layoutManager;
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.tab_item_layout, parent, false);
    return new ViewHolder(view);
  }


  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

    TextView title = holder.title;
    ImageView icon = holder.icon;
    CardView cardView = holder.cardView;

    TabItem currModel = dataSet.get(position);
    title.setText(currModel.getText());
    icon.setImageDrawable(context.getDrawable(currModel.getIcon()));

    //changing appearance of selected item
    if(currModel.isStatus()){
      title.setTextColor(Color.BLUE);
      cardView.setElevation(20f);
    }else{
      title.setTextColor(Color.BLACK);
      cardView.setElevation(0f);
    }

    cardView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        listener.onItemClick(position);
      }
    });

  }


  //Return item view at the given position or null if position is not visible.
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


  //public method to set the current selected tab item.
  public void setCurrentSelected(int i) {
    select(i);
  }


  @Override
  public long getItemId(int position) {
    return position;
  }


  @Override
  public int getItemCount() {
    return dataSet.size();
  }


  //interface to pass the on click control to the host activity.
  public interface OnItemClickListener {
    void onItemClick(int position);
  }


  static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView icon;
    CardView cardView;

    ViewHolder(View itemView) {
      super(itemView);
      cardView = itemView.findViewById(R.id.cardView);
      icon = itemView.findViewById(R.id.icon);
      title = itemView.findViewById(R.id.title);
    }

  }


}

