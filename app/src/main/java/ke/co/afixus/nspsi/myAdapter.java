package ke.co.afixus.nspsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder>
        {
            Context context;
            ArrayList<Refferrals> myDataList;
            public myAdapter(Context contxt, ArrayList<Refferrals> mylist)
            {
           context = contxt;
           myDataList = mylist;

            }
            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.ref_row,viewGroup, false));
            }

            @Override
            public void onBindViewHolder(@NonNull myViewHolder holder, int position)
            {

                holder.nameTextView.setText(myDataList.get(position).getRstdname());
                holder.phoneTextView.setText(myDataList.get(position).getRstdphoneno1());
                holder.courseTextview.setText(myDataList.get(position).getRstdcourse());
                //holder.refferraldateTextview.setText(myDataList.get(position).get);
            }

            @Override
            public int getItemCount() {
                return myDataList.size();
            }

           class myViewHolder extends  RecyclerView.ViewHolder
            {

                 TextView  nameTextView, phoneTextView,courseTextview;
                public myViewHolder(@NonNull View itemView)
                {
                    super(itemView);
                    nameTextView = (TextView) itemView.findViewById(R.id.studenname);
                    phoneTextView = (TextView) itemView.findViewById(R.id.studentmobileno);
                    courseTextview = (TextView) itemView.findViewById(R.id.student_targetcourse);
                    //refferraldateTextview = (TextView) itemView.findViewById(R.id.student_refdate);

                }
            }
        }
