Widgets with listViews updated by MainActivity
---------------------

- Initially the list of strings was part of Application
- Dagger2 was used to include the list as a dependency which is then accesible from Activity and WidgetFactory.
- MainActivity only calls `appWidgetManager.notifyAppWidgetViewDataChanged( widgetIds, R.id.listView )` and that refreshes each list instead of recreating the layout.

