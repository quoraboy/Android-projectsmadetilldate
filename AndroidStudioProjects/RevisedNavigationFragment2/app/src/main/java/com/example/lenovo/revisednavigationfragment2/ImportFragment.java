package com.example.lenovo.revisednavigationfragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Lenovo on 8/21/2018.
 */

public class ImportFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
              return inflater.inflate(R.layout.fragment_import,null);// the line need to be added to use fragment
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.Button).setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           Toast.makeText(getActivity(),"you are learing sachin",Toast.LENGTH_SHORT).show();
       }
   });
    }
}
