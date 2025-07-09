# 🎞️ Videoclub-development

A movie rental management system for a fictional 1980s-inspired video club, specializing in slasher, horror, and classic films. The project provides a complete platform for managing movies, customers, events, and promotions — with both a desktop and web interface designed for immersive and nostalgic experiences.

Customers can explore the movie catalog, join waitlists, and mark favorites, while employees manage rentals, events, and apply special promotions. The system also integrates smart recommendations and clear AI-generated explanations to improve the user experience.

> 🎬 **Slogan**: *Now Showing: Your Next Favourite Movie*

---

## ✨ Features

- **🎥 Movie Catalog Management**  
  Admins can add, edit, and remove movies, including metadata like genre, subgenre, and production details.

- **👤 Customer Account Management**  
  Customers can register, change passwords, rent available movies, and join waitlists for out-of-stock films.

- **📅 Event & Promotion System**  
  Employees can create and manage in-store events (e.g. *May the 4th*, *Halloween Specials*) and apply rental promotions.

- **🔍 Search & Filter**  
  Browse films by genre, release year, popularity, or availability — with smart filtering and subgenre exploration.

- **💬 AI Integration**  
  Intelligent explanations about account status, rental conditions, or system rules — powered by an AI text generation API.

- **📧 Email Notifications**  
  Customers receive confirmation and waitlist updates through email (via external mailing API).

- **🌐 Dual Interface**  
  - **Desktop App** (JavaFX) for employee operations.  
  - **Web App** (HTML/CSS/JavaScript) for customer access.

---

## 🛠️ Technologies Used

- **Java (21/23)** for backend logic and business rules  
- **Spring Boot** for RESTful API development and user authentication  
- **JavaFX** for desktop GUI (employee-side)  
- **MySQL** for relational database management  
- **HTML, CSS, JavaScript** for frontend customer web interface  
- **Axios / Fetch** for client-server communication  
- **SendGrid / Mailjet API** for email integration  
- **OpenAI API or Gemini AI** for intelligent recommendations and explanations  
- **Figma** for UX/UI prototyping and design

---

## 📈 Current Status

This project is in **active development**.  
Currently completed:
- System use cases and class diagrams  
- Early prototypes of the interfaces  
- ER diagram and database design

**Next Steps:**
- Set up backend and connect to database  
- Build secure login system with Spring Security  
- Develop frontend web interface for customers  
- Integrate APIs for email and AI support  
- Finalize employee-side JavaFX functionality
