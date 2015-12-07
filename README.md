# android-location-polling

<p>Example demo project to demonstrate how to track user's coordinates and send them to your server via RESTful API.
<p> Project to demonstrate how I usually schedule user location updates in the app. It is not the best approach or the most correct approach: just an example. Current demo uses `AlarmManager` to start polling service with associated worker [HandlerThread](http://developer.android.com/reference/android/os/HandlerThread.html) each 2 minutes. If you want to update your backend more frequently, consider using `Handler`'s `postDelayed` method (instead of `AlarmManager` with `setRepeating`) to increase battery life.
<p>**Note:** App uses [WakefulBroadcastReceiver](http://developer.android.com/reference/android/support/v4/content/WakefulBroadcastReceiver.html).
<p>For sending location data to server I use Retrofit 2.0 library and simple `DTO` object. For dependency injection I use Dagger 2.0.
