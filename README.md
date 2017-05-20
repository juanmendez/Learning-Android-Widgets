Using Dagger and Realm with Widgets
---------------------

- Clone realmModels as your widgets are not running in main thread, and that causes issues
- Dagger2 or any other DI can be used to have widgetProviders and widgetViewFactories connect with realm
    - Dagger2 is annoying when it comes to describe errors
- Widgets can be fun to work with yet I guess I need more desing skills as this demo widget is not that good looking.