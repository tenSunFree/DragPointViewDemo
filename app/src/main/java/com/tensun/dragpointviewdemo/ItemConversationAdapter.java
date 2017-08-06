package com.tensun.dragpointviewdemo;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.javonlee.dragpointview.OnPointDragListener;
import com.javonlee.dragpointview.view.AbsDragPointView;
import com.javonlee.dragpointview.view.DragPointView;

import java.util.ArrayList;
import java.util.List;

public class ItemConversationAdapter extends BaseAdapter implements OnPointDragListener {

    private List<ConversationEntity> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;
    private AnimationDrawable animationDrawable;

    public ItemConversationAdapter(Context context, List<ConversationEntity> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;

        animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode1), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode2), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode3), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode4), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode5), 100);
        animationDrawable.setOneShot(true);
        animationDrawable.setExitFadeDuration(300);
        animationDrawable.setEnterFadeDuration(100);
    }

    @Override
    public int getCount() {                                                                         // 取得Item的數量, 通常用於取得資料集合的大小或數量
        return objects.size();
    }

    @Override
    public ConversationEntity getItem(int position) {                                               // 回傳Item的資料
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {                                                           // 回傳Item的ID
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {                         // 回傳處理後的ListItem畫面, 這個地方謹慎處理如不小心會發生很多錯誤
        parent.setClipChildren(false);
        parent.setClipToPadding(false);

        if (convertView == null) {                                                                  // 如果Item為空值
            convertView = layoutInflater.inflate(R.layout.item_conversation, null);                    // 加載 如何呈現Item的Layout
            convertView.setTag(new ViewHolder(convertView));                                        // 先將Item的view 導入ViewHolder() 進行綁定, 透過setTag() 把已經完整綁定的Item畫面框架 保存起來
        }

        initializeViews(getItem(position), (ViewHolder) convertView.getTag());                      // 進行數據的綁定
        return convertView;
    }

    private void initializeViews(ConversationEntity object, ViewHolder holder) {                    // 進行數據的綁定
        Glide.with(context).load(object.getAvatar()).into(holder.avatar);
        holder.message.setText(object.getLastMessage());
        holder.message.setTextColor(0xffF2A104);
        holder.name.setText(object.getNickname());
        holder.name.setTextColor(0xff192E5B);
        holder.time.setText(object.getLastTime());
        holder.time.setTextColor(0xff192E5B);
        holder.pointView.setTag(object);                                                            // 找不到對應的getTag(), 有意義?
        holder.pointView.setOnPointDragListener(this);
        holder.pointView.setRemoveAnim(animationDrawable);

        if (object.isRead() || object.getMessageNum() <= 0) {
            holder.pointView.setVisibility(View.GONE);
        } else {
            holder.pointView.setVisibility(View.VISIBLE);
            if (object.getMessageNum() <= 99)
                holder.pointView.setText(object.getMessageNum() + "");
            else
                holder.pointView.setText("99");
        }
    }

    @Override
    public void onRemoveStart(AbsDragPointView view) {
    }

    @Override
    public void onRemoveEnd(AbsDragPointView view) {
        ConversationEntity entity =
                (ConversationEntity) view.getTag();
        entity.setMessageNum(0);
        entity.setRead(true);
        ((SampleActivity)context).updateUnreadCount();
    }

    @Override
    public void onRecovery(AbsDragPointView view) {
    }

    /** 宣告並實體化 你會使用到的Item元件 */
    protected class ViewHolder {
        private ImageView avatar;
        private TextView name;
        private TextView time;
        private TextView message;
        private DragPointView pointView;

        public ViewHolder(View view) {
            avatar = (ImageView) view.findViewById(R.id.avatar);
            name = (TextView) view.findViewById(R.id.name);
            time = (TextView) view.findViewById(R.id.time);
            message = (TextView) view.findViewById(R.id.message);
            pointView = (DragPointView) view.findViewById(R.id.drag_point_view);
        }
    }
}
