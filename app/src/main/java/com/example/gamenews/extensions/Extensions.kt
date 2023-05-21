package com.example.gamenews.extensions

/**
 * Format date to date news.
 * This extension returns a string from the first letter until to letter on index 16
 * @receiver
 */
fun String.formatDateToDateNews() {
    this.substring(startIndex = 0, endIndex = 16)
}
