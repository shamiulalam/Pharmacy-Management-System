# Pharmacy Management System

A small desktop pharmacy application written in Java. It uses Swing for its graphical interface and local text files for administrator credentials, customer accounts, and medicine inventory. The app demonstrates basic Java classes, event-driven GUI programming, file input/output, and inventory and billing workflows.

> This is an educational/demo project. It is not suitable for real pharmacy operations or real customer data in its current form. See [Security and limitations](#security-and-limitations).

## What this project does

This project is a desktop pharmacy management and checkout application. A customer can create an account, log in, select medicines and quantities, review a purchase, and generate a text bill. An administrator can log in separately to add or remove medicines and change stock quantities or prices. The app saves customer accounts, administrator credentials, and inventory in local text files; when a sale is completed, it writes a bill and updates the inventory file.

## Features

- Customer registration and login
- Administrator login
- Medicine inventory management for administrators:
  - Add a medicine with a name, stock quantity, and rate
  - Delete a medicine
  - Update a medicine's stock quantity or rate
- Customer purchase workflow:
  - Select medicines and quantities
  - Review and remove items from the current bill
  - Check requested quantities against listed stock
  - Generate a text bill containing customer, date, item, and total information
  - Deduct purchased quantities from inventory
- Starter medicine inventory in `medlist.txt`

## Technologies

- **Language:** Java (JDK 8 or later)
- **GUI:** Java Swing and AWT
- **Persistence:** tab-separated plain text files
- **External libraries:** none
- **Build tool:** none; compile with `javac` or open the project in IntelliJ IDEA

## Application flow

1. `Main` starts the Swing application on the Event Dispatch Thread.
2. `LoadingPanel` displays a short progress screen, then opens `DashBoard`.
3. The dashboard lets a user register or log in.
4. `Person` handles registration; `Login` checks the local user records and administrator credentials.
5. Successful administrator login opens `AdminPanel` to maintain inventory.
6. Successful customer login opens `Store` to build a purchase and generate a bill.

## Project structure

```text
.
├── src/
│   ├── Main.java                 # Application entry point
│   ├── LoadingPanel.java         # Startup progress screen
│   ├── DashBoard.java            # Sign-up and login choices
│   ├── Signup.java               # Opens the customer registration form
│   ├── Login.java                # Login flow and account selection
│   ├── Person.java               # Customer registration and customer model
│   ├── AdminCredentials.java     # Reads local administrator credentials
│   ├── AdminPanel.java           # Administrator inventory controls
│   ├── Store.java                # Customer shopping and billing screen
│   ├── Medicine.java             # Medicine data model
│   ├── OtherMethods.java         # Password confirmation helper
│   └── RoundedCornerButton.java  # Custom Swing button painting
├── medlist.txt                   # Starter medicine data (tab-separated)
├── AdminCredentials.example.txt  # Safe template; copy and customize locally
├── Pharmacy.iml                  # IntelliJ IDEA module configuration
├── .gitignore                    # Excludes local/private/generated files
└── README.md
```

Runtime files such as `AdminCredentials.txt` and `records.txt` files are created or used in the project working directory. They are not included in the repository.
## Run the application

### Requirements

- Install a JDK (JDK 8 or later) and make sure `java` and `javac` are available on your `PATH`.
- Use a desktop environment that can display Swing windows.

### macOS / Linux

From the repository root:

```sh
cp AdminCredentials.example.txt AdminCredentials.txt
# Edit AdminCredentials.txt: replace both placeholders and keep the tab separator.
mkdir -p out
javac -d out src/*.java
java -cp out Main
```

### Windows PowerShell

From the repository root:

```powershell
Copy-Item AdminCredentials.example.txt AdminCredentials.txt
# Edit AdminCredentials.txt: replace both placeholders and keep the tab separator.
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem src\*.java | ForEach-Object { $_.FullName })
java -cp out Main
```

If `AdminCredentials.txt` already exists, do not copy over it unless you intend to replace its contents. The example file contains placeholders and is not a ready-to-use account. The application creates `records.txt` when needed. Run the commands from the project root because the application reads and writes its data files using relative paths.

To open in IntelliJ IDEA, open `Pharmacy.iml` or the project directory, ensure `src` is marked as the source folder, then run `Main`.

## Local data formats

The files use tab-separated fields, one record per line:

- **`AdminCredentials.txt`:** `username<TAB>password`
- **`records.txt`:** `firstName<TAB>lastName<TAB>username<TAB>address<TAB>contact<TAB>password<TAB>gender`
- **`medlist.txt`:** `medicineName<TAB>quantity<TAB>rate`

Keep each field free of tab characters. The application expects the files and fields in these formats.

## Design and code organization

There is no formal MVC, layered architecture, or other complete design pattern implemented. The project is a small, event-driven Swing application organized mostly by screen and responsibility:

- GUI classes build their forms and attach action listeners directly to buttons.
- `Medicine` is a simple domain/data class used by inventory and checkout screens.
- `AdminCredentials` and `OtherMethods` provide small helper responsibilities.
- Each screen directly reads or writes the text files it needs; there is no database, repository/DAO layer, or separate service layer.
- `RoundedCornerButton` extends `JButton` and overrides painting to provide a custom appearance.

This structure is straightforward for a learning project, though separating UI, business logic, and storage would make a larger application easier to maintain.

## Security and limitations

- Passwords for both administrators and customers are currently stored as **plain text** in local files. Password hashing and safer storage have not been implemented. Use only disposable demo credentials and data.
- `AdminCredentials.txt` and `records.txt` contain sensitive information and are excluded by `.gitignore`. Never commit them. The example credentials file contains placeholders only.
- `medlist.txt` is sample inventory and is included so the app has medicines to display. Remove or replace it if its contents should not be public.
- The app uses local files, so data is tied to the working directory and is not shared between installations.
- Input validation and error handling are basic. Invalid numeric input can interrupt inventory actions, and the app does not provide production-grade stock, identity, or billing controls.
- The checkout stock check evaluates each bill row independently. Repeated entries for the same medicine may therefore exceed the total stock even when each row is individually within stock.
- Bills include customer contact/address details and are written as plain text. Keep generated bills private.
