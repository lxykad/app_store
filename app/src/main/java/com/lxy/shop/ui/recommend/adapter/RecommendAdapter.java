package com.lxy.shop.ui.recommend.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lxy.shop.R;
import com.lxy.shop.ui.recommend.AppBean;
import com.youth.banner.Banner;

import org.eclipse.jface.text.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/6/9.
 */

public class RecommendAdapter extends DelegateAdapter.Adapter<RecommendAdapter.RecommendViewHolder> {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APP_TOP = 3;
    private static final int TYPE_GAME_TOP = 4;
    private static final int TYPE_APP_LIST = 5;
    private static final int TYPE_GAME_LIST = 6;


    private List<AppBean> mList = new ArrayList<>();
    private Context mContext;
    String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";


    private LayoutHelper mLayoutHelper;
    private RecyclerView.LayoutParams mLayoutParams;
    private int mCount;
    private int mItemType;

    public RecommendAdapter(Context context, LayoutHelper helper, int count, int type) {

        this(context, helper, count, type, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    public RecommendAdapter(Context context, LayoutHelper helper, int count, int type, RecyclerView.LayoutParams params) {

        mContext = context;
        mLayoutParams = params;
        mCount = count;
        mLayoutHelper = helper;
        mItemType = type;

    }

    public void addItems(List<AppBean> list) {
        mList.clear();
        mList.addAll(list);
        // notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        return mLayoutHelper;
    }

    @Override
    public RecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_recommend_fragment, parent, false);

        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecommendViewHolder holder, int position) {

        System.out.println("mItemType ============" + mItemType);

        switch (mItemType) {

            case TYPE_BANNER:
               final Banner banner = (Banner) holder.itemView.findViewById(R.id.banner);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mChildItemClickListener != null) {

                            mChildItemClickListener.setOnClick(banner);
                        }
                    }
                });


                break;
            case TYPE_ICON:

                final TextView app = (TextView) holder.itemView.findViewById(R.id.tv_hot_app);
                final TextView game = (TextView) holder.itemView.findViewById(R.id.tv_hot_game);
                final TextView theme = (TextView) holder.itemView.findViewById(R.id.tv_hot_theme);
                app.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mChildItemClickListener != null) {

                            mChildItemClickListener.setOnClick(app);
                        }
                    }
                });
                game.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mChildItemClickListener != null) {

                            mChildItemClickListener.setOnClick(game);
                        }
                    }
                });
                theme.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mChildItemClickListener != null) {

                            mChildItemClickListener.setOnClick(theme);
                        }
                    }
                });

                break;
            case TYPE_APP_TOP:

                break;
            case TYPE_APP_LIST:

                break;
            case TYPE_GAME_TOP:

                break;
            case TYPE_GAME_LIST:

                break;
        }

    }

    @Override
    public int getItemCount() {
        return mCount;
    }


    public static class RecommendViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.text_title);


        }

    }

   private ChildItemClickListener mChildItemClickListener;

   public interface ChildItemClickListener{

       void setOnClick(View view, int position);
       void setOnClick(View view);
   }

   public void setChildClickListener(ChildItemClickListener listener){
       mChildItemClickListener = listener;
   }

}
