package scrollnum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import util.CommonUtils;

/**
 * @author chenyanping
 * @date 2020-07-15
 */
public class SmothScrollTestActivity extends Activity implements View.OnClickListener{

    private RecyclerView recyclerView;

    private Button scroll1;

    private Button scroll2;

    private Button scroll3;

    public static void launch(Context context) {
        Intent starter = new Intent(context, SmothScrollTestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoth_scroll);
        initView();
        initListener();
        initData();
    }

    private void initListener() {
        scroll1.setOnClickListener(this);
        scroll2.setOnClickListener(this);
        scroll3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scroll1:
                recyclerView.smoothScrollToPosition(5);
                break;
            case R.id.scroll2:
                recyclerView.smoothScrollToPosition(30);
                break;
            case R.id.scroll3:
                recyclerView.smoothScrollToPosition(99);
                break;
            default:
                break;
        }
    }

    private void initData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0;i < 100;i++) {
            arrayList.add(i);
        }
        MyAdapter adapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDecorationView(this,LinearLayoutManager.VERTICAL));

        adapter.setList(arrayList);
        adapter.notifyDataSetChanged();

        scroll1.setText("滑动到position 5");
        scroll2.setText("滑动到position  30");
        scroll3.setText("互动到99");
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_recyclerview);
        scroll1 = findViewById(R.id.scroll1);
        scroll2 = findViewById(R.id.scroll2);
        scroll3 = findViewById(R.id.scroll3);
    }

    class MyAdapter extends RecyclerView.Adapter{
        private ArrayList<Integer> list = new ArrayList();
        private Context context = SmothScrollTestActivity.this;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 正确的
            View view = LayoutInflater.from(context).inflate(R.layout.item_smoth_num_layout,viewGroup,false);
            return new MyViweHolder(view);
        }
        //            return new NumBerViewHolder(NumBerViewHolder.getTextView(SmothScrollTestActivity.this));

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            if (viewHolder instanceof MyViweHolder) {
                ((MyViweHolder) viewHolder).tvNumber.setText(String.valueOf(list.get(i)));
            }
//            if (viewHolder instanceof NumBerViewHolder){
//                NumBerViewHolder numBerViewHolder = (NumBerViewHolder) viewHolder;
//                numBerViewHolder.setTextContent(String.valueOf(list.get(i)));
//            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setList(ArrayList<Integer> list) {
            this.list = list;
        }
    }

   static class NumBerViewHolder extends RecyclerView.ViewHolder{
        public NumBerViewHolder(View itemView) {
            super(itemView);
        }

        public static TextView getTextView(Context context){
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    CommonUtils.dip2px(context,200));
            TextView textView = new TextView(context);
            textView.setTextSize(26);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);
            return textView;
        }

        public void setTextContent(String content){
            if (itemView instanceof TextView) {
                ((TextView) itemView).setText(content);
            }
        }
    }

    class MyViweHolder extends RecyclerView.ViewHolder{
        private TextView tvNumber;

        public MyViweHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_num);
        }
    }

}
