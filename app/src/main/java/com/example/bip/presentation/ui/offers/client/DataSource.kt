package com.example.bip.presentation.ui.offers.client

import com.example.bip.R

/**
 * @author v.nasibullin
 */
object PeopleDataProvider {

    val listOfSpotifyHomeLanes = listOf(
        "Continue listening",
        "Popular Playlists",
        "Top Charts",
        "Recommended for today",
        "Bollywood",
        "Acoustic only"
    )

    val album = People(
        id = 0,
        artist = "Adele",
        song = "Someone like you",
        descriptions = "Album by Adele-2016",
        imageId = R.drawable.img
    )

    val albums = mutableListOf(
        People(
            id = 1,
            artist = "Ed Sheeran",
            song = "Perfect",
            descriptions = "Album by Ed Sheeran-2016",
            imageId = R.drawable.img_1,
            genre = "Pop"
        ),
        People(
            id = 2,
            artist = "Camelia Cabello",
            song = "Havana",
            descriptions = "Album by Camelia Cabello-2016",
            imageId = R.drawable.img_2,
            genre = "R&B"
        ),
        People(
            id = 3,
            artist = "BlackPink",
            song = "Kill this love",
            descriptions = "Album by BlackPink-2016",
            imageId = R.drawable.img,
            genre = "K-pop"
        ),
    )
}
