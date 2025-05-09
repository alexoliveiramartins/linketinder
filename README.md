![GitHub repo size](https://img.shields.io/github/repo-size/alexoliveiramartins/linketinder)


# Linketinder

Linketinder is a platform that bridges the gap between companies and job applicants. Designed to streamline the recruitment process, Linketinder enables organizations to post job listings and engage directly with prospective candidates, while job seekers can create detailed profiles, upload resumes, and apply for positions with ease.

---

## Overview

Linketinder is a full-stack web application that facilitates meaningful connections between companies and job seekers. Whether you're a company looking for the perfect candidate or a job applicant aiming to find your next opportunity, Linketinder provides a user-friendly interface to make those connections seamless and efficient.

---

## Technologies

- **Frontend**: Typescript, CSS, HTML
- **Backend**: Groovy
- **Database**: PostgreSQL

---

## Pre-requisites

Java: v11.0.26 or above
Groovy: v3.0.22 or above
Gradle: v8.13 or above
psql (PostgreSQL): v16.8 or above 

---

## Features

- **Profile Management**: Tailored dashboards for companies and job seekers to manage their profiles and job listings.
- **Job Posting & Application**: Companies can easily post job vacancies, and candidates can connect with comapanies through the platform.

---

## Getting Started

Follow these steps to set up your local development environment:

### Prerequisites

Java, Groovy, Node.js, Gradle, npm

### Installation

1. **Clone the Repository**

```bash
git clone https://github.com/alexoliveiramartins/linketinder.git
cd linketinder
```

2. **Install Dependencies**

   The project is split into backend and frontend components. Install dependencies for each:

   **Backend:**
   ```bash
   cd backend/
   ./gradlew build
   ```

   **Frontend:**

   ```bash
   cd frontend/
   npm install
   ```

### Running the Application

1. **Start the Backend Application**

```bash
cd backend/
gradle run
```
or
```bash
cd backend/src/
groovy main.groovy.Main.groovy
```

2. **Start the Frontend Application**

   ```bash
   cd frontend-linketinder/
   npm run dev
   ```

Your application should now be running locally at:
- **Frontend:** [http://localhost:5173](http://localhost:3000)
- **Backend API:** [http://localhost:8080](http://localhost:5000)

---

## Project Structure


### Database

![BD_MODELAGEM](https://github.com/user-attachments/assets/2f17d9f5-42ba-48f1-aaef-ca4d5e9a1ea0)

> made with [dbdiagram](dbidiagram.io)

---

## License

