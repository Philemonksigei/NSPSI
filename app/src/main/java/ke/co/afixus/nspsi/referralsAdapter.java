package ke.co.afixus.nspsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class referralsAdapter extends RecyclerView.Adapter<referralsAdapter.myViewHolder>
        {
            Context context;
            ArrayList<referralsData> myDataList;
            public referralsAdapter(Context contxt, ArrayList<referralsData> mylist)
            {
               context = contxt;
               myDataList = mylist;
            }
            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
            {
                return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.referralsrow,viewGroup, false));
            }
            @Override
            public void onBindViewHolder(@NonNull myViewHolder holder, int position)
                {
                    holder.nameTextView.setText("Name:"+myDataList.get(position).getRstdname());
                    holder.phoneTextView.setText("Mobile:"+myDataList.get(position).getRstdphoneno1());
                    holder.courseTextview.setText("Target Course:"+myDataList.get(position).getRstdcourse());
                    holder.DateView.setText("Refferral Date:"+myDataList.get(position).getMyDate());
                }
            @Override
            public int getItemCount()
                {
                    return myDataList.size();
                }
           class myViewHolder extends  RecyclerView.ViewHolder
            {
                 TextView  nameTextView, phoneTextView,courseTextview, DateView;
                public myViewHolder(@NonNull View itemView)
                {
                    super(itemView);
                    nameTextView = (TextView) itemView.findViewById(R.id.messageTopic);
                    phoneTextView = (TextView) itemView.findViewById(R.id.studentmobileno);
                    courseTextview = (TextView) itemView.findViewById(R.id.messageBody);
                    DateView = (TextView) itemView.findViewById(R.id.messageDate);

                }
            }
        }
