package com.fearefull.todoreminder.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.evernote.android.job.JobManager;
import com.fearefull.todoreminder.R;
import com.fearefull.todoreminder.data.AppDataManager;
import com.fearefull.todoreminder.data.DataManager;
import com.fearefull.todoreminder.data.local.db.AppDatabase;
import com.fearefull.todoreminder.data.local.db.AppDbHelper;
import com.fearefull.todoreminder.data.local.db.DbHelper;
import com.fearefull.todoreminder.data.local.prefs.AppPreferencesHelper;
import com.fearefull.todoreminder.data.local.prefs.PreferencesHelper;
import com.fearefull.todoreminder.di.DatabaseInfo;
import com.fearefull.todoreminder.di.PreferenceInfo;
import com.fearefull.todoreminder.schedule.AlarmScheduler;
import com.fearefull.todoreminder.schedule.AppAlarmScheduler;
import com.fearefull.todoreminder.schedule.AppJobCreator;
import com.fearefull.todoreminder.utils.AppConstants;
import com.fearefull.todoreminder.utils.rx.AppSchedulerProvider;
import com.fearefull.todoreminder.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.adtrace.sdk.AdTraceConfig;
import io.adtrace.sdk.LogLevel;
import io.github.inflationx.calligraphy3.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iran_sans_web.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    JobManager provideJobManager(Context context) {
        return JobManager.create(context);
    }

    @Provides
    @Singleton
    AppJobCreator provideMyJobCreator() {
        return new AppJobCreator();
    }

    @Provides
    @Singleton
    AlarmScheduler provideAlarmScheduler(AppAlarmScheduler appAlarmScheduler) {
        return appAlarmScheduler;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    AdTraceConfig provideAdTraceConfig(Context context) {
        String adtraceToken = AppConstants.ADTRACE_TOKEN;
        String environment = AdTraceConfig.ENVIRONMENT_SANDBOX;
        return new AdTraceConfig(context, adtraceToken, environment);
    }
}
