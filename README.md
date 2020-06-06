# NEWS HQ - Android App

News HQ has been made for you to comfortably find all your technology news. The app aggregates news 
from this [api](https://learnappmaking.com/ex/news/articles/Apple?secret=CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju).
source to users.

# Link to App Demo

Link to screen recording of app. Click [here](https://some_youtube_link) to watch

# How It Works

The app uses the MVVM pattern for code separation. At the data layer, Retrofit is used to fetch the 
news articles from this [API](https://learnappmaking.com/ex/news/articles/Apple?secret=CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju).
The data returned is deserialized with Jackson by using the wrapper classes found in News.kt

A repository layer stands in between the data layer and the view model layer. At the ViewModels,
the data is obtained from the repository which is prepared for the UI views as LiveData from which 
the UI views observe and enact changes to their view elements observing the data source.

The articles are passed to a RecyclerView which presents the articles. On the user tapping an 
article, the app sends the user to a detail page where the user gets more information. Images 
are loaded from article URLs using Picasso. 


