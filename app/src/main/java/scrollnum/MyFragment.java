package scrollnum;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uxin.myapplication.R;

import java.util.ArrayList;

/**
 * @author chenyanping
 * @date 2020-07-30
 */
public class MyFragment extends Fragment {

    private RecyclerView gridRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        gridRv = view.findViewById(R.id.rv_grid);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private void initData() {
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0;i < 100;i++) {
            arrayList.add(i);
        }
        MyAdapter adapter = new MyAdapter();
        gridRv.setAdapter(adapter);
        adapter.addAll(arrayList);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        private ArrayList<Integer> list = new ArrayList<>();
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_smoth_num_layout,viewGroup,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            Integer integer = list.get(i);
            if (integer != null) {
                myViewHolder.tvNum.setText(String.valueOf(integer));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void addAll(ArrayList<Integer> subList){
            if (subList != null ) {
                list.clear();
                list.addAll(subList);
                notifyDataSetChanged();
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_num);
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
