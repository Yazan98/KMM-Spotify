package com.yazantarifi.radio.android;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApplicationModule_GetHttpClientFactory implements Factory<HttpClient> {
  @Override
  public HttpClient get() {
    return getHttpClient();
  }

  public static ApplicationModule_GetHttpClientFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpClient getHttpClient() {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.INSTANCE.getHttpClient());
  }

  private static final class InstanceHolder {
    private static final ApplicationModule_GetHttpClientFactory INSTANCE = new ApplicationModule_GetHttpClientFactory();
  }
}
