package app.cloudnews.com.cloudnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import app.cloudnews.com.cloudnews.R;
import app.cloudnews.com.cloudnews.listener.NewListListener;
import app.cloudnews.com.cloudnews.model.Source;
import app.cloudnews.com.cloudnews.model.Sources;
import app.cloudnews.com.cloudnews.ws.INewsService;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.MyViewHolder> {
 
    private List<Sources> sourcesList;
    private NewListListener newListListener;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout lncontent;
        public TextView title;
        public TextView descrip;
 
        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            descrip=(TextView) view.findViewById(R.id.descrip);
            lncontent=(LinearLayout) view.findViewById(R.id.lncontent);
            lncontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newListListener.onDetail(view,getAdapterPosition());
                }
            });
        }
    }
    public SourcesAdapter(List<Sources> sourcesList,Context context, NewListListener listListener) {
        this.sourcesList = sourcesList;
        this.newListListener=listListener;
        this.context=context;

    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_sources, parent, false);

 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sources sources = sourcesList.get(position);
        holder.title.setText(sources.getName());
        holder.descrip.setText(sources.getDescription());



    }
 
    @Override
    public int getItemCount() {
        return sourcesList.size();
    }
}