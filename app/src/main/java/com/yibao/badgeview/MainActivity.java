package com.yibao.badgeview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * @author luoshipeng
 * createDate：2020/1/13 0013 15:35
 * className   MainActivity
 * Des：TODO
 */
public class MainActivity extends AppCompatActivity implements OnBadgeListener {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.menu, R.string.menu, R.string.menu};
    private BottomNavigationView mBottomNavigationView;
    private SparseArray<BottomNavigationItemView> mMenuSparesArray;
    private ViewPager2 mVp2;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }


    private void initView() {
        mBottomNavigationView = findViewById(R.id.bnv);
        mBottomNavigationView.setItemTextAppearanceActive(R.style.bottom_selected_text);
        mBottomNavigationView.setItemTextAppearanceInactive(R.style.bottom_normal_text);
        mVp2 = findViewById(R.id.vp2);
        mTabLayout = findViewById(R.id.tabs);

    }

    private void initData() {
        mMenuSparesArray = new SparseArray<>();
        BadgePagerAdapter badgePagerAdapter = new BadgePagerAdapter(this);
        mVp2.setAdapter(badgePagerAdapter);
        mVp2.setCurrentItem(0, false);
        new TabLayoutMediator(mTabLayout, mVp2, true, (tab, position) -> tab.setText("fragment" + position)).attach();
        int tabCount = mTabLayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                View tabView = getTabView(this, i);
                tab.setCustomView(tabView);
            }

        }

    }

    private void initListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //是否允许ViewPager2滑动
        mVp2.setUserInputEnabled(true);
        mVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_alarm:
                MainActivity.this.switchItem(0);
                return true;
            case R.id.navigation_status:
                MainActivity.this.switchItem(1);
                return true;
            case R.id.navigation_map:
                MainActivity.this.switchItem(2);
                return true;
            case R.id.navigation_case:
                MainActivity.this.switchItem(3);
                return true;
            case R.id.navigation_lock:
                MainActivity.this.switchItem(4);
                return true;
            default:
                break;
        }
        return false;
    };

    private void switchItem(int i) {
        mVp2.setCurrentItem(i, false);
    }


    @Override
    public void showBadgeCount(int menuPosition, int noticeCount) {
        int maxNoticeCount = 99;
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mBottomNavigationView.getChildAt(0);
        BottomNavigationItemView badgeMenuItemView = (BottomNavigationItemView) menuView.getChildAt(menuPosition);
        // 判断当前菜单是否已经添加红点，如果已经添加就先移除。
        removeMenuChild(badgeMenuItemView);
        View badgeView = LayoutInflater.from(this).inflate(R.layout.notice_badge, menuView, false);
        badgeView.setTag(menuPosition);
        mMenuSparesArray.put(menuPosition, badgeMenuItemView);
        badgeMenuItemView.addView(badgeView);
        TextView tvNotic = badgeView.findViewById(R.id.tv_badge);
        TextView tvDot = badgeView.findViewById(R.id.tv_badge_dot);
        tvDot.setVisibility(noticeCount > maxNoticeCount ? View.VISIBLE : View.GONE);
        tvNotic.setVisibility(noticeCount > maxNoticeCount ? View.GONE : View.VISIBLE);
        if (noticeCount <= maxNoticeCount) {
            tvNotic.setText(String.valueOf(noticeCount));
        }
    }

    /**
     * BottomNavigationView中,每一个 单独的菜单 BottomNavigationItemView 默认都有两个子View,分别是菜单的图标和菜单的描述文字。
     * BottomNavigationItemView.getChildCount()可以拿到子View的数量，默认数量为 2。前面两个为默认的图标（0）和文字（1）。
     * 在某个菜单上添加消息红点之后，菜单的子View数量为 3，如果不清除已经添加的红点子View，子View的数量会一直增加。
     * 每次添加红点时都判断当前子View数量是否大于2，大于2 表示当前菜单已经添加红点子View ，
     * 后续更新红点信息就先移除已经添加的红点子View（就是第3个子View，索引为 2），再重新添加新的红点消息。
     *
     * @param badgeItemView 底部导航栏BottomNavigationView 中的菜单
     */
    private void removeMenuChild(BottomNavigationItemView badgeItemView) {
        int childCount = 2;
        if (badgeItemView.getChildCount() > childCount) {
            View childAtView = badgeItemView.getChildAt(2);
            badgeItemView.removeView(childAtView);
        }
    }


    @Override
    public void removeBadgeCount(int menuPosition) {
        Log.d("lsp", "移除 " + menuPosition);
        if (mMenuSparesArray.size() > 0) {
            BottomNavigationItemView badgeItemView = mMenuSparesArray.get(menuPosition);
            removeMenuChild(badgeItemView);
        }
    }


    public View getTabView(Context context, int position) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        int resId = position == 0 ? R.layout.item_tab_left : position == mTabLayout.getTabCount() - 1 ? R.layout.item_tab_right : R.layout.item_tab_middle;
        View view = mInflater.inflate(resId, null);
        TextView tv = view.findViewById(R.id.tab_text);
        tv.setText(TAB_TITLES[0]);
        return view;
    }


}
