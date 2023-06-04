package com.yazantarifi.radio.android;

import com.yazantarifi.radio.useCases.GetAccessTokenUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class ApplicationModule_GetGetAccessTokenUseCaseFactory implements Factory<GetAccessTokenUseCase> {
  @Override
  public GetAccessTokenUseCase get() {
    return getGetAccessTokenUseCase();
  }

  public static ApplicationModule_GetGetAccessTokenUseCaseFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetAccessTokenUseCase getGetAccessTokenUseCase() {
    return Preconditions.checkNotNullFromProvides(ApplicationModule.INSTANCE.getGetAccessTokenUseCase());
  }

  private static final class InstanceHolder {
    private static final ApplicationModule_GetGetAccessTokenUseCaseFactory INSTANCE = new ApplicationModule_GetGetAccessTokenUseCaseFactory();
  }
}
