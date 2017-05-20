Keeping database alive with widgets
---------------------

- Activity updates list of countries. 
- It indicates WidgetViewsFactory to update its list
- WidgetViewsFactory's country list is a dagger dependency which is a singleton so updates from Activity get it update automatically
- Learn Realm and Dagger2

![Activity form](/readme/activity.png)
![Widget](/readme/widget.png)