# IT BookStore

## Modules
        
## :app

- /core - core **DI Graph** and common components of app: for **Data Layer**(API, CACHING), **Domain Layer**(base Repository interfaces and Models), base **Presentation Layer**, **Utilities**
- /features - features with their DATA, DOMAIN, PRESENTATION layers. For Presentation Layer there is used MVVM Architecture using Coroutines and Flows.
 Navigation happens with **Destinations**(see below)
- /main - launcher Activity and Main **Navigator**

## :buildSrc
- Whole app dependencies management with Kotlin DSL

## :navigation - Android Library
- Contains abstract Navigator class which has core functionality and also DefaultNavigator implementation for it. 
Using it you can navigate between Destinations. Every screen has its Destination. You can put arguments and safely get it from Activity/Fragment. 
There are default ActivityDestination and FragmentDestination for simplicity of using navigation. Module contains also its DI Graph.

## :paging - Java Library
- Contains abstract Pager<Key, PageModel> class that can 
get pages from PagingDataSource<Key, PageModel>, which can be changed dynamically(Strategy Pattern). 
Client should just implement class, set datasource and collect **'pageListFlow'** which is StateFlow of Pagemodel's list.
The module also contains tests for Pager.
    
## Used Technologies
- Clean architecture (MVVM for Presentation Layer)
- Dependency Injection with Hilt
- Coroutines with Flow, StateFlow
- Repository Pattern
- Retrofit with its Interceptors and custom Logger
- SQLDelight
- Kotlin DSL
- ViewBinding
- Coil for loading images
- MockK for Tests
- JUnit4
