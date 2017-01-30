package com.juanocampo.test.appstoretest.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.juanocampo.test.appstoretest.R;
import com.juanocampo.test.appstoretest.models.Entry;
import com.juanocampo.test.appstoretest.ui.activities.DetailActivity;
import com.juanocampo.test.appstoretest.util.AnimationsCommons;
import com.squareup.picasso.Picasso;

/**
 * Created by juanocampo
 */
public class DetailFragment extends Fragment {

    public static DetailFragment newInstance(@NonNull Entry entry) {

        Bundle args = new Bundle();
        args.putSerializable(DetailActivity.ENTRY, entry);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment_view, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Preconditions.checkNotNull(getArguments());
        Preconditions.checkState(getArguments().containsKey(DetailActivity.ENTRY));

        Entry entry = (Entry) getActivity().getIntent().getExtras().get(DetailActivity.ENTRY);

        ImageView imageView = (ImageView) getView().findViewById(R.id.app_image);
        TextView titleApp = (TextView) getView().findViewById(R.id.app_title);
        TextView price = (TextView)  getView().findViewById(R.id.app_price);
        TextView category = (TextView) getView().findViewById(R.id.app_category);
        TextView description = (TextView) getView().findViewById(R.id.detail_description);
        TextView company = (TextView) getView().findViewById(R.id.detail_company);
        TextView releaseDate = (TextView) getView().findViewById(R.id.detail_release_date);
        TextView copyRights = (TextView) getView().findViewById(R.id.detail_copy_rights);
        final View container = getView().findViewById(R.id.container_extra_info);

       View sharedElement = getView().findViewById(R.id.share_item);

        final String transitionName = getString(R.string.share_transition_name) + entry.getName().getLabel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setTransitionName(sharedElement, transitionName);
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                container.setVisibility(View.VISIBLE);
                Animation translateIn = AnimationUtils.loadAnimation(getContext(), R.anim.up_in);

                container.startAnimation(translateIn);

            }
        });




        titleApp.setText(entry.getTitle().getLabel());
        price.setText(entry.getPrice().getPriceAttributes().getCurrency() + " " + entry.getPrice().getPriceAttributes().getAmount());
        category.setText(entry.getCategory().getAttributes().getLabel());
        company.setText(entry.getLink().getAttributes().getHref());
        description.setText(entry.getSummary().getLabel());
        releaseDate.setText(entry.getReleaseDate().getAttributes().getLabel());
        copyRights.setText(entry.getRights().getLabel());


        Picasso.with(getContext()).
                load(entry.getImagesList().get(2).getLabel()).
                placeholder(R.mipmap.ic_launcher).transform(AnimationsCommons.getCircleTransformation())
                .into(imageView);




    }
}
