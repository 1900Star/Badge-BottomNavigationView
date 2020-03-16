package com.yibao.badgeview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author luoshipeng
 * createDate：2020/1/13 0013 15:35
 * className   BadgePagerAdapter
 * Des：TODO
 */
public class BadgePagerAdapter
        extends FragmentStateAdapter {


    public BadgePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BadgeFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
