package com.chenbaolu.qflt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.chenbaolu.baselib.CallBack.LoadTasksCallBack;
import com.chenbaolu.baselib.network.bean.dto.PostCommentsDto;
import com.chenbaolu.baselib.network.bean.pojo.PostComments;
import com.chenbaolu.qflt.CallBack.PostCommentsCallBack;
import com.chenbaolu.qflt.Dialog.PostCommentsBottomDialog;
import com.chenbaolu.qflt.MVP.API.PostAPI;
import com.chenbaolu.qflt.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 描述 :
 * 创建时间 : 2022/9/11 15:43
 * 作者 : 23128
 */
public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder> {

    private List<PostComments> list;
    private Context context;
    private PostCommentsCallBack postCommentsCallBack;
    public CommentsRecyclerViewAdapter(List<PostComments> list, Context context, PostCommentsCallBack postCommentsCallBack) {
        this.list = list;
        this.context = context;
        this.postCommentsCallBack = postCommentsCallBack;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post_comments,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostComments comments = list.get(position);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.leftMargin = holder.itemView.getLeft()+100*comments.getLayers();
        holder.itemView.setLayoutParams(layoutParams);
        Glide.with(context).load(comments.getUser_data().getAvatar()).into(holder.circleImageView);
        holder.title.setText(comments.getTitle());
        holder.size.setText(comments.getLike()+"");
        holder.name.setText(comments.getUser_data().getName());
        holder.date.setText(comments.getCreate_date().toString());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAPI.commentsLike(comments.getPost_id(), comments.getId(), new LoadTasksCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        comments.setLike(comments.getLike()+1);
                        holder.size.setText(comments.getLike()+"");
                        ((ImageView)holder.like.findViewById(R.id.comments_like)).setImageDrawable(context.getDrawable(R.drawable.ic_post_thumb2));
                        Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFailed(String message, Integer code) {
                        comments.setLike(comments.getLike()-1);
                        holder.size.setText(comments.getLike()+"");
                        ((ImageView)holder.like.findViewById(R.id.comments_like)).setImageDrawable(context.getDrawable(R.drawable.ic_post_thumb));
                        Toast.makeText(context, "点赞失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comments.getLayers()>5){
                    Toast.makeText(context, "层数太高了", Toast.LENGTH_SHORT).show();
                    return;
                }
                new PostCommentsBottomDialog(context, holder.name.getText().toString(), new PostCommentsCallBack() {
                    @Override
                    public void setComments(PostCommentsDto postCommentsDto) {

                    }

                    @Override
                    public void backContent(String title) {
                        PostCommentsDto postCommentsDto = new PostCommentsDto();
                        postCommentsDto.setPostId(comments.getPost_id());
                        postCommentsDto.setTitle(title);
                        postCommentsDto.setReplyId(comments.getId());
                        postCommentsCallBack.setComments(postCommentsDto);
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView title;
        TextView name;
        TextView size;
        RelativeLayout like;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.comments_circle);
            title = itemView.findViewById(R.id.comments_title);
            name = itemView.findViewById(R.id.comments_name);
            like = (RelativeLayout) itemView.findViewById(R.id.comments_like).getParent();
            size = itemView.findViewById(R.id.like_size);
            date = itemView.findViewById(R.id.comments_date);
        }
    }

    public List<PostComments> getList() {
        return list;
    }

    public void setList(List<PostComments> list) {
        this.list = list;
    }
}
