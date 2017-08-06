package com.tensun.dragpointviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.javonlee.dragpointview.OnPointDragListener;
import com.javonlee.dragpointview.view.AbsDragPointView;
import com.javonlee.dragpointview.view.DragPointView;

import java.util.List;

/**
 * Created by lijinfeng on 2017/7/25.
 */

public class SampleActivity extends AppCompatActivity implements OnPointDragListener {

    private ListView listView;
    public List<ConversationEntity> conversationEntities;                                           // 儲存TEST_JSON文本

    private DragPointView pointView1;
    private DragPointView pointView2;
    private DragPointView pointView3;

    private AnimationDrawable animationDrawable;
    private AnimatorSet animatorSet;
    private ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v("more", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        init();
    }

    private void init() {
        Log.v("more", "init()");
        initView();                                                                                 // 實體化
        initAnim();
        initSetting();
        initList();
    }

    private void initView() {                                                                       // 實體化
        Log.v("more", "initView()");
        pointView1 = (DragPointView) findViewById(R.id.drag_point_view1);
        pointView2 = (DragPointView) findViewById(R.id.drag_point_view2);
        pointView3 = (DragPointView) findViewById(R.id.drag_point_view3);
        listView = (ListView) findViewById(R.id.list);
    }

    private void initAnim() {                                                                       // 為3個DragPointView 分別綁定動畫效果
        Log.v("more", "initAnim()");
        animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.explode1), 100);              // 添加一帧, 并设置该帧显示的持续时间
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.explode2), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.explode3), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.explode4), 100);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.explode5), 100);
        animationDrawable.setOneShot(false);                                                        //
        animationDrawable.setExitFadeDuration(300);                                                 // 设置新状态下将要进场的Drawable淡入耗时毫秒值
        animationDrawable.setEnterFadeDuration(100);                                                // 设置旧状态下将要离场的Drawable淡出耗时毫秒值

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(null, "scaleX", 1.f, 0.f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(null, "scaleY", 1.f, 0.f);
        animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(objectAnimator1, objectAnimator2);

        objectAnimator = ObjectAnimator.ofFloat(null, "alpha", 1.f, 0.f);
        objectAnimator.setDuration(2000l);
    }

    private void initSetting() {
        Log.v("more", "initSetting()");
        pointView1.setRemoveAnim(animationDrawable);
        pointView2.setRemoveAnim(objectAnimator);
        pointView3.setRemoveAnim(animatorSet);

        pointView1.setOnPointDragListener(this);
    }

    private void initList() {
        Log.v("more", "initList()");
        conversationEntities =                                                                      // 將JSON文本parse成JSONArray, 把TEST_JSON文本 parse到conversationEntities
        JSONArray.parseArray(ConversationEntity.TEST_JSON, ConversationEntity.class);
        listView.setAdapter(new ItemConversationAdapter(this, conversationEntities));
        updateUnreadCount();
    }

    public void updateUnreadCount() {                                                               // 更新pointView1顯示的 未讀數字
        Log.v("more", "updateUnreadCount()");
        int totalUnreadCount = 0;                                                                   // 未讀數字初始為0
        for (ConversationEntity entity : conversationEntities) {                                    // 將conversationEntities 所有的未讀數字, 分別加到totalUnreadCount
            totalUnreadCount += entity.getMessageNum();
        }
        if (totalUnreadCount > 0) {
            Log.v("more", "updateUnreadCount(), totalUnreadCount > 0");
            pointView1.setVisibility(View.VISIBLE);
            if (totalUnreadCount <= 99) {                                                           // 如果總Item 未讀數字 小於等於99
                pointView1.setText(totalUnreadCount + "");
            } else {                                                                                // 如果總Item 未讀數字 大於99
                pointView1.setText("99+");
            }
        } else {
            Log.v("more", "updateUnreadCount(), totalUnreadCount = 0");
            pointView1.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRemoveStart(AbsDragPointView view) {
        Log.v("more", "onRemoveStart()");
        for (ConversationEntity entity : conversationEntities) {                                    // 把所有Item的
            Log.v("more", "onRemoveStart(), for()");
            entity.setRead(true);                                                                   // 將isRead 設置為true
            entity.setMessageNum(0);                                                                // 將messageNum 設置為0
        }
    }

    @Override
    public void onRemoveEnd(AbsDragPointView view) {
        Log.v("more", "onRemoveEnd()");
    }

    @Override
    public void onRecovery(AbsDragPointView view) {
        Log.v("more", "onRecovery()");
    }
}
