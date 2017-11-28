package com.languagestools.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.languagestools.R;
import com.languagestools.databinding.ItemLanguageToolBinding;
import com.languagestools.databinding.ItemProgressBinding;
import com.languagestools.models.LanguageTool;
import com.languagestools.models.LanguageToolViewModel;

import java.util.ArrayList;
import java.util.List;

public class LanguageToolAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    private List<LanguageTool> languageTools;
    private Context context;

    public LanguageToolAdapter(Context context) {
        this.context = context;
        languageTools = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM:
                ItemLanguageToolBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_language_tool, parent, false);

                viewHolder = new ItemViewHolder(binding);

                break;
            case LOADING:
                ItemProgressBinding bindingProgress = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_progress, parent, false);

                viewHolder = new LoadingViewHolder(bindingProgress);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                ItemLanguageToolBinding binding = itemViewHolder.binding;
                binding.setLanguageTool(new LanguageToolViewModel(languageTools.get(position)));
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == languageTools.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    @Override
    public int getItemCount() {
        return languageTools == null ? 0 : languageTools.size();
    }

    public void add(LanguageTool languageTool) {
        languageTools.add(languageTool);
        notifyItemInserted(languageTools.size() - 1);
    }

    public void addAll(List<LanguageTool> languageTools) {
        for (LanguageTool languageTool : languageTools) {
            add(languageTool);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new LanguageTool());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = languageTools.size() - 1;
        LanguageTool item = getItem(position);

        if (item != null) {
            languageTools.remove(position);
            notifyItemRemoved(position);
        }
    }


    public LanguageTool getItem(int position) {
        return languageTools.get(position);
    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemLanguageToolBinding binding;

        public ItemViewHolder(ItemLanguageToolBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ItemProgressBinding binding;

        public LoadingViewHolder(ItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
