package edittexttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 测试EditText
 * addTextChangedListener  输入框内容发生改变时执行
 * setOnEditorActionListener  点击软件盘的完成/发送/搜索等执行
 *
 * @author chenyanping
 * @date 2020-07-13
 */
public class EditTextTestActivity extends Activity {

    private EditText mEt;

    public static void launch(Context context) {
        Intent starter = new Intent(context, EditTextTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext_test);
        initView();
        initListener();

//        HashMap<Integer, String> map = new HashMap<>();
        SparseArray<String> map = new SparseArray<>();
        map.put(1, "se");
        map.put(1, "we");

        Log.i("cyp", "onCreate: "+map.size());

        HashSet<String> set = new HashSet<>();
        set.add("we");
        set.add("we");
        Log.i("cyp", "onCreate: set"+set.size());
    }

    private void initListener() {
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("cyp", "beforeTextChanged: s+" + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("cyp", "onTextChanged: s+" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("cyp", "afterTextChanged: s+" + s.toString());
            }
        });

        mEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.i("cyp", "KeyEvent:" + event);
                if (event != null) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_DOWN:
                            Log.i("cyp", "ACTION_DOWN");
                            break;
                        case KeyEvent.ACTION_UP:
                            Log.i("cyp", "ACTION_UP");
                            break;
                        default:
                            break;
                    }
                }
                Log.i("cyp", "actionId:" + actionId);
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    hideKeyboard();
                    Log.i("cyp", "content:" + mEt.getText().toString());
                    // 业务处理
                    mEt.setText("");
                    return true;// 返回false 执行两次 软件盘不消失 返回true，只执行这一次，软件盘隐藏
                    // 第二次走不进这个if  第二次的actionId为0
                }
                return false;
            }
        });
        mEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard();
            }
        });
    }

    private void initView() {
        mEt = findViewById(R.id.et_edit);
    }

    /**
     * 展示软键盘
     */
    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mEt, 0);
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mEt.getWindowToken(), 0);
    }
}
