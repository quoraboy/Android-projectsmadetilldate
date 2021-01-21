package com.example.lenovo.fetchingdatausingrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.List;

public class Myadaptor extends RecyclerView.Adapter<Myadaptor.ViewHolder> {
    private List<ListItem> ListIt;
    private Context context;

    public Myadaptor(List<ListItem> listIt, Response.Listener<JSONObject> context1) {

        context = (Context) context1;
    }


   /* public Myadaptor(List<ListItem> listIt, Response.ErrorListener errorListener) {
    ListIt= listIt;

    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
    return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ListItem list=ListIt.get(position);
     holder.head1.setText(list.getHead());
     //holder.disc1.setText(list.getDis());
    }

    @Override
    public int getItemCount() {
        return ListIt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView head1;
     //public TextView disc1;
        public ViewHolder(View itemView) {
            super(itemView);
            head1= itemView.findViewById(R.id.head);
       ///     disc1= itemView.findViewById(R.id.disc);
        }
    }
}
