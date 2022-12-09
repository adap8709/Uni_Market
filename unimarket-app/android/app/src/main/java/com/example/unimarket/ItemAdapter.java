package com.example.unimarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 검색해서 받아온 상품 item.xml이랑 매칭
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<PostResponseData> items = new ArrayList<PostResponseData>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) { //뷰홀더가 만들어질 때
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) { //인덱스에 맞는 객체를 찾음
        PostResponseData item = items.get(position);
        viewHolder.setItem(item);
    }
    // 받아온 상품 수
    @Override
    public int getItemCount() {
        return items.size();
    }
    //리스트에 아이템 추가
    public void addItem(PostResponseData item) {
        items.add(item);
    }

    public void setItems(ArrayList<PostResponseData> items) {
        this.items = items;
    }

    public PostResponseData getItem(int position) {
        return items.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView priceView;
        TextView timeview;
        TextView regionview;
        TextView sellerView;
        TextView description;

        ImageView platformview;
        ImageView pictureView;

        String picture_link;
        String app_name;
        String link;
        String nunstr;
        DecimalFormat decFormat = new DecimalFormat("###,###");




        public ViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.item_title);
            priceView = itemView.findViewById(R.id.item_price);
            timeview = itemView.findViewById(R.id.item_time);
            regionview = itemView.findViewById(R.id.item_reg);
            sellerView = itemView.findViewById(R.id.item_seller);

            platformview = itemView.findViewById(R.id.platform);
            pictureView = itemView.findViewById(R.id.item_picture);

        }

        public void setItem(PostResponseData item) {
            titleView.setText(item.getTitle());
            String nunstr = decFormat.format(item.getPrice());
            priceView.setText(nunstr+" 원");
            timeview.setText(item.getDate());
            regionview.setText(item.getRegion());
            sellerView.setText(item.getSeller_info());

            app_name = item.getApp_name();
            picture_link = item.getPicture();

            Glide.with(itemView).load(picture_link).into(pictureView);


            switch(app_name){
                case "당근":
                    platformview.setImageResource(R.drawable.ic_karrot_24dp);
                    break;
                case "중고":
                    platformview.setImageResource(R.drawable.ic_joongna_24dp);
                    break;
                case "번개":
                    platformview.setImageResource(R.drawable.ic_ightning_24dp);
                    break;
            }

        }

    }
    // 원래 뷰 삭제
    public void clear() {
        this.items.clear();
    }


    //시간 순 정렬
    public ArrayList<PostResponseData> SortedToTime(){
        ArrayList<PostResponseData> sortedData = this.items;
        Collections.sort(sortedData);

        return sortedData;
    }
}
