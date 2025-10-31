package com.educonnect;

import java.util.Scanner;
import java.util.Set;
import entity.User;
import repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
// ... (All other necessary imports from your project) ...
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import config.SecurityConfig;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "entity")
@Import(SecurityConfig.class)
@ComponentScan(basePackages = {"com.educonnect", "service", "controller"})
public class EduconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduconnectApplication.class, args);
	}

    // Existing initUsers bean is unchanged...
    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        // ... (User initialization logic) ...
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
            	User admin = new User(null, "admin", passwordEncoder.encode("adminpass"), Set.of("ADMIN", "STUDENT")); 
                userRepository.save(admin);
                System.out.println("--- Initial 'admin' user created. Username: admin / Password: adminpass ---");
            }
        };
    }

    // NEW BEAN: Scanner Input Runner with Switch Logic
    @Bean
    public CommandLineRunner menuRunner() {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            int choice = -1;

            System.out.println("\n------------------------------------------------------");
            System.out.println(" EduConnect Backend Console Demo (Server running on 808) ");
            System.out.println("------------------------------------------------------");

            // Loop until the user chooses to exit (0)
            while (choice != 0) {
                System.out.println("\nSelect Action Type:");
                System.out.println("1. ADMIN Action (Create Course)");
                System.out.println("2. STUDENT Action (Enroll in Course)");
                System.out.println("0. Exit Console Input");
                System.out.print("Enter your choice: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    // Handle non-integer input
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Consume the bad input
                    continue;
                }
                scanner.nextLine(); // Consume the remaining newline

                // --- SWITCH STATEMENT CONTROLS THE FLOW ---
                switch (choice) {
                    case 1:
                        handleAdminAction(scanner);
                        break;
                    case 2:
                        handleStudentAction(scanner);
                        break;
                    case 0:
                        System.out.println("Exiting console input demonstration. Server remains active.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 0.");
                }
            }
            // IMPORTANT: Closing the scanner here is generally safe since the app is running in the background.
            // scanner.close();
        };
    }

    // --- Helper Method to simulate Admin data creation ---
    private void handleAdminAction(Scanner scanner) {
        System.out.println("\n--- ADMIN: Create New Course ---");
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Course Credits (Integer): ");
        int credits = 0;
        if (scanner.hasNextInt()) {
             credits = scanner.nextInt();
             scanner.nextLine(); // Consume newline
        } else {
             System.out.println("Invalid credits input. Defaulting to 3.");
             credits = 3;
             scanner.nextLine();
        }
        
        System.out.println("\nData Prepared for Server (Admin Action):");
        System.out.printf("Action: POST /api/courses\nPayload: {title: '%s', credits: %d}\n", title, credits);
        System.out.println("--- Course data ready for persistence (via API call) ---");
    }

    // --- Helper Method to simulate Student data creation/action ---
    private void handleStudentAction(Scanner scanner) {
        System.out.println("\n--- STUDENT: Enroll in Course ---");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter Course ID to Enroll: ");
        String courseId = scanner.nextLine();
        
        System.out.println("\nData Prepared for Server (Student Action):");
        System.out.printf("Action: POST /api/enrollments\nPayload: {studentId: '%s', courseId: '%s'}\n", studentId, courseId);
        System.out.println("--- Enrollment data ready for transactional processing ---");
    }
}