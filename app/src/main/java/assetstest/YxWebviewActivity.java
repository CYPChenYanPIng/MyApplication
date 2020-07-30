
package assetstest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.uxin.myapplication.R;

import java.net.URL;

/**
 * company 深圳市有信网络技术有限公司 author Dylan.zhuang Date: 16/1/29-下午7:17 Copyright (c)
 * 2016, uxin.com All Rights Reserved.
 */
public class YxWebviewActivity extends Activity
        implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "YxWebviewActivity";
    public static final String REQUEST_PAGE = "Android_YxWebviewActivity";
    public static final String INTENT_TAG_URL = "URL";
    public static final String INTENT_TAG_TOKEN = "user_token";
    public static final String INTENT_TAG_UID = "user_uid";
    public static final String INTENT_TAG_SHOW_CUSTOMER_SERVICE = "show_customer_service";
    public static final String INTENT_TAG_APKNAME = "apk_name";
    public static final String INTENT_TAG_WEBTYPE = "web_type";
    public static final String INTENT_TAG_NEED_BACKTOHOME_LOGIC = "need_backtohome_logic";

    private final static int FILECHOOSER_RESULTCODE = 100;
    //广告类型H5
    public final static int WEB_TYPE_ADV = 1;
    //非广告类型H5
    public final static int WEB_TYPE_COMMON = 0;

    protected String url_302 = "";
    protected boolean mIsFromAssit = false;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAbovel;
    private String url;

    /**
     * h5中播放全屏视频时返回的一个回调；
     */
    private WebChromeClient.CustomViewCallback mCustomViewCallback;

    private static Context mContext;
    private String apkName = "";
    //广告类型为 1，其他为0
    /**
     * 是否需要执行backToHome逻辑；true - 正常默认值；false - 不执行backToHome逻辑；
     */

    private WebView mWebView;


    public static void launch(Context context,String url) {
        Intent starter = new Intent(context, YxWebviewActivity.class);
        starter.putExtra(INTENT_TAG_URL,url);
        context.startActivity(starter);
    }
    public static void launch(Context context, URL url) {
        Intent starter = new Intent(context, YxWebviewActivity.class);
        starter.putExtra(INTENT_TAG_URL,url);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initViewsAndEvents(savedInstanceState,null);
    }

    protected void initViewsAndEvents(Bundle savedInstanceState, Bundle extras) {

        mWebView = findViewById(R.id.webview);
//        mWebView.setDownloadListener(new WebViewDownLoadListener());
        mWebView.addJavascriptInterface(new DownloadJavaScriptInterface(), "JsInterface");
        handleWebBusiness();
        mWebView.setOnLongClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(INTENT_TAG_URL);
            apkName = intent.getStringExtra(INTENT_TAG_APKNAME);
            parseShowshareParams();

            mWebView.loadUrl(url);
        }
    }

    /**
     * 解析url中showshare字段，用来判断是否展示分享按钮；
     * 是否展示分享按钮统一从url的params里解析，这样有个统一的入口处理
     */
    private void parseShowshareParams() {
        if (!TextUtils.isEmpty(url)) {
            try {
                Uri uri = Uri.parse(url);
                String showShareValue = uri.getQueryParameter("showshare");
                if (!TextUtils.isEmpty(showShareValue) && showShareValue.equals("1")) {
                    //url链接中有showshare参数，且值为1，则展示分享按钮
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void handleWebBusiness() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                try {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(YxWebviewActivity.this);
                    String message = "SSL Certificate error.";
                    switch (error.getPrimaryError()) {
                        case SslError.SSL_UNTRUSTED:
                            message = "The certificate authority is not trusted.";
                            break;
                        case SslError.SSL_EXPIRED:
                            message = "The certificate has expired.";
                            break;
                        case SslError.SSL_IDMISMATCH:
                            message = "The certificate Hostname mismatch.";
                            break;
                        case SslError.SSL_NOTYETVALID:
                            message = "The certificate is not yet valid.";
                            break;
                        case SslError.SSL_DATE_INVALID:
                            message = "The date of the certificate is invalid";
                            break;
                        case SslError.SSL_INVALID:
                        default:
                            message = "A generic error occurred";
                            break;
                    }
                    message += " Do you want to continue anyway?";

                    builder.setTitle("SSL Certificate Error");
                    builder.setMessage(message);

                    builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.proceed();
                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            handler.cancel();
                        }
                    });
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.proceed();// 接受所有网站的证书
                }
                // super.onReceivedSslError(view, handler, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // 加载结束
                // 获取html页面title的名称，用于标题展示
                String webTitle = view.getTitle();
                if (!TextUtils.isEmpty(webTitle) && mIsFromAssit) {
//                    mTitleBar.setTiteTextView(webTitle);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                mWebView.loadUrl(url);
//                return true;
                return parseUrl(url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String json, JsResult result) { // 此处为JS返回的结果处理
                return super.onJsAlert(view, url, json, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!mIsFromAssit) {
//                    mTitleBar.setTiteTextView(title);
                }
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(
                        Intent.createChooser(i, "选择图片"),
                        FILECHOOSER_RESULTCODE);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooser(uploadMsg);
            }

            // For Android 4.1
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType,
                                        String capture) {
                openFileChooser(uploadMsg, acceptType);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                mUploadCallbackAbovel = filePathCallback;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(
                        Intent.createChooser(i, "选择图片"),
                        FILECHOOSER_RESULTCODE);
                return true;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);

            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
                if (mCustomViewCallback != null) {
                    mCustomViewCallback.onCustomViewHidden();
                    mCustomViewCallback = null;
                }

            }
        });
    }

    /**
     * 解析Url，如果是http链接，直接使用webview加载，如果是scheme，则跳转
     *
     * @param url url String
     * @return true:跳转外部处理内容，false：webview自己处理
     */
    private boolean parseUrl(String url) {
        if (url.startsWith("http")) {
            mWebView.loadUrl(url);
            return false;
        } else {
//            H5OrSchemeJumpUtil.dealH5OrScheme(this, url);
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.canGoBack() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (isGoBack()) {
                finish();
            } else {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 尝试通知H5隐藏了自定义View；当H5全屏播放视频时WebChromeClient会返回自定义的view展示视频，端上将这个view展示到最上层；
     * 要隐藏自定义view时需要通知WebChromeClient；
     */
    private void tryToNotifyCustomViewHiden() {
        if (mCustomViewCallback != null) {
//            mVideoContainer.removeAllViews();
            mCustomViewCallback.onCustomViewHidden();
            mCustomViewCallback = null;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        //获取WebView页面点击元素的类型
        final WebView.HitTestResult result = mWebView.getHitTestResult();
        if (result == null){
            return false;
        }
        int type = result.getType();
        //如果点击的元素类型为未知的则不处理
        if (type == WebView.HitTestResult.UNKNOWN_TYPE) {
            return false;
        }
        switch (type) {
            //点击的元素是图片类型
            case WebView.HitTestResult.IMAGE_TYPE: {
                //弹出保存到本地弹层
//                showSaveDialog(result);
                break;
            }
            default:
                break;
        }
        return true;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mWebView.removeJavascriptInterface("JsInterface");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAbovel == null) {
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAbovel.onReceiveValue(results);
        mUploadCallbackAbovel = null;
        return;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    private boolean isGoBack() {
        WebBackForwardList mWebBackForwardList = mWebView.copyBackForwardList();
        if (mWebBackForwardList.getCurrentIndex() > 0) {
            String historyUrl = mWebBackForwardList.getItemAtIndex(
                    mWebBackForwardList.getCurrentIndex() - 1).getUrl();
            return url_302.equals(historyUrl);
        }
        return false;
    }



    /**
     * 供h5调用
     */
    public class DownloadJavaScriptInterface {

        public void downReady(String id) {
            mWebView.loadUrl("javascript:downReady('" + id + "')");
        }

        public void downError(String id) {
            mWebView.loadUrl("javascript:downError('" + id + "')");
        }


        @JavascriptInterface
        public void actionTypeBackUp() {
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    if (!isFinishing()) {
                        finish();
                    }
                }
            });
        }

    }

}
