package com.rutvik.a20mca121_android;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends FirebaseRecyclerAdapter<Model, MyAdapter.PastViewHolder> {

    private Context context;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, final int i, @NonNull final Model model) {


        holder.id.setText(model.getId());
        holder.name.setText(model.getName());
        holder.dept.setText(model.getDept());

holder.cd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context,RViewActivity.class);
        intent.putExtra("gid",model.getId());
        intent.putExtra("gname",model.getName());
        intent.putExtra("gdept",model.getDept());

        context.startActivity(intent);

    }
});

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Demo")
                        .child(getRef(i).getKey())
                        .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50, 0, 50, 0)
                        .setContentHolder(new ViewHolder(R.layout.content))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();

                View holderView = (LinearLayout) dialog.getHolderView();

                final EditText id = holderView.findViewById(R.id.txtuId);
                final EditText name = holderView.findViewById(R.id.txtuName);
                final EditText dept = holderView.findViewById(R.id.txtudept);


                id.setText(model.getId());
                name.setText(model.getName());
                dept.setText(model.getDept());

                Button update = holderView.findViewById(R.id.btnupdate);

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id.getText().toString());
                        map.put("name", name.getText().toString());
                        map.put("dept", dept.getText().toString());

                        FirebaseDatabase.getInstance().getReference()
                                .child("Demo")
                                .child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });


                    }
                });

                dialog.show();
            }
        });


    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, dept;
        ImageView edit, delete;
        CardView cd;


        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtId);
            name = itemView.findViewById(R.id.txtname);
            dept = itemView.findViewById(R.id.txtdept);
            edit = itemView.findViewById(R.id.btnedit);
            delete = itemView.findViewById(R.id.btndelete);
            cd=itemView.findViewById(R.id.cd);



        }
}
}
