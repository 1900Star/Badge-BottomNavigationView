package com.yibao.badgeview.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author luoshipeng
 * createDate：2020/4/13 0013 11:23
 * className   BadgeApplication
 * Des：TODO
 */
public class BadgeApplication extends TinkerApplication {

    public BadgeApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.yibao.badgeview.app.BadgeApplication",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
