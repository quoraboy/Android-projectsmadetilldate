package com.example.lenovo.attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
//nested classes are there, Viewholder class is nested inside MyAdaptor class
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Viewholder> {
    List<ListItem> list;
    Context context;

    public MyAdapter(List<ListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Viewholder holder, int position) {
        ListItem l = list.get(position);
        holder.Subcode.setText(l.getSubcode());
        holder.Sub.setText(l.getSub());
        holder.name.setText(l.getName());
        holder.attendance.setText(l.getAttendance());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        TextView Subcode;
        TextView Sub;
        TextView name;
        TextView attendance;
        public Viewholder(View itemView) {
            super(itemView);
            Subcode = (TextView)itemView.findViewById(R.id.txtSubcode);
            Sub =(TextView)itemView.findViewById(R.id.txtSub);
            name =(TextView)itemView.findViewById(R.id.txtname);
            attendance=(TextView)itemView.findViewById(R.id.txtatten);

        }
    }
}
