package com.yazantarifi.radio.useCases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class GetCategoryPlaylistsUseCaseAlias_Factory implements Factory<GetCategoryPlaylistsUseCaseAlias> {
  @Override
  public GetCategoryPlaylistsUseCaseAlias get() {
    return newInstance();
  }

  public static GetCategoryPlaylistsUseCaseAlias_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GetCategoryPlaylistsUseCaseAlias newInstance() {
    return new GetCategoryPlaylistsUseCaseAlias();
  }

  private static final class InstanceHolder {
    private static final GetCategoryPlaylistsUseCaseAlias_Factory INSTANCE = new GetCategoryPlaylistsUseCaseAlias_Factory();
  }
}
