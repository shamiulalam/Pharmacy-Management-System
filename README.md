# Pharmacy Management System

A Java Swing desktop application for a small pharmacy. It uses only the Java standard library; no external dependencies are required.

## Requirements

- JDK 8 or later
- Windows, macOS, or Linux with a graphical desktop (Swing)

## Run from a terminal

Run these commands from the project root:

```sh
mkdir -p out
javac -d out src/*.java
java -cp out Main
```

On Windows PowerShell, the equivalent compile command is:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem src\*.java | ForEach-Object { $_.FullName })
java -cp out Main
```

## First run

1. If `AdminCredentials.txt` does not already exist, copy `AdminCredentials.example.txt` to `AdminCredentials.txt`.
2. Replace the placeholder username and password with your own values. Keep one tab between the username and password on each line. Do not commit `AdminCredentials.txt`.
3. Run the application. New customer registrations are saved to `records.txt` automatically. The starter medicine inventory is in `medlist.txt`.

`AdminCredentials.txt` and `records.txt` are local runtime data and are excluded from Git. Keep backups somewhere private. Do not put real customer or account information in a public repository.

## Security note

This is a learning/demo application. It currently stores admin and customer passwords as plain text in local files and does not hash them. Do not use real credentials or personal data until password hashing and safer data storage are implemented. The example credentials file contains placeholders only.

