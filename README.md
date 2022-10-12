# RickAndMortyApi
# Android Architecture: Hilt, MVVM, Kotlin Coroutines, Live Data, Room and Retrofit ( Api Rick and Morty)

Bom esse e meu primeiro aplicativo usando injeção de dependência(Dependency Injection), vou passar uma breve introdução sobre oque e injeção de dependência e Hilt!

## Injeção de dependência (DI) 
<div align="justify">
 É uma técnica amplamente utilizada em programação e bem adequada ao desenvolvimento Android, onde as dependências são fornecidas a uma classe em vez de criá-las. 
Ao seguir os princípios de DI, você estabelece as bases para uma boa arquitetura de aplicativo, maior reutilização de código e facilidade de teste. Você já tentou
injeção manual de dependência em seu aplicativo? Mesmo com muitas das bibliotecas de injeção de dependência existentes hoje, requer muito código clichê à medida que 
seu projeto se torna maior, já que você precisa construir cada classe e suas dependências manualmente e criar contêineres para reutilizar e gerenciar dependências.
</div>

## Hilt 
<div align="justify">
 A biblioteca Hilt define uma maneira padrão de fazer DI em seu aplicativo, fornecendo contêineres para cada classe Android em seu projeto e gerenciando 
seus ciclos de vida automaticamente para você. 
O Hilt foi desenvolvido com base na popular biblioteca DI Dagger , portanto, se beneficia da correção do tempo de compilação, do desempenho do tempo de execução,
da escalabilidade e do suporte ao Android Studio que o Dagger oferece. Devido a isso, o Dagger teve uma grande adoção em 30% dos 10 mil principais aplicativos da 
Google Play Store. No entanto, devido à geração de código em tempo de compilação, espere um aumento no tempo de compilação.
Como muitas classes de estrutura do Android são instanciadas pelo próprio sistema operacional, há um padrão associado ao usar o Dagger em aplicativos Android. 
Ao contrário do Dagger, o Hilt é integrado às bibliotecas do Jetpack e às classes de estrutura do Android e remove a maior parte desse clichê para permitir que
você se concentre apenas nas partes importantes da definição e injeção de associações sem se preocupar em gerenciar toda a configuração e fiação do Dagger. 
Ele gera e fornece automaticamente:<br></p>

* Componentes para integrar classes de estrutura do Android com o Dagger que você precisaria criar manualmente.
* Anotações de escopo para os componentes que o Hilt gera automaticamente.
* Ligações e qualificadores predefinidos.<br></p>

 O melhor de tudo, como Dagger e Hilt podem coexistir juntos, os aplicativos podem ser migrados conforme a necessidade .
</div>

## Hilt em ação
<div align="justify">
  Apenas para mostrar como é fácil usar o Hilt, vamos realizar algumas DI rápidas em um aplicativo Android típico.
  Vamos fazer Hilt injetar um CharacterRepository nosso arquivo CharacterDetailViewModel.<br></p>
  
  * Primeiro passo devemos habilitar o Hilt no aplicativo anotando sua classe de aplicativo com o @HiltAndroidApp acionar a geração de código do Hilt:
 ```
 @HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
 ``` 
* Segundo, diga ao Hilt como fornecer instâncias de CharacterRepository seu construtor com @Inject:
 ``` 
  class CharacterRepository @Inject constructor() {}
 ```
 
No terceiro, para injetar uma instância de CharacterRepository CharacterDetailViewModel, para o Hilt na ViewModel com 
 a @HiltViewModel e execute a injeção de campo usando a @Inject:
  ``` 
  @HiltViewModel
  class CharactersViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel() {}
   ``` 
</div>

## Suporte a Jetpack!
<div align="justify">
 
  Você pode usar suas bibliotecas favoritas do Jetpack com o Hilt pronto para uso. Fornecemos suporte de injeção direta para ViewModel, WorkManager, 
  Navigation e Compose até agora.<br></p>
  
  Por exemplo, para injetar um ViewModel de componentes de arquitetura:<br></p>
  CharactersViewModel em um MainActivityActivity: para fazer anotação no ViewModel usamos o @HiltViewModel, o construtor com @Inject usá-lo em uma atividade ou fragmento usamos o
  @AndroidEntryPoint:
  
 ``` 
  @HiltViewModel
  class CharactersViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel() {}
  
   
   @AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
 ``` 
 Saiba mais sobre o suporte do Jetpack na [Documentação](https://developer.android.com/training/dependency-injection/hilt-jetpack)
   
</div>
