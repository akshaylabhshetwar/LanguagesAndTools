package com.languagestools.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.languagestools.LanguagesToolApplication;
import com.languagestools.R;
import com.languagestools.adapters.LanguageToolAdapter;
import com.languagestools.api.Server;
import com.languagestools.api.languageTool.LanguageToolResponse;
import com.languagestools.databinding.ActivityLanguagesToolsBinding;
import com.languagestools.utils.PaginationScrollListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LanguagesToolsActivity extends AppCompatActivity {

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = PAGE_START;
    private int currentPage = PAGE_START;
    private LanguageToolAdapter languageToolAdapter;
    private ActivityLanguagesToolsBinding binding;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_languages_tools);
        setTitle(getString(R.string.languages_tools));

        layoutManager = new LinearLayoutManager(this);
        binding.languageToolList.setLayoutManager(layoutManager);
        binding.languageToolList.setItemAnimator(new DefaultItemAnimator());
        binding.languageToolList.addItemDecoration(new DividerItemDecoration(LanguagesToolsActivity.this, DividerItemDecoration.VERTICAL));
        languageToolAdapter = new LanguageToolAdapter(LanguagesToolsActivity.this);
        binding.languageToolList.setAdapter(languageToolAdapter);

        loadPage();


    }

    private void loadPage() {
        Server.getApi().languagesTools(currentPage).enqueue(new Callback<LanguageToolResponse>() {
            @Override
            public void onResponse(Call<LanguageToolResponse> call, Response<LanguageToolResponse> response) {
                binding.progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    TOTAL_PAGES = response.body().getResponse().getTotal_pages();
                    if (currentPage != 1) {
                        languageToolAdapter.removeLoadingFooter();
                        isLoading = false;
                    } else {
                        setScrollListener();
                    }

                    languageToolAdapter.addAll(response.body().getResponse().getList());
                    if (currentPage != TOTAL_PAGES) languageToolAdapter.addLoadingFooter();
                    else isLastPage = true;
                } else {
                    Toast.makeText(LanguagesToolsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LanguageToolResponse> call, Throwable throwable) {
                Log.d(LanguagesToolApplication.TAG, throwable.getMessage());

            }
        });
    }

    private void setScrollListener() {
        binding.languageToolList.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }
}
