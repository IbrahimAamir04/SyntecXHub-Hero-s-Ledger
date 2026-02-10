# Hero's Ledger

A personal finance management application with a tactical, Metal Gear-inspired interface. Track your income and expenses with the precision of a seasoned operative.

## Features

*   **Secure User Authentication:** Firebase-powered email and password authentication to keep your financial data secure.
*   **Tactical Dashboard:** An at-a-glance overview of your financial status, including:
    *   Total Income
    *   Total Expenses
    *   Current Balance
    *   An interactive pie chart providing a visual breakdown of your expenditures. The center of the chart displays your total income and remaining balance for a quick status update.
*   **Transaction Management:**
    *   Easily add new income or expense transactions with a title, amount, and optional description.
    *   View a comprehensive history of all your financial activities in a clear, list-based format.
*   **Custom Theming:** A unique user interface inspired by the iconic Metal Gear Solid series, featuring a tactical color palette and design.

## Tech Stack & Libraries

*   **Kotlin:** The official, modern programming language for Android development.
*   **Android Jetpack:**
    *   **Fragments & Navigation Component:** For building a single-activity app with a modular UI.
    *   **ViewModel & LiveData:** For creating a robust and lifecycle-aware UI layer.
    *   **Room:** For persistent local data storage of all transactions.
*   **Hilt:** For dependency injection to manage components and dependencies.
*   **Firebase Authentication:** For handling user sign-up and login.
*   **MPAndroidChart:** For creating the interactive and informative pie chart on the dashboard.
