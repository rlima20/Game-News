<!DOCTYPE html>
<html>
<head>
</head>
<body>
  <h1>GameNews Project</h1>
  <p>
    This project utilizes a public API to fetch video game news. The API provides up-to-date information on various game-related topics. To access the API, you need to subscribe and obtain the required API key and host information.
  </p>
  <h2>Getting Started</h2>
  <ol>
    <li>Subscribe to the public API by visiting the following URL: <a href="https://rapidapi.com/danielilieprojects-G7QdvK7X5Ao/api/videogames-news2">GameNews API</a>.</li>
    <li>Once subscribed, you will receive the X-RapidAPI-Key and X-RapidAPI-Host values required for accessing the API.</li>
  </ol>
  <h2>Setup</h2>
  <p>
    To configure the project to use your subscription details, make the following changes in the <code>Constants</code> file:
  </p>
  <pre>
const val BASE_URL = "https://videogames-news2.p.rapidapi.com"
const val API_KEY = "X-RapidAPI-Key: YOUR_API_KEY"
const val API_HOST = "X-RapidAPI-Host: YOUR_API_HOST"
const val TEXT_SECTION_TEST_TAG = "TEXT_SECTION_TEST_TAG"
const val TEXT_SECTION_TITLE_TEST_TAG = "TEXT_SECTION_TITLE_TEST_TAG"
const val TEXT_SECTION_DATE_TEST_TAG = "TEXT_SECTION_DATE_TEST_TAG"
const val TEXT_SECTION_DESCRIPTION_TEST_TAG = "TEXT_SECTION_DESCRIPTION_TEST_TAG"
const val TEXT_SECTION_LINK_TEST_TAG = "TEXT_SECTION_LINK_TEST_TAG"
  </pre>
  <p>
    Replace <code>YOUR_API_KEY</code> with your obtained X-RapidAPI-Key value, and <code>YOUR_API_HOST</code> with your obtained X-RapidAPI-Host value.
  </p>
  <p>
    Additionally, there is a variable in the project that determines whether to make requests to the API or use an internal data source. Modify the following variable in the appropriate file to control this behavior:
  </p>
  <pre>
private val _shouldSearchFromAPI = MutableStateFlow(true)
  </pre>
  <p>
    If <code>_shouldSearchFromAPI</code> is set to <code>true</code>, the project will make requests to the API. If set to <code>false</code>, it will use the internal data source, which is a list of GameNews.
  </p>
  <h2>Usage</h2>
  <p>
    The GameNews project retrieves video game news using the provided API. The news can be displayed in a user-friendly format or used for further processing within the application. The choice of using the API or the internal data source depends on the <code>_shouldSearchFromAPI</code> variable mentioned earlier.
  </p>
  <p>
    Feel free to explore the project and adapt it to your specific needs. Enjoy Game News!
  </p>
  <p>
    <strong>Note</strong>: Make sure to respect the API provider's terms of service and any usage limits they may have in place.
  </p>
</body>
</html>
