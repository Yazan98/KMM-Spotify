package com.yazantarifi.radio.android;

import android.content.Context;
import com.yazantarifi.radio.base.context.SopifyStorageProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_GetStorageProviderImplementationInstanceFactory implements Factory<SopifyStorageProvider> {
  private final Provider<Context> contextProvider;

  public ApplicationModule_GetStorageProviderImplementationInstanceFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SopifyStorageProvider get() {
    return getStorageProviderImplementationInstance(contextProvider.get());
  }

  public static ApplicationModule_GetStorageProviderImplementationInstanceFactory create(
      Provider<Context> contextProvider) {
    return new ApplicationModule_GetStorageProviderImplementationInstanceFactory(contextProvider);
  }

  public static SopifyStorageProvider getStorageProviderImplementationInstance(Context context) {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.INSTANCE.getStorageProviderImplementationInstance(context));
  }
}
