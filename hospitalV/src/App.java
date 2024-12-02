import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Hospital {
    String H_name;
    String location;
    int available_beds;
    float rating;
    String contact;
    String doctor_name;
    int price;

    public Hospital(String H_name, String location, int available_beds, float rating, String contact, String doctor_name, int price) {
        this.H_name = H_name;
        this.location = location;
        this.available_beds = available_beds;
        this.rating = rating;
        this.contact = contact;
        this.doctor_name = doctor_name;
        this.price = price;
    }
}

class Patient {
    String P_name;
    int P_id;
    String contact;
    int price;

    public Patient(String P_name, int P_id, String contact, int price) {
        this.P_name = P_name;
        this.P_id = P_id;
        this.contact = contact;
        this.price = price;
    }
}

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class App {

    private static List<User> users = new ArrayList<>();

    static {
        // Sample users (username: password)
        users.add(new User("user1", "pass1"));
        users.add(new User("user2", "pass2"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User sign-in
        if (!signIn(scanner)) {
            System.out.println("Sign-in failed. Exiting application.");
            return; // Exit if sign-in fails
        }

        // Sample data
        String[] patientNames = {"P1", "P2", "P3", "P4"};
        int[] patientIds = {2, 3, 4, 1};
        String[] patientContacts = {"234534XXX7", "234576XXX2", "857465XXX9", "567657XXX0"};
        int[] bookingCosts = {1000, 1200, 1100, 600};

        String[] hospitalNames = {"H1", "H2", "H4", "H3"};
        String[] locations = {"Bangalore", "Bangalore", "Mumbai", "Prayagraj"};
        int[] beds = {4, 5, 6, 9};
        float[] ratings = {5.2f, 4.1f, 3.4f, 5.9f};
        String[] hospitalContacts = {"657534XXX7", "298766XXX2", "324565XXX9", "343456XXX4"};
        String[] doctorNames = {"D1", "D4", "D3", "D2"};
        int[] prices = {100, 200, 100, 290};

        // Function Call
        hospitalManagement(patientNames, patientIds, patientContacts,
                bookingCosts, hospitalNames, locations,
                beds, ratings,
                hospitalContacts,
                doctorNames, prices);

        scanner.close();
    }

    private static boolean signIn(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                System.out.println("Sign-in successful!");
                return true; // Successful sign-in
            }
        }

        return false; // Failed sign-in
    }

    public static void printHospitalData(List<Hospital> hospitals) {
        System.out.println("PRINT hospitals DATA:");
        System.out.printf("%-15s %-15s %-15s %-10s %-15s %-15s %-10s%n",
                "HospitalName", "Location", "Beds_Available", "Rating",
                "Hospital_Contact", "Doctor_Name", "Price_Per_Bed");

        for (Hospital h : hospitals) {
            System.out.printf("%-15s %-15s %-15d %-10.1f %-15s %-15s %-10d%n",
                    h.H_name, h.location, h.available_beds,
                    h.rating, h.contact, h.doctor_name,
                    h.price);
        }
        System.out.println();
    }

    public static void printPatientData(List<Patient> patients, List<Hospital> hospitals) {
        System.out.println("PRINT patients DATA:");
        System.out.printf("%-15s %-10s %-15s %-20s %-10s%n",
                "Patient_Name", "Patient_Id",
                "Patient_Contact", "Alloted_Hospital",
                "Patient_Expenditure");

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            Hospital h = hospitals.get(i);
            System.out.printf("%-15s %-10d %-15s %-20s %-10d%n",
                    p.P_name, p.P_id,
                    p.contact, h.H_name,
                    p.price);
        }
        System.out.println();
    }

    public static void sortHospitalsByName(List<Hospital> hospitals) {
        Collections.sort(hospitals, Comparator.comparing(h -> h.H_name));
        System.out.println("SORT BY NAME:");
        printHospitalData(hospitals);
    }

    public static void sortHospitalsByRating(List<Hospital> hospitals) {
        Collections.sort(hospitals, Comparator.comparingDouble(h -> h.rating));
        System.out.println("SORT BY Rating:");
        printHospitalData(hospitals);
    }

    public static void sortHospitalsByAvailableBeds(List<Hospital> hospitals) {
        Collections.sort(hospitals, Comparator.comparingInt(h -> h.available_beds));
        System.out.println("SORT BY Available Beds:");
        printHospitalData(hospitals);
    }

    public static void sortHospitalsByPrice(List<Hospital> hospitals) {
        Collections.sort(hospitals, Comparator.comparingInt(h -> h.price));
        System.out.println("SORT BY Price Per Bed:");
        printHospitalData(hospitals);
    }

    public static void printHospitalsByCity(String city, List<Hospital> hospitals) {
        System.out.println("PRINT hospitals by City: " + city);

        System.out.printf("%-15s %-15s %-15s %-10s %-15s %-15s %-10s%n",
                "HospitalName", "Location",
                "Beds_Available", "Rating",
                "Hospital_Contact", "Doctor_Name",
                "Price_Per_Bed");

        for (Hospital h : hospitals) {
            if (h.location.equalsIgnoreCase(city)) {
                System.out.printf("%-15s %-15s %-15d %-10.1f %-15s %-15s %-10d%n",
                        h.H_name,
                        h.location,
                        h.available_beds,
                        h.rating,
                        h.contact,
                        h.doctor_name,
                        h.price);
            }
        }
        System.out.println();
    }

    public static void hospitalManagement(String[] patientNames,
                                          int[] patientIds,
                                          String[] patientContacts,
                                          int[] bookingCosts,
                                          String[] hospitalNames,
                                          String[] locations,
                                          int[] beds,
                                          float[] ratings,
                                          String[] hospitalContacts,
                                          String[] doctorNames,
                                          int[] prices) {

        List<Hospital> hospitals = new ArrayList<>();

        // Initialize hospital data
        for (int i = 0; i < hospitalNames.length; i++) {
            hospitals.add(new Hospital(hospitalNames[i], locations[i], beds[i], ratings[i], hospitalContacts[i], doctorNames[i], prices[i]));
        }

        List<Patient> patients = new ArrayList<>();

        // Initialize patient data
        for (int i = 0; i < patientNames.length; i++) {
            patients.add(new Patient(patientNames[i], patientIds[i], patientContacts[i], bookingCosts[i]));
        }

        System.out.println();

        // Call various operations
        printHospitalData(hospitals);
        printPatientData(patients, hospitals);

        sortHospitalsByName(hospitals);
        sortHospitalsByRating(hospitals);

        // Example: Print hospitals in Bangalore
        printHospitalsByCity("Bangalore", hospitals);

        sortHospitalsByAvailableBeds(hospitals);
        sortHospitalsByPrice(hospitals);
    }
}