package com.yibao.badgeview;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author luoshipeng
 * createDate：2020/1/13 0013 15:35
 * className   BadgePagerAdapter
 * Des：TODO
 */
public class BadgePagerAdapter
        extends FragmentStatePagerAdapter {

    public BadgePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BadgeFragment.newInstance(position);
    }


    @Override
    public int getCount() {
        return 5;
    }

}
