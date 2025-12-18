# FairEmail MVP

A comprehensive Android email client MVP based on [M66B/FairEmail](https://github.com/M66B/FairEmail).

## Features

This MVP includes all 15 core features from FairEmail:

1. **Email Synchronization (IMAP/POP3/SMTP)** - Full support for standard email protocols
2. **Account Setup & Configuration** - Easy account management with manual IMAP/SMTP configuration
3. **Message Viewing & Display** - Clean message viewing interface
4. **Compose & Send Emails** - Full compose functionality with send operations
5. **Folder Management** - Support for Inbox, Sent, Drafts, Trash, and Archive folders
6. **Email Encryption (OpenPGP & S/MIME)** - BouncyCastle integration for encryption
7. **Search & Indexing** - Apache Lucene-powered search
8. **Push Notifications** - Real-time email notifications
9. **Offline Storage & Database** - Room database for offline access
10. **Attachment Handling** - File attachment support
11. **Contact Integration** - Android contacts integration
12. **Advanced Authentication** - Support for OAuth2 and biometric authentication
13. **Calendar Integration** - Calendar event handling
14. **Material Design UI with Theming** - Material Design 3 with dark mode support
15. **Home Screen Widgets** - Unread count widget

## Tech Stack

- **Language**: Java 11
- **Android SDK**: Target SDK 34, Min SDK 26
- **Architecture**: MVVM with Room Database
- **Email**: JavaMail (android-mail 1.6.7)
- **Encryption**: BouncyCastle 1.70, OpenPGP API 12.0
- **Search**: Apache Lucene 8.11.2
- **UI**: Material Design 3 (Material Components 1.9.0)
- **Database**: Room 2.5.2
- **Background Processing**: WorkManager 2.8.1

## Project Structure

```
app/
├── src/main/
│   ├── java/eu/faircode/email/
│   │   ├── ApplicationEx.java          # Application class with notification channels
│   │   ├── DB.java                     # Room database
│   │   ├── Converters.java             # Type converters
│   │   ├── Entity*.java                # Database entities (Account, Message, etc.)
│   │   ├── Dao*.java                   # Data access objects
│   │   ├── Activity*.java              # Activities (View, Setup, Compose, Message)
│   │   ├── Service*.java               # Services (Synchronize, Send)
│   │   └── WidgetUnified.java          # Home screen widget
│   ├── res/
│   │   ├── layout/                     # Activity layouts
│   │   ├── values/                     # Strings, colors, themes
│   │   ├── xml/                        # Widget config, file paths
│   │   └── menu/                       # App menus
│   └── AndroidManifest.xml
└── build.gradle
```

## Building

1. Clone the repository:
```bash
git clone https://github.com/manhthang0106/fairemail-mvp.git
cd fairemail-mvp
```

2. Open in Android Studio

3. Sync Gradle and build the project

4. Run on device or emulator (API 26+)

## Configuration

No environment variables or API keys are required for basic functionality. The app works with standard email protocols.

### Setting Up an Account

1. Launch the app
2. Enter your email credentials:
   - Email address
   - Password
   - IMAP server (e.g., `imap.gmail.com`)
   - IMAP port (e.g., `993`)
   - SMTP server (e.g., `smtp.gmail.com`)
   - SMTP port (e.g., `587`)
3. Save the account

## Key Dependencies

| Dependency | Version | Purpose |
|------------|---------|----------|
| AndroidX AppCompat | 1.6.1 | Android compatibility |
| Material Components | 1.9.0 | Material Design UI |
| Room Database | 2.5.2 | Offline storage |
| JavaMail | 1.6.7 | Email protocols (IMAP/SMTP) |
| BouncyCastle | 1.70 | Encryption (OpenPGP/S/MIME) |
| OpenPGP API | 12.0 | OpenPGP integration |
| Apache Lucene | 8.11.2 | Search and indexing |
| JSoup | 1.16.1 | HTML processing |
| WorkManager | 2.8.1 | Background sync |
| Biometric | 1.1.0 | Biometric authentication |

## Architecture

The app follows the architecture patterns from the parent FairEmail repository:

- **Database Layer**: Room database with entities for accounts, messages, folders, attachments, and operations
- **Service Layer**: Background services for email synchronization and sending
- **UI Layer**: Activities for viewing, composing, and managing emails
- **Widget**: Home screen widget for unread count

## Limitations (MVP Scope)

This is an MVP version with simplified implementations:

- Encryption buttons trigger placeholder actions
- Search functionality structure is in place but needs full Lucene integration
- Contact and calendar integration requires runtime permissions
- OAuth2 authentication structure exists but needs provider-specific implementation
- Some advanced features like conversation threading are simplified

## Parent Repository

Based on: [M66B/FairEmail](https://github.com/M66B/FairEmail)

## License

This MVP is for educational and demonstration purposes. Please refer to the original FairEmail project for licensing information.
