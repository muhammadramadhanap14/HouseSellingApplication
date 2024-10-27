### Muhammad Ramadhan Aditya Putra (2602168772)  
**Universitas Bina Nusantara** - *Mobile Application and Technology*

---

# House Selling App

The **House Selling App** is an Android application designed to enable users to search for and view property listings effortlessly. The app connects to a backend API, providing data for both search results and detailed information about specific properties. Built using **Kotlin**, **MVVM architecture**, and **Dagger-Hilt** for dependency injection, this application ensures a clean and maintainable code structure.

---

## Features

- **Search Listings**: Users can browse property listings based on their search criteria, presented in a scrollable list format.
- **Property Details**: Each listing offers a detailed view that displays essential information such as price, address, and property attributes (e.g., number of bedrooms and bathrooms).
- **Image Loading**: Images are efficiently loaded and cached using **Glide**.
- **MVVM Architecture**: The app adheres to the Model-View-ViewModel architecture to promote a clear separation of concerns and streamline UI updates.

---

## Tech Stack

- **Kotlin**: Language for Android development.
- **MVVM (Model-View-ViewModel)**: Architecture that maintains separation between the UI and business logic.
- **Dagger-Hilt**: Framework for dependency injection, making the app modular and testable.
- **Coroutines**: Utilized for asynchronous operations and network requests.
- **Glide**: Manages image loading and caching.
- **RecyclerView**: Efficiently displays property listings in a list.
- **LiveData**: Observes and reacts to data changes, enabling seamless UI updates.

---

## Project Structure

```plaintext
├── data/
│   ├── ApiService.kt           # Interface for API requests
│   ├── ListingRepository.kt     # Repository that interacts with the ApiService
│
├── models/
│   ├── Listing.kt               # Data class representing a property listing
│   ├── ListingDetail.kt         # Data class for detailed property information
│   ├── Address.kt               # Data class for property address information
│   ├── Attributes.kt            # Data class for property attributes
│   ├── AddressDetail.kt         # Data class for detailed address information
│   ├── MapCoordinates.kt        # Data class for geographic coordinates
│
├── ui/
│   ├── ListingAdapter.kt        # RecyclerView adapter for displaying listings
│   ├── MainActivity.kt          # Main entry point of the application
│   ├── SearchResultsActivity.kt  # Activity to display search results
│   ├── ListingDetailActivity.kt  # Activity to display detailed property info
│
├── di/
│   ├── NetworkModule.kt         # Hilt module for providing network dependencies
│
└── utils/
    └── GlideUtils.kt            # Utility for managing image loading with Glide
```

---

## Key Components

1. **ListingAdapter.kt**  
   Manages the display of property listings within a `RecyclerView`, utilizing **Glide** for image handling. Click listeners are set up to navigate users to the detailed view of a selected listing.

2. **ListingDetailActivity.kt**  
   Displays detailed property information, including price, address, and specific property features like bedrooms and bathrooms. Data is retrieved from the repository.

3. **SearchResultsActivity.kt**  
   Fetches and displays search results from the API, observing data to update the UI dynamically.

4. **MainActivity.kt**  
   Serves as the app’s entry point, setting up the initial content view and initializing essential components.

---

## Dependencies

- **Glide**: [GitHub Repository](https://github.com/bumptech/glide)
- **Dagger-Hilt**: [Dagger Documentation](https://dagger.dev/hilt)
- **Coroutines**: [Kotlin Coroutines Overview](https://kotlinlang.org/docs/coroutines-overview.html)
- **Retrofit**: [GitHub Repository](https://github.com/square/retrofit)
