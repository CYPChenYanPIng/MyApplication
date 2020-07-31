package fragmenttest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uxin.myapplication.R;




/**
 * @author chenyanping
 * @date 2020-07-30
 */
public class HomeCombinationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // container 写不写都一样，布局没有改变
        View view = inflater.inflate(R.layout.fragment_home_combination,container,false);
        return view;
    }
}
