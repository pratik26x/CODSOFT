import java.io.*;
import java.util.*;

// Class to represent a Student
class Student 
{
    private String fullName;
    private int studentID;
    private String grade;

    // Constructor to initialize student attributes
    public Student(String fullName, int studentID, String grade) 
    {
        this.fullName = fullName;
        this.studentID = studentID;
        this.grade = grade;
    }

    // Getter methods for accessing student attributes
    public String getFullName() 
    {
        return fullName;
    }

    public int getStudentID() 
    {
        return studentID;
    }

    public String getGrade() 
    {
        return grade;
    }

    // Override toString method to display student information
    @Override
    public String toString() 
    {
        return "Name: " + fullName + ", Student ID: " + studentID + ", Grade: " + grade;
    }
}

// Class to manage the collection of students
class StudentManagementSystem 
{
    private List<Student> studentList = new ArrayList<>();
    private static final String DATA_FILE = "student_data.txt";

    // Method to add a student to the system
    public void addStudent(Student student) 
    {
        studentList.add(student);
    }

    // Method to remove a student by student ID
    public void removeStudent(int studentID) 
    {
        studentList.removeIf(student -> student.getStudentID() == studentID);
    }

    // Method to search for a student by student ID
    public Student searchStudent(int studentID) 
    {
        for (Student student : studentList) 
        {
            if (student.getStudentID() == studentID) 
            {
                return student;
            }
        }
        return null; // Return null if the student is not found
    }

    // Method to get a list of all students
    public List<Student> getAllStudents() 
    {
        return studentList;
    }

    // Method to save student data to a file
    public void saveDataToFile() 
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) 
        {
            oos.writeObject(studentList);
            System.out.println("Data saved to " + DATA_FILE);
        } 
        catch (IOException e) 
        {
            System.err.println("Saving data to file: " + e.getMessage());
        }
    }

    // Method to load student data from a file
    @SuppressWarnings("unchecked")
    public void loadDataFromFile() 
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) 
        {
            studentList = (List<Student>) ois.readObject();
            System.out.println("Data loaded from " + DATA_FILE);
        } 
        catch (IOException | ClassNotFoundException e) 
        {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }
}

// Main class for the Student Management Application
public class StudentManagementSystemApp 
{
    public static void main(String[] args) 
    {
        StudentManagementSystem system = new StudentManagementSystem();

        // Load existing student data from file (if any)
        system.loadDataFromFile();

        Scanner scanner = new Scanner(System.in);
        while (true) 
        {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter student's full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter student's ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student's grade: ");
                    String grade = scanner.nextLine();
                    Student student = new Student(fullName, studentID, grade);
                    system.addStudent(student);
                    break;
                case 2:
                    System.out.print("Enter student ID to remove: ");
                    int removeStudentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.removeStudent(removeStudentID);
                    break;
                case 3:
                    System.out.print("Enter student ID to search: ");
                    int searchStudentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Student foundStudent = system.searchStudent(searchStudentID);
                    if (foundStudent != null) 
                    {
                        System.out.println("Student Found:\n" + foundStudent);
                    } 
                    else 
                    {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    List<Student> allStudents = system.getAllStudents();
                    if (!allStudents.isEmpty()) 
                    {
                        System.out.println("All Students:");
                        for (Student s : allStudents) 
                        {
                            System.out.println(s);
                        }
                    } 
                    else 
                    {
                        System.out.println("No students found.");
                    }
                    break;
                case 5:
                    // Save data to file and exit
                    system.saveDataToFile();
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}