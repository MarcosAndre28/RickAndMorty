package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.db.AppDatabase
import com.example.rickandmorty.db.dao.CharacterDao
import com.example.rickandmorty.db.remote.CharacterRemoteDataSource
import com.example.rickandmorty.db.remote.CharacterService
import com.example.rickandmorty.db.repository.CharacterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) =
        CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: CharacterRemoteDataSource,
        localDataSource: CharacterDao
    ) =
        CharacterRepository(remoteDataSource, localDataSource)

}

/*@Module anota o objeto para indicar que obteremos nossas dependências daqui. Usaremos apenas um neste aplicativo simples,
 mas projetos de maior escala geralmente têm muitos módulos.

 @Singleton forçará que apenas uma instância da dependência seja criada e usada em todo o aplicativo.

 @Provides indica que a próxima função fornecerá uma dependência.*/
