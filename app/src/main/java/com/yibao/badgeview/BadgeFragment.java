package com.yibao.badgeview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author luoshipeng
 * createDate：2020/1/13 0013 15:35
 * className   BadgeFragment
 * Des：TODO
 */
public class BadgeFragment extends Fragment {
    private TextView mTvIndex;
    private EditText mEdit;
    private Button mBtn;
    private FragmentActivity mActivity;
    private int mPosition;
    private Button mBtnRemove;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.badge_fragment, container, false);
        mActivity = getActivity();
        mTvIndex = view.findViewById(R.id.tv_index);
        mEdit = view.findViewById(R.id.edit);
        mBtn = view.findViewById(R.id.btn);
        mBtnRemove = view.findViewById(R.id.btn_remove);
        initData();
        initListener();

        return view;
    }

    private void initData() {
        mPosition = getArguments().getInt("position");
        Log.d("lsp", "position   " + mPosition);
        mTvIndex.setText(String.valueOf(mPosition));
    }

    private void initListener() {
        mBtn.setOnClickListener(v -> {
            String badgeCount = mEdit.getText().toString().trim();
            if (badgeCount.isEmpty()) {
                Toast.makeText(mActivity, "Please enter content", Toast.LENGTH_LONG).show();
            } else {
                showBadge(mPosition, Integer.valueOf(badgeCount));
            }
        });
        mBtnRemove.setOnClickListener(v -> removeBadge(mPosition));
    }

    private void showBadge(int menuPosition, int noticeCount) {
        if (mActivity instanceof OnBadgeListener) {
            OnBadgeListener badgeListener = (OnBadgeListener) mActivity;
            badgeListener.showBadgeCount(menuPosition, noticeCount);
        }
    }

    private void removeBadge(int menuPosition) {
        if (mActivity instanceof OnBadgeListener) {
            OnBadgeListener badgeListener = (OnBadgeListener) mActivity;
            badgeListener.removeBadgeCount(menuPosition);
        }
    }


    public static BadgeFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        BadgeFragment fragment = new BadgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
