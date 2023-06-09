package com.yazantarifi.radio.android;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0017J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000e"}, d2 = {"Lcom/yazantarifi/radio/android/RadioIntroScreen;", "Lcom/yazantarifi/radio/android/core/screens/SopifyScreen;", "()V", "storage", "Lcom/yazantarifi/radio/base/context/SopifyStorageProvider;", "getStorage", "()Lcom/yazantarifi/radio/base/context/SopifyStorageProvider;", "setStorage", "(Lcom/yazantarifi/radio/base/context/SopifyStorageProvider;)V", "OnScreenContent", "", "savedInstanceState", "Landroid/os/Bundle;", "onScreenStarted", "androidApp_debug"})
public final class RadioIntroScreen extends com.yazantarifi.radio.android.core.screens.SopifyScreen {
    @javax.inject.Inject
    public com.yazantarifi.radio.base.context.SopifyStorageProvider storage;
    
    public RadioIntroScreen() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.yazantarifi.radio.base.context.SopifyStorageProvider getStorage() {
        return null;
    }
    
    public final void setStorage(@org.jetbrains.annotations.NotNull
    com.yazantarifi.radio.base.context.SopifyStorageProvider p0) {
    }
    
    @java.lang.Override
    @androidx.compose.runtime.Composable
    public void OnScreenContent(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onScreenStarted(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
}