Muhammad Ramadhan Aditya Putra (2602168772) Universitas Bina Nusantara - Mobile Application and Technology

House Selling App

The **House Selling App** is an Android application designed to provide users with the ability to search for and view property listings. The app connects to a backend API to retrieve search results and detailed information about specific properties. This project uses **Kotlin**, **MVVM architecture**, and **Dagger-Hilt** for dependency injection.

## Features

- **Search Listings**: Users can search for available property listings and view them in a list.
- **Property Details**: Each listing includes detailed information such as the property's price, address, and attributes (e.g., bedrooms, bathrooms, etc.).
- **Image Loading**: Property images are loaded using **Glide**.
- **MVVM Architecture**: The app follows the Model-View-ViewModel pattern to separate business logic from the UI.

## Tech Stack

- **Kotlin**: The primary programming language used for Android development.
- **MVVM (Model-View-ViewModel)**: Architectural pattern used for separation of concerns.
- **Dagger-Hilt**: Dependency injection framework.
- **Coroutines**: For asynchronous operations and API requests.
- **Glide**: For image loading and caching.
- **RecyclerView**: For efficient display of a large number of listings.
- **LiveData**: For observing and reacting to changes in the ViewModel.

## Project Structure

```bash
├── data/
│   ├── ApiService.kt           # Interface for making API requests
│   ├── ListingRepository.kt    # Repository that handles data fetching
│
├── ui/
│   ├── ListingAdapter.kt       # Adapter for displaying listings in a RecyclerView
│   ├── ListingViewModel.kt     # ViewModel for managing listing data
│   ├── MainActivity.kt         # Main activity of the app
│   ├── SearchResultsActivity.kt# Displays search results
│   ├── ListingDetailActivity.kt# Displays detailed property info
│
├── di/
│   ├── NetworkModule.kt        # Hilt module for providing network dependencies
│
└── utils/
    └── GlideUtils.kt           # Utility for image loading using Glide
```

## Key Components

### 1. **ListingAdapter.kt**

Handles displaying the list of property listings in a `RecyclerView`. It uses **Glide** to load images and sets up click listeners to navigate to the detailed view of a listing.

### 2. **ListingDetailActivity.kt**

Displays detailed information about a specific property, such as price, address, number of bedrooms, bathrooms, and square footage. Data is fetched from the `ListingViewModel`.

### 3. **ListingViewModel.kt**

The ViewModel for managing property listings and their details. It fetches data from the `ListingRepository`, which connects to the API. The ViewModel uses **LiveData** to observe data changes and update the UI accordingly.

### 4. **SearchResultsActivity.kt**

Displays the search results fetched from the API. This activity observes the `listings` LiveData in the `ListingViewModel` and updates the UI accordingly.

### 5. **MainActivity.kt**

The entry point of the application. It sets the content view and initializes necessary components.

## Dependencies

- **Glide**: [https://github.com/bumptech/glide](https://github.com/bumptech/glide)
- **Dagger-Hilt**: [https://dagger.dev/hilt](https://dagger.dev/hilt)
- **Coroutines**: [https://kotlinlang.org/docs/coroutines-overview.html](https://kotlinlang.org/docs/coroutines-overview.html)
