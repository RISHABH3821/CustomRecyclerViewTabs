package com.rishabh.customrecyclerviewtabs.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rishabh.customrecyclerviewtabs.Data.DummyData;
import com.rishabh.customrecyclerviewtabs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SampleFragmentOne extends Fragment {


  public SampleFragmentOne() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_sample_one, container, false);
    Bundle bundle = getArguments();
    assert bundle != null;
    int id = bundle.getInt("key");
    TextView details = view.findViewById(R.id.details);
    details.setText(DummyData.details[id]);
    return view;
  }

}
