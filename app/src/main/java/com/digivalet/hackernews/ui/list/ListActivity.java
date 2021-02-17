package com.digivalet.hackernews.ui.list;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.digivalet.data.dto.ArticleResponse;
import com.digivalet.hackernews.BR;
import com.digivalet.hackernews.R;
import com.digivalet.hackernews.base.BaseActivity;
import com.digivalet.hackernews.databinding.ActivityListBinding;
import com.digivalet.hackernews.ui.adapters.ListAdapter;
import com.digivalet.hackernews.ui.details.DetailsActivity;
import com.digivalet.hackernews.utils.AppConstants;
import com.digivalet.hackernews.utils.GeneralFunctions;
import com.digivalet.hackernews.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity<ActivityListBinding, ListVM> implements ListNavigator {

    private MutableLiveData<List<ArticleResponse>> listMutableLiveData;
    private ListAdapter adapter;

    @Override
    public int getBindingVariable() {
        return BR.listVM;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public Class<ListVM> getVM() {
        return ListVM.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.init();
    }

    @Override
    public void init() {
        adapter = new ListAdapter(this, new ArrayList());
        listMutableLiveData = new MutableLiveData<>();

        /*item touch listener*/
        mViewDataBinding.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, (view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(AppConstants.INTENT_DETAILS, adapter.getItem(position));
            activityNavigator.startActWithData(DetailsActivity.class, bundle);
        }));
    }

    @Override
    public void getTopStories() {
        if (GeneralFunctions.isInternetAvailable()) {
            mViewModel.getTopStories();
        } else {
            mViewDataBinding.networkStatus.setVisibility(View.VISIBLE);
            mViewModel.requestOfflineData();
        }
    }

    @Override
    public void setArticleList(List<ArticleResponse> list) {
        if (list != null && !list.isEmpty()) {
            mViewDataBinding.recyclerView.setAdapter(adapter);
            listMutableLiveData.setValue(list);
            listMutableLiveData.observe(this, articleResponses -> {
                if (articleResponses != null) {
                    adapter.setList(articleResponses);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            showError(getString(R.string.label_no_data));
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}