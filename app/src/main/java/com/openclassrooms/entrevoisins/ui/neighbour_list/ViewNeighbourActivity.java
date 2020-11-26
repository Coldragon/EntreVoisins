package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewNeighbourActivity extends AppCompatActivity {

	// UI Components
	@BindView(R.id.view_neighbour_avatar)
	ImageView mImageViewAvatar;
	@BindView(R.id.view_neighbour_name1)
	TextView mTextViewName1;
	@BindView(R.id.view_neighbour_name2)
	TextView mTextViewName2;
	@BindView(R.id.view_neighbour_location)
	TextView mTextViewLocation;
	@BindView(R.id.view_neighbour_phone)
	TextView mTextViewPhone;
	@BindView(R.id.view_neighbour_social)
	TextView mTextViewSocial;
	@BindView(R.id.view_about_me_content)
	TextView mTextViewAboutMeContent;
	@BindView(R.id.neighbour_add_fav)
	FloatingActionButton mFloatingActionButtonAddFavorite;

	private Neighbour mNeighbour;
	private NeighbourApiService mApiService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getSupportActionBar() != null)
			getSupportActionBar().hide();
		setContentView(R.layout.activity_view_neighbour);
		ButterKnife.bind(this);

		mApiService = DI.getNeighbourApiService();

		initView();
		initListener();

	}

	private void initListener() {
		mFloatingActionButtonAddFavorite.setOnClickListener(v -> {
			boolean isFav = mApiService.getIsFavorite(mNeighbour);
			Toast.makeText(getBaseContext(), isFav ? "Removed from Favorite" : "Added to Favorite", Toast.LENGTH_SHORT).show();
			mFloatingActionButtonAddFavorite.setImageResource(isFav ? R.drawable.ic_outline_star_border_24 : R.drawable.ic_star_fav);
			mApiService.toggleIsFavorite(mNeighbour);
		});
	}

	private void initView() {
		mNeighbour = (Neighbour) getIntent().getParcelableExtra("Neighbour");

		Glide.with(this)
				.load(mNeighbour.getAvatarUrl())
				.into(mImageViewAvatar);

		mFloatingActionButtonAddFavorite.setImageResource(mApiService.getIsFavorite(mNeighbour) ? R.drawable.ic_star_fav : R.drawable.ic_outline_star_border_24);
		mTextViewName1.setText(mNeighbour.getName());
		mTextViewName2.setText(mNeighbour.getName());
		mTextViewLocation.setText(mNeighbour.getAddress());
		mTextViewPhone.setText(mNeighbour.getPhoneNumber());
		mTextViewSocial.setText(mNeighbour.getAvatarUrl());
		mTextViewAboutMeContent.setText(mNeighbour.getAboutMe());
	}
}