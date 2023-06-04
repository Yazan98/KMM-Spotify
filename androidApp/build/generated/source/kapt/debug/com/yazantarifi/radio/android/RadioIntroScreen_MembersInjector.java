package com.yazantarifi.radio.android;

import com.yazantarifi.kmm.sopy.base.context.SopifyStorageProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RadioIntroScreen_MembersInjector implements MembersInjector<RadioIntroScreen> {
  private final Provider<SopifyStorageProvider> storageProvider;

  public RadioIntroScreen_MembersInjector(Provider<SopifyStorageProvider> storageProvider) {
    this.storageProvider = storageProvider;
  }

  public static MembersInjector<RadioIntroScreen> create(
      Provider<SopifyStorageProvider> storageProvider) {
    return new RadioIntroScreen_MembersInjector(storageProvider);
  }

  @Override
  public void injectMembers(RadioIntroScreen instance) {
    injectStorage(instance, storageProvider.get());
  }

  @InjectedFieldSignature("com.yazantarifi.radio.android.RadioIntroScreen.storage")
  public static void injectStorage(RadioIntroScreen instance, SopifyStorageProvider storage) {
    instance.storage = storage;
  }
}
