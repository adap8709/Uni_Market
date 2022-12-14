package com.example.unimarket;


import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.startActivity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewHolderItem extends RecyclerView.ViewHolder {
    public static Context context;

    TextView titleView;
    TextView priceView;
    TextView timeview;
    TextView regionview;
    TextView sellerView;
    TextView desView;

    ImageView platformview;
    ImageView pictureView;

    Button linkbutton;

    String picture_link;
    String app_name;
    String des;
    String link;
    String nunstr;
    DecimalFormat decFormat = new DecimalFormat("###,###");

    OnViewHolderItemClickListener onViewHolderItemClickListener;
    LinearLayout linearlayout;
    LinearLayout linearlayout2;

    public ViewHolderItem(@NonNull View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.item_title);
        priceView = itemView.findViewById(R.id.item_price);
        timeview = itemView.findViewById(R.id.item_time);
        regionview = itemView.findViewById(R.id.item_reg);
        sellerView = itemView.findViewById(R.id.item_seller);
        platformview = itemView.findViewById(R.id.platform);
        pictureView = itemView.findViewById(R.id.item_picture);
        desView = itemView.findViewById(R.id.item_des);

        linearlayout = itemView.findViewById(R.id.linearlayout);
        linearlayout2 = itemView.findViewById(R.id.linearlayout2);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "?????? ?????? Hello World!", Toast.LENGTH_SHORT).show();
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });


        linkbutton = itemView.findViewById(R.id.item_go);


    }

    public void setItem(PostResponseData item, int position, SparseBooleanArray selectedItems) {
        titleView.setText(item.getTitle());
        String nunstr = decFormat.format(item.getPrice());
        priceView.setText(nunstr+" ???");
        timeview.setText(item.getDate());
        regionview.setText(item.getRegion());
        sellerView.setText(item.getSeller_info());

        des = item.getDescription();
        desView.setText(des.replaceAll("\n\n","\n"));   //???????????????
        app_name = item.getApp_name();
        picture_link = item.getPicture();

        Glide.with(itemView).load(picture_link).into(pictureView);

        link = item.getLink();

        linkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)MainActivity.context).goToItem(link);
            }
        });

        switch(app_name){
            case "??????":
                platformview.setImageResource(R.drawable.ic_karrot_24dp);
                break;
            case "??????":
                platformview.setImageResource(R.drawable.ic_joongna_24dp);
                break;
            case "??????":
                platformview.setImageResource(R.drawable.ic_ightning_24dp);
                break;
        }

        changeVisibility(selectedItems.get(position));
    }


    private void changeVisibility(final boolean isExpanded) { //????????? ?????????

        // ValueAnimator.ofInt(int... values)??? View??? ?????? ?????? ??????, ????????? int ??????
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 500) : ValueAnimator.ofInt(500, 0);
        // Animation??? ???????????? ??????, n/1000???
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                // TextView??? ?????? ??????
                //desView.getLayoutParams().height = (int) animation.getAnimatedValue();
                linearlayout2.requestLayout();
                // TextView??? ????????? ?????????????????? ??????
                linearlayout2.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }
    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }

}


