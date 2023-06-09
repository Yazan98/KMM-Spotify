package com.yazantarifi.radio.android;

@dagger.Module
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/yazantarifi/radio/android/ApplicationModule;", "", "()V", "getGetAccessTokenUseCase", "Lcom/yazantarifi/radio/useCases/GetAccessTokenUseCase;", "getHttpClient", "Lio/ktor/client/HttpClient;", "getStorageProviderImplementationInstance", "Lcom/yazantarifi/radio/base/context/SopifyStorageProvider;", "context", "Landroid/content/Context;", "androidApp_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class ApplicationModule {
    @org.jetbrains.annotations.NotNull
    public static final com.yazantarifi.radio.android.ApplicationModule INSTANCE = null;
    
    private ApplicationModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.yazantarifi.radio.base.context.SopifyStorageProvider getStorageProviderImplementationInstance(@dagger.hilt.android.qualifiers.ApplicationContext
    @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.yazantarifi.radio.useCases.GetAccessTokenUseCase getGetAccessTokenUseCase() {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final io.ktor.client.HttpClient getHttpClient() {
        return null;
    }
}