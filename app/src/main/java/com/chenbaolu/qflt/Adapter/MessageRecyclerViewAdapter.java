package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.BaseApplication;
import com.chenbaolu.baselib.network.bean.pojo.UserData;
import com.chenbaolu.baselib.network.bean.pojo.UserNews;
import com.chenbaolu.qflt.MyApplication;
import com.chenbaolu.qflt.R;
import com.chenbaolu.qflt.ui.activity.DialogActivity;
import com.chenbaolu.qflt.ui.activity.MainActivity;
import com.chenbaolu.qflt.ui.activity.PostDetailsActivity;
import com.chenbaolu.qflt.ui.activity.UserDetailActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/12 21:04
 * 作者 : 23128
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<UserNews> list;
    private Map<Long,UserData> userDataMap;

    public MessageRecyclerViewAdapter(Context context, List<UserNews> list,Map<Long,UserData> userDataMap) {

        this.context = context;
        this.userDataMap = userDataMap;


        if (list.size()>0){
            if (list.get(0).getType()==1){
                Map<Long,List<UserNews>> map;
                Long userId = BaseApplication.getUserId();
                if (userId!=0){
                    map = assort(list,userId);
                }else{
                    map = new LinkedHashMap<>();
                }
                Log.d("test3",map.toString());
                list = getAirList();
                for (Long set : map.keySet()){
                    list.add(map.get(set).get(0));
                }
                Log.d("test3",list.toString());
            }
        }
        this.list = list;
    }

    public static List<UserNews> getAirList(){
        List<UserNews> list = new LinkedList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort(new Comparator<UserNews>() {
                @Override
                public int compare(UserNews o1, UserNews o2) {
                    if (o1.getCreate_date().getTime()<o2.getCreate_date().getTime()){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            });
        }
        return list;
    }


    public static Map<Long,List<UserNews>> assort(List<UserNews> list, Long userId){
        Map<Long,List<UserNews>> map = new LinkedHashMap<>();
        for (UserNews userNews:list){
            if (userNews.getUser_id()==userId){
                if (map.containsKey(userNews.getProduce_user_id())){
                    map.get(userNews.getProduce_user_id()).add(userNews);
                }else{
                    List<UserNews> list1 = getAirList();
                    list1.add(userNews);
                    map.put(userNews.getProduce_user_id(),list1);
                }
            }else{
                if(map.containsKey(userNews.getUser_id())){
                    map.get(userNews.getUser_id()).add(userNews);
                }else{
                    List<UserNews> list1 = getAirList();
                    list1.add(userNews);
                    map.put(userNews.getUser_id(),list1);
                }
            }

        }

        return map;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_message,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserNews userNews = list.get(position);
        Long id;
        switch ((int) userNews.getType()){
            case 1:
                if (userNews.getUser_id()==BaseApplication.getUserId()){
                    id = userNews.getProduce_user_id();
                }else {
                    id = userNews.getUser_id();
                }
                Glide.with(context).load(userDataMap.get(id).getAvatar()).into(holder.circleImageView);
                holder.name.setText(userDataMap.get(id).getName());
                holder.message.setText(userNews.getContent());holder.message.setText(userNews.getContent());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userNews.getType()==1){
                            Intent intent = new Intent(context, DialogActivity.class);
                            intent.putExtra("id",id);
                            context.startActivity(intent);
                        }
                    }
                });
                break;
            case 5:
                id = userNews.getUser_id();
                Glide.with(context).load(userDataMap.get(userNews.getUser_id()).getAvatar()).into(holder.circleImageView);
                holder.name.setText(userDataMap.get(userNews.getUser_id()).getName());
                holder.message.setText(userNews.getNews_type().getTitle());
                break;
            default:
                id = userNews.getUser_id();
                Glide.with(context).load(userDataMap.get(userNews.getUser_id()).getAvatar()).into(holder.circleImageView);
                holder.name.setText(userDataMap.get(userNews.getUser_id()).getName());
                holder.message.setText(userNews.getNews_type().getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PostDetailsActivity.class);
                        intent.putExtra("id",userNews.getPost_id());
                        context.startActivity(intent);
                    }
                });
                break;
        }
        View.OnClickListener click = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        };
        holder.circleImageView.setOnClickListener(click);
        holder.name.setOnClickListener(click);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView name;
        TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.card_message_circle);
            name = itemView.findViewById(R.id.card_message_name);
            message = itemView.findViewById(R.id.card_message_message);
        }
    }

}
