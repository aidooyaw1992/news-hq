# NEWS HQ - Android App

News HQ - An android app which finds and aggregates tech news to users. The app uses this
[API](https://learnappmaking.com/ex/news/articles/Apple?secret=CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju)
source for fetching data.

# Link to App Demo

A screen recording of the app is hosted on youtube. Click [here](https://youtu.be/mi_MQoyYdz0) to watch

# How It Works

The app uses the MVVM pattern for code separation. At the data layer, Retrofit is used to fetch the 
news articles from this [API](https://learnappmaking.com/ex/news/articles/Apple?secret=CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju).
The data returned is deserialized with Jackson by using the wrapper classes found in News.kt

A repository layer sits in between the data layer and the view model layer. At the ViewModels,
the data is obtained from the repository which is prepared for the UI views as LiveData from which 
the UI views observe and enact changes to their view elements observing the data source.

The articles are passed to a RecyclerView which presents the articles. On the user tapping an 
article, the app sends the user to a detail page where the user gets more information. Images 
are loaded from article URLs using Picasso. Network availability is handled by classes in netutils 
package on app startup

A user can bookmark an article from the detail page which will be stored in Room database for caching.
The cache data is only presented to the user when offline.
