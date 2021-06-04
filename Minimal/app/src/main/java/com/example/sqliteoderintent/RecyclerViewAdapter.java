package com.example.sqliteoderintent;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteoderintent.model.Category;
import com.example.sqliteoderintent.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OderViewHolder> {
    @NonNull
    private List<Category> list;
    private View v;
    public RecyclerViewAdapter() {
        list=new ArrayList<>();
    }
    public void setStudents(List<Category> list){
        this.list=list;
    }

    @Override
    public OderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        v=inflater.inflate(R.layout.oder_card,parent,false);
        return new OderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OderViewHolder holder, final int position) {
        Category o=list.get(position);
        holder.idname.setText(o.getName().toString());
        holder.ngay.setText(o.getDate().toString());
        holder.time.setText(o.getTime().toString());
        String text=o.getName().toString().substring(0,1);
        holder.t.setText(text);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category todo =list.get(position);
                Intent intent=new Intent(v.getContext(),UpdateActivity.class);
                intent.putExtra("todo", todo);
                v.getContext().startActivity(intent);

            }
        });


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OderViewHolder extends RecyclerView.ViewHolder {
        private TextView idname,ngay,time,t;
        private Spinner spin;
        public OderViewHolder(@NonNull View itemView) {
            super(itemView);
            idname=itemView.findViewById(R.id.idnameitem);
            ngay=itemView.findViewById(R.id.dateitem);
            time=itemView.findViewById(R.id.timeitem);
            spin=itemView.findViewById(R.id.spinner);
            t=itemView.findViewById(R.id.text);
        }
    }
}
