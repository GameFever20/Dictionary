package com.example.aisha.newmaterialsearchview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.animation;

/**
 * Created by Aisha on 3/26/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public String recentWord = "Random Word";
    Context mainContext;
    int a;

    public static ArrayList<String> recentWordsNameArraylist = new ArrayList<>();
    public ArrayList<Dictionary> randomwordnameArraylist = new ArrayList<>();

    public RecyclerAdapter(Context baseContext, int slide_in_left, ArrayList<Dictionary> mrandomwordnameArraylist) {
        mainContext = baseContext;
        a = slide_in_left;
        randomwordnameArraylist = mrandomwordnameArraylist;
        Log.d("al recycler", randomwordnameArraylist.size() + "");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.recentWOrdTextview.setText(recentWord);
        holder.nameOfWordTextview.setText(randomwordnameArraylist.get(position).getWord());
        if (randomwordnameArraylist.get(position).getWordPronunciation()!=null){
            holder.pronunciationTextview.setText(randomwordnameArraylist.get(position).getWordPronunciation());

        }
        setAnimation(holder.nameOfWordTextview, position);

    }

    private void setAnimation(View viewToAnimate, int posi) {

        Animation animation = AnimationUtils.loadAnimation(mainContext, a);
        viewToAnimate.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public int currentItem;
        public ImageButton textToSpeechImageBtn;
        public TextView recentWOrdTextview;
        public TextView nameOfWordTextview;
        public  TextView pronunciationTextview;

        public ViewHolder(View itemView) {
            super(itemView);
            textToSpeechImageBtn = (ImageButton) itemView.findViewById(R.id.text_to_speech_imagebtn_cardview);
            recentWOrdTextview = (TextView) itemView.findViewById(R.id.recent_word_tv_cardview);
            nameOfWordTextview = (TextView) itemView.findViewById(R.id.name_of_the_word_tv_cardview);
            pronunciationTextview=(TextView)itemView.findViewById(R.id.pronunciation_tv_cardview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                }
            });

        }
    }

}
