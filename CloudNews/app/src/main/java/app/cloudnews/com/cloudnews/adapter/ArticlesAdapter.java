package app.cloudnews.com.cloudnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import app.cloudnews.com.cloudnews.R;
import app.cloudnews.com.cloudnews.listener.NewListListener;
import app.cloudnews.com.cloudnews.model.Articles;

import static android.R.attr.format;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Articles> articlesList;
    private NewListListener newListListener;
    private Context context;
    public SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout lncontent;
        public TextView title;
        public TextView descrip;
        public TextView author;
        public ImageView image;
        public TextView date;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            descrip=(TextView) view.findViewById(R.id.descrip);
            author=(TextView) view.findViewById(R.id.author);
            image=(ImageView) view.findViewById(R.id.photo);
            lncontent=(LinearLayout) view.findViewById(R.id.lncontent);
            date=(TextView)view.findViewById(R.id.date);
            lncontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newListListener.onDetail(view,getAdapterPosition());
                }
            });
        }
    }
    public ArticlesAdapter(List<Articles> articlesList, Context context, NewListListener listListener) {
        this.articlesList = articlesList;
        this.newListListener=listListener;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_news_sources, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Articles articles = articlesList.get(position);
        holder.title.setText(articles.getTitle());
        holder.author.setText(articles.getAuthor());
        try{
            holder.date.setText(formatter.format(dateFormatter.parse(articles.getPublishedAt())));
        }catch (Exception e){
            holder.date.setText(articles.getPublishedAt());
            e.printStackTrace();
        }
        holder.descrip.setText(articles.getDescription());
        Picasso.with(context)
                .load(articles.getUrlToImage())
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}