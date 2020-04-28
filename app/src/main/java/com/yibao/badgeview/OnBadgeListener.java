package com.yibao.badgeview;

/**
 * @author luoshipeng
 * createDate：2020/1/6 0006 17:01
 * className   OnBadgeListener
 * Des：TODO
 */
public interface OnBadgeListener {
    /**
     * 设置红点int menuPosition, int noticeNumber
     * @param menuPosition 需要加红点的位置
     * @param noticeCount 红点数量
     */
    void showBadgeCount(int menuPosition, int noticeCount);

    /**
     * 移除红点消息
     * @param menuPosition 需要移除红点的位置
     */
    void removeBadgeCount(int menuPosition);
}
