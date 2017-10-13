package com.zank.choiceness;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.zank.choiceness.injector.components.AppComponent;
import com.zank.choiceness.injector.components.DaggerAppComponent;
import com.zank.choiceness.injector.module.AppModule;

/**
 * Created by Zank on 2017/9/27.
 */

public class AppApplication extends Application {

    private static AppComponent appComponent;

    private static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        /**
         * initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
         * 阿里云
         */
        try {
            SophixManager.getInstance().setContext(this)
                    .setAppVersion(getPackageManager().getPackageInfo(getPackageName(), 0).versionName)
                    .setSecretMetaData("24640264-1", "1ad456ba9ea86f49b4c342ae0ba1a863", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLVoNSMezRQYVrjYBJdxblvo16fm8aVub0PVEYoo2iMtmRgaK3x5yoB88jHjWb9QQiAAAA7LJSjDF8XQX/StyQZ5byd6qigQA30OpW7YPg0lUDegdcQ3StOqaXrcXZMOhNjUkv9/cJYyNSK4eEiGeia1a9XDlLolhdC7CMTmYqwPPgVFvN4Ynk2EhQbdgAG3UzfHsV8b+WFFcVrsymIp/1ouqQVzztBeW5WT/+OM9XZFUNz8bFhdyzYbUAIVjzrEIPMN3+phGT+G8XyWeZqR9IUVc0EBKeNCyDxwTvWifbi/9RazSCkB2kAQ9EOHAqABvobMf3c5cLNt2DQuqqZjRnAgMBAAECggEAIxFc75zdCzVATPdJ6DNusdepZ8DWWXyxl5qvBFI07zN5CmPj/fjg9nEwspfxyhR6O8xgM9tebeQWuEP5NG004asQlGmFqmxpD6Mmt0YDwNtuxfc6TwAzJz3xm48Ckkz6yjnax5XSuKwYwZ9u9e4WlwwIwpOpX921+4TrpAQ/NlcEEsawvTgGgbuKVI6Ba0CPNNShRC2x1/J3kpcYZMltalUq/gtaHbvxn5uThCiL8sfgADaV65ov+9duSOeGE16WSDz9i/Lq1x2kZVG+dyBfpsZanQcrI9JuGcfbD5dzBajnhLuwoTVsTs/B0T16X811WWVq2kKMZU4XwTrIW+Ky4QKBgQDsUT7ecA/6mjq/wwJ1ftjaYopD8iEseqvicuUV0IERzouE3MMpod7UVHPtG35kgKs7FG+woO7r9V3ycCWHJ9ReOOLLAahrmkOdZEha03J53v42NFHx2fnm1wDQag8fPQcg5kZKNrIXHfbSqllIE8MTEAp9zqVYz3DZIToOcAKq3wKBgQCW8XlTtcdR/PLVsHPUFwFRMewxGvP2Mod5PqMtIsWvPyuti0ADIpSPxXqF9ARzKxjKYdI5by5OPTOAAQpFCjISO0N2FudBTpj7f9FTqOWtduPKzlvg9e6QG/83KvRDTTjkSoAv+IURWnzVH7vmOk+0JojmzN6nZ5i99cjECUuveQKBgB6lbqTEHZ5fL+08ae441ACGNO4Y0WqGQhIs2j6Y+pQHmBpT0s9sWYxpo1sFbL+12/7eaJmcVT+uY0+UcZl0ddr+P5MaFPBXYhtgLO3ya+8YFxmKcXIO1ZS1Hzrn9iwT6v++PTFUpIXQ8fhh9RAe1/Z55xJnoRPCWx0dbP0AU6g7AoGAdV9F+drjKBTqe1Hfb6+H3axVPoX36Jy4DYawejuMvs3/V85tM0LjhLyJnrn179w2RcE1KGYU+s/KSSIF61OXRegBeEUtA/JrMT3d9xDhsDY/wYcfmNmD7Vln5m0nTWIx+AsajLLSPoE/irZHdSooqixAzj/GbyQ2eJgzyoRz7vkCgYEAhIobh6PKiiP+nIpvxbyxPKqTwc8I1UdH2b4iJEVwkxmgZXICwKr8XkUO82sCS2RykUVS57a3w7dVNDz/Eo/T4h/ok0P882bwkSbvVBZBitJNRplV4jGweXtoFv1hBT4hK/EQRvQ5VJHWN/1pkMgN41lzHKVLh7wrzhY+oC8PyfE=")
                    .setAesKey(null)
                    .setEnableDebug(true)
                    .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                        @Override
                        public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                            // 补丁加载回调通知
                            if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                // 表明补丁加载成功
                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            } else {
                                // 其它错误信息, 查看PatchStatus类说明
                            }
                        }
                    }).initialize();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("QupidApplication", "阿里云初始化异常");
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
        _initInjector();
    }

    private void _initInjector() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppcomponent(){
        return appComponent;
    }

    public static Context getContext() {
        return mContext;
    }

}
