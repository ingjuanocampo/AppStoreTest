package com.juanocampo.test.appstoretest.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by juanocampo on 6/20/16.
 */
public class CardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int MIN_ITEMS_TO_ANIMATE = 4;
    private int lastPosition = -1;

    public interface CardAdapterActions {
        void onCardClicked(Entry entry, View view);

        void onCategoryClicked(String categoryKey);
    }

    private static final int ANIMATED_ITEMS_COUNT = 20;

    private final Context context;
    private final CardAdapterActions actions;
    private final List<ViewType> items;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;
    private int itemsCount = 0;

    public CardsAdapter(Context context, List<ViewType> items, CardAdapterActions adapterActions) {
        this.context = context;
        this.items = items;
        this.actions = adapterActions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (Entry.CATEGORY_VIEW_TYPE == viewType) {
            viewHolder = new CategoryViewHolder(parent);
        } else {
            viewHolder = new CardViewHolder(parent);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof CardViewHolder) {
            CardViewHolder holder = (CardViewHolder) viewHolder;
            Entry entry = (Entry) items.get(position);
            holder.setValues(entry);

            String transitionName = context.getString(R.string.share_transition_name) + entry.getName().getLabel();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ViewCompat.setTransitionName(holder.sharedItem, transitionName);
            }
        } else if (viewHolder instanceof CategoryViewHolder) {
            Entry.Category categoryKey = (Entry.Category) items.get(position);
            ((CategoryViewHolder) viewHolder).setValues(categoryKey.getAttributes().getLabel());
        }

        setAnimation(viewHolder.itemView, position);

    }

    private void setAnimation(final View viewToAnimate, final int position) {

        if (getItemCount() >= MIN_ITEMS_TO_ANIMATE && position > lastPosition) {

            // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(context, position % 2 == 0 ? R.anim.slide_in_left : R.anim.slide_in_rigth);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    viewToAnimate.clearAnimation();
                    viewToAnimate.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


        } else {
            viewToAnimate.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView categoryText;

        public CategoryViewHolder(ViewGroup item) {
            super(LayoutInflater.from(item.getContext()).inflate(R.layout.category_view_item, item, false));
            categoryText = (TextView) itemView.findViewById(R.id.category_text);
            itemView.setVisibility(View.GONE);
        }

        public void setValues(final String categoryKey) {
            categoryText.setText(categoryKey);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actions.onCategoryClicked(categoryKey);
                }
            });
        }


    }


    public class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtTitle;
        private TextView price;
        private TextView category;
        private View sharedItem;

        public CardViewHolder(ViewGroup item) {
            super(LayoutInflater.from(item.getContext()).inflate(R.layout.card_view_item, item, false));
            sharedItem = itemView.findViewById(R.id.share_item);

            imageView = (ImageView) itemView.findViewById(R.id.app_image);
            txtTitle = (TextView) itemView.findViewById(R.id.app_title);
            price = (TextView) itemView.findViewById(R.id.app_price);
            category = (TextView) itemView.findViewById(R.id.app_category);
            itemView.setVisibility(View.GONE);

        }

        public void setValues(final Entry entry) {
            Picasso.with(context).
                    load(entry.getImagesList().get(2).getLabel()).
                    placeholder(R.mipmap.ic_launcher)
                    .transform(AnimationsCommons.getCircleTransformation())
                    .into(imageView);


            txtTitle.setText(entry.getTitle().getLabel());

            price.setText(entry.getPrice().getPriceAttributes().getCurrency() + " " + entry.getPrice().getPriceAttributes().getAmount());
            category.setText(entry.getCategory().getAttributes().getLabel());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actions.onCardClicked(entry, sharedItem);
                }
            });


        }
    }
}
