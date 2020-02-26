package ke.co.afixus.nspsi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class messagesAdapter extends RecyclerView.Adapter<messagesAdapter.myViewHolder>
{
    Context context;
    ArrayList<messagesData> myDataList;
    public messagesAdapter(Context contxt, ArrayList<messagesData> mylist)
    {
        context = contxt;
        myDataList = mylist;

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
        {
            return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.messagesrow,viewGroup, false));
        }
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {
        holder.msTopicView.setText(myDataList.get(position).getMessageTopic());
        holder.msbodyView.setText(myDataList.get(position).getMessageBody());
        holder.msDateView.setText("Posted on:"+myDataList.get(position).getMessageDate());
    }
    @Override
    public int getItemCount()
    {
        return myDataList.size();
    }
    class myViewHolder extends  RecyclerView.ViewHolder
    {
        TextView  msTopicView, msbodyView,msDateView;
        public myViewHolder(@NonNull View itemView)
            {
                super(itemView);
                msTopicView = (TextView) itemView.findViewById(R.id.messageTopic);
                msbodyView = (TextView) itemView.findViewById(R.id.messageBody);
                msDateView = (TextView) itemView.findViewById(R.id.messageDate);
            }
    }
}

