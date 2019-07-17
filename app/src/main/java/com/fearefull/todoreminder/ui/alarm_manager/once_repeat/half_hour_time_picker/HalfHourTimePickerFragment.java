package com.fearefull.todoreminder.ui.alarm_manager.once_repeat.half_hour_time_picker;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.fearefull.todoreminder.BR;
import com.fearefull.todoreminder.R;
import com.fearefull.todoreminder.ViewModelProviderFactory;
import com.fearefull.todoreminder.data.model.other.HalfHourType;
import com.fearefull.todoreminder.databinding.FragmentHalfHourTimePickerBinding;
import com.fearefull.todoreminder.ui.base.BaseFragment;
import com.fearefull.todoreminder.utils.ViewUtils;

import javax.inject.Inject;

public class HalfHourTimePickerFragment extends BaseFragment<FragmentHalfHourTimePickerBinding, HalfHourTimePickerViewModel>
        implements HalfHourTimePickerNavigator {

    public static final String TAG = HalfHourTimePickerFragment.class.getSimpleName();
    private static final String MINUTE_KEY = "minute_key";
    private static final String HOUR_KEY = "hour_key";
    private static final String HALF_HOUR_KEY = "half_hour_key";

    @Inject
    ViewModelProviderFactory factory;
    private HalfHourTimePickerViewModel viewModel;
    private FragmentHalfHourTimePickerBinding binding;
    private HalfHourTimePickerCallBack callBack;

    public static HalfHourTimePickerFragment newInstance(int minute, int hour, HalfHourType halfHourType) {
        Bundle args = new Bundle();
        HalfHourTimePickerFragment fragment = new HalfHourTimePickerFragment();
        args.putSerializable(MINUTE_KEY, minute);
        args.putSerializable(HOUR_KEY, hour);
        args.putSerializable(HALF_HOUR_KEY, halfHourType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_half_hour_time_picker;
    }

    @Override
    public HalfHourTimePickerViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(HalfHourTimePickerViewModel.class);
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        assert getArguments() != null;
        viewModel.setMinute(getArguments().getInt(MINUTE_KEY));
        viewModel.setHour(getArguments().getInt(HOUR_KEY));
        viewModel.setHalfHourType((HalfHourType) getArguments().getSerializable(HALF_HOUR_KEY));
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        ViewUtils.setUpNumberPicker(binding.minutePicker, viewModel.getMinutes(), viewModel.getMinuteIndex());
        ViewUtils.setUpNumberPicker(binding.hourPicker, viewModel.getHours(), viewModel.getHourIndex());
        ViewUtils.setUpNumberPicker(binding.halfHourTypePicker, viewModel.getHalfHourTypes(), viewModel.getHalfHourTypeIndex());
    }

    @Override
    public void onMinuteChanged() {
        callBack.onMinuteChanged(viewModel.getMinute());
    }

    @Override
    public void onHourChanged() {
        callBack.onHourChanged(viewModel.getHour());
    }

    @Override
    public void onHalfHourTypeChanged() {
        callBack.onHourChanged(viewModel.getHour());
    }

    public void setCallBack(HalfHourTimePickerCallBack callBack) {
        this.callBack = callBack;
    }

    public interface HalfHourTimePickerCallBack {
        void onHourChanged(int hour);
        void onMinuteChanged(int minute);
    }
}
