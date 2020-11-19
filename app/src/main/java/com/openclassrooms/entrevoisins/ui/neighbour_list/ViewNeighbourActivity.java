package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

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

	Neighbour mNeighbour;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getSupportActionBar() != null)
			getSupportActionBar().hide();
		setContentView(R.layout.activity_view_neighbour);
		ButterKnife.bind(this);
		mNeighbour = (Neighbour) getIntent().getSerializableExtra("Neighbour");

		Glide.with(this)
				.load(mNeighbour.getAvatarUrl())
				.into(mImageViewAvatar);

		mTextViewName1.setText(mNeighbour.getName());
		mTextViewName2.setText(mNeighbour.getName());
		mTextViewLocation.setText(mNeighbour.getAddress());
		mTextViewPhone.setText(mNeighbour.getPhoneNumber());
		mTextViewSocial.setText(mNeighbour.getAvatarUrl());
		mTextViewAboutMeContent.setText(mNeighbour.getAboutMe());

	}
}