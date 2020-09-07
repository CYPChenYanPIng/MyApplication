package handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * @author chenyanping
 * @date 2020-08-03
 */
public class HandlerTestActivity extends Activity {

    private MyHandler handler;

    private static class MyHandler extends Handler {
        private WeakReference<HandlerTestActivity> weakReference ;

        public MyHandler(HandlerTestActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerTestActivity handlerTestActivity = weakReference.get();
            int eventId = msg.what;
            switch (eventId) {
                case 1:

                    handlerTestActivity.test1Method();

                    break;
                case 2:
                    handlerTestActivity.test2Method();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        handler = new MyHandler(this);
        handler.sendEmptyMessage(1);
    }

    private void test1Method(){
        Toast.makeText(this,"test1Method",Toast.LENGTH_LONG).show();
    }

    private void test2Method(){
        Toast.makeText(this,"test2Method",Toast.LENGTH_LONG).show();

    }
}
