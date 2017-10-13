package com.zank.choiceness.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * NetWork Utils
 */
public class NetUtil {

    public static final String NETWORK_TYPE_WIFI = "WIFI";
    public static final String NETWORK_TYPE_4G = "4G";
    public static final String NETWORK_TYPE_3G = "3G";
    public static final String NETWORK_TYPE_2G = "2G";
    public static final String NETWORK_TYPE_WAP = "WAP";
    public static final String NETWORK_TYPE_UNKNOWN = "UNKONW";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }

    /**
     * Get network type name
     *
     * @param context
     * @return
     */
    public static String getNetworkTypeName(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }

        if (networkInfo.isConnected()) {
            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                type = NETWORK_TYPE_WIFI;
            } else if ("MOBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? NETWORK_TYPE_3G : NETWORK_TYPE_2G)
                        : NETWORK_TYPE_WAP;
            } else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }

    /**
     * Whether is fast mobile network
     *
     * @param context
     * @return
     */
    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return false;
        }

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT: // 2G CDMA2000 1xRTT (RTT - 无线电传输技术) 144kbps 2G的过渡,
                return false;
            case TelephonyManager.NETWORK_TYPE_CDMA: // 2G 电信 Code Division Multiple Access 码分多址
                return false;
            case TelephonyManager.NETWORK_TYPE_EDGE: // 2G(2.75G) Enhanced Data Rate for GSM Evolution 384kbps
                return false;
            case TelephonyManager.NETWORK_TYPE_GPRS: // 2G(2.5) General Packet Radia Service 114kbps
                return false;
            case TelephonyManager.NETWORK_TYPE_IDEN: // 2G Integrated Dispatch Enhanced Networks 集成数字增强型网络 （属于2G，来自维基百科）
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0: // 3G (EVDO 全程 CDMA2000 1xEV-DO) Evolution - Data Only (Data Optimized) 153.6kps - 2.4mbps 属于3G
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_A: // 3G 1.8mbps - 3.1mbps 属于3G过渡，3.5G
                return true;
            case TelephonyManager.NETWORK_TYPE_HSDPA: // 3.5G 高速下行分组接入 3.5G WCDMA High Speed Downlink Packet Access 14.4mbps
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPA: // 3G (分HSDPA,HSUPA) High Speed Packet Access
                return true;
            case TelephonyManager.NETWORK_TYPE_HSUPA: // 3.5G High Speed Uplink Packet Access 高速上行链路分组接入 1.4 - 5.8 mbps
                return true;
            case TelephonyManager.NETWORK_TYPE_UMTS: // 3G WCDMA 联通3G Universal Mobile Telecommunication System 完整的3G移动通信技术标准
                return true;
            case TelephonyManager.NETWORK_TYPE_EHRPD: // 3G CDMA2000向LTE 4G的中间产物 Evolved High Rate Packet Data HRPD的升级
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // 3G EV-DO Rev.B 14.7Mbps 下行 3.5G
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPAP: // 3G HSPAP 比 HSDPA 快些
                return true;
            case TelephonyManager.NETWORK_TYPE_LTE: // 4G Long Term Evolution FDD-LTE 和 TDD-LTE , 3G过渡，升级版 LTE Advanced 才是4G
                return true;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

    /**
     * 网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return null != info && info.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null && mMobileNetworkInfo.isAvailable()) {
                return mMobileNetworkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * 判断WIFI网络是否可用
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null && mWiFiNetworkInfo.isAvailable()) {
                return mWiFiNetworkInfo.isConnected();
            }
        }
        return false;
    }
}
