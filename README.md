GameNews Project
This project utilizes a public API to fetch video game news. The API provides up-to-date information on various game-related topics. To access the API, you need to subscribe and obtain the required API key and host information.

Getting Started
To get started with the GameNews project, follow the steps below:

Subscribe to the public API by visiting the following URL: GameNews API.
Once subscribed, you will receive the X-RapidAPI-Key and X-RapidAPI-Host values required for accessing the API.
Setup
To configure the project to use your subscription details, make the following changes in the Constants file:

kotlin
Copy code
const val BASE_URL = "https://videogames-news2.p.rapidapi.com"
const val API_KEY = "X-RapidAPI-Key: YOUR_API_KEY"
const val API_HOST = "X-RapidAPI-Host: YOUR_API_HOST"
const val TEXT_SECTION_TEST_TAG = "TEXT_SECTION_TEST_TAG"
const val TEXT_SECTION_TITLE_TEST_TAG = "TEXT_SECTION_TITLE_TEST_TAG"
const val TEXT_SECTION_DATE_TEST_TAG = "TEXT_SECTION_DATE_TEST_TAG"
const val TEXT_SECTION_DESCRIPTION_TEST_TAG = "TEXT_SECTION_DESCRIPTION_TEST_TAG"
const val TEXT_SECTION_LINK_TEST_TAG = "TEXT_SECTION_LINK_TEST_TAG"
Replace YOUR_API_KEY with your obtained X-RapidAPI-Key value, and YOUR_API_HOST with your obtained X-RapidAPI-Host value.

Additionally, there is a variable in the project that determines whether to make requests to the API or use an internal data source. Modify the following variable in the appropriate file to control this behavior:

kotlin
Copy code
private val _shouldSearchFromAPI = MutableStateFlow(true)
If _shouldSearchFromAPI is set to true, the project will make requests to the API. If set to false, it will use the internal data source, which is a list of GameNews.

Usage
The GameNews project retrieves video game news using the provided API. The news can be displayed in a user-friendly format or used for further processing within the application. The choice of using the API or the internal data source depends on the _shouldSearchFromAPI variable mentioned earlier.

Feel free to explore the project and adapt it to your specific needs. Enjoy gaming news!

Note: Make sure to respect the API provider's terms of service and any usage limits they may have in place.
