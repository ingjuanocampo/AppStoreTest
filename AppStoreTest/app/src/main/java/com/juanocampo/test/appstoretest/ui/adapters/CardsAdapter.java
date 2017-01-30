package com.juanocampo.test.appstoretest.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by juanocampo on 6/20/16.
 */
public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {



    public interface CardAdapterActions {
        void onCardClicked(Entry entry, View view);
    }

    private static final int ANIMATED_ITEMS_COUNT = 20;

    private final Context context;
    private final CardAdapterActions actions;
    private final List<Entry> entries;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;
    private int itemsCount = 0;

    public CardsAdapter(Context context, List<Entry> entries, CardAdapterActions adapterActions) {
        this.context = context;
        this.entries = entries;
        this.actions = adapterActions;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.setValues(entries.get(position));

        String transitionName = context.getString(R.string.share_transition_name) + entries.get(position).getName().getLabel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(holder.sharedItem, transitionName);
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
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
             price = (TextView)  itemView.findViewById(R.id.app_price);
             category = (TextView) itemView.findViewById(R.id.app_category);

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
