import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

abstract class User {
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void displayInfo();
}

class Admin extends User {
    public Transport transport;

    public Admin(String username, String password, Transport transport) {
        super(username, password);
        if (transport == null) {
            throw new IllegalArgumentException("Transport cannot be null.");
        }
        this.transport = transport;
    }

    @Override
    public void displayInfo() {
        System.out.println("Admin Information:");
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
    }

    // Şirket işlemleri
    public void displayCompanies() {
        List<Company> companies = transport.getCompanies();
        System.out.println("List of Companies:");
        for (Company company : companies) {
            System.out.println("Company Name: " + company.getCompanyName());
            // Diğer özellikleri ekleyebilirsiniz.
        }
    }

    public void addCompany(String newUsername, String newPassword, String newCompanyName) {
        Company newCompany = new Company(newUsername, newPassword, newCompanyName);
        transport.addCompany(newCompany);
        System.out.println("Company added: " + newCompany.getCompanyName() + " with username: " + newCompany.getUsername());
    }

    public void removeCompany(Company company) {
        transport.removeCompany(company);
        System.out.println("Company removed: " + company.getCompanyName());
    }

    // Sefer işlemleri


    public void addTrip(Trip trip) {
        transport.addTrip(trip);
        System.out.println("Trip added");
    }

    public void removeTrip(Trip trip) {
        transport.removeTrip(trip);
        System.out.println("Trip removed");
    }

    // Rezervasyon işlemler

    // Yolcu işlemleri
    public void addPassenger(Passenger passenger) {
        transport.addPassenger(passenger);
        System.out.println("Passenger added: " + passenger.getFullName());
    }

    public void removePassenger(Passenger passenger) {
        transport.removePassenger(passenger);
        System.out.println("Passenger removed: " + passenger.getFullName());
    }

    public void displayPassengers() {
        List<Passenger> passengers = transport.getPassengers();
        System.out.println("List of Passengers:");
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getFullName());
            // Diğer özellikleri ekleyebilirsiniz.
        }
    }

    // Personel işlemleri
    public void addPersonnel(Personnel personnel) {
        transport.addPersonnel(personnel);
        System.out.println("Personnel added: " + personnel.getFullName());
    }

    public void removePersonnel(Personnel personnel) {
        transport.removePersonnel(personnel);
        System.out.println("Personnel removed: " + personnel.getFullName());
    }

    public void displayPersonnel() {
        List<Personnel> personnelList = transport.getPersonnelList();
        System.out.println("List of Personnel:");
        for (Personnel personnel : personnelList) {
            System.out.println("Personnel Name: " + personnel.getFullName());
            // Diğer özellikleri ekleyebilirsiniz.
        }
    }

    // Güzergah işlemleri

    // Araç Tipi işlemleri
    public void addVehicleType(String vehicleType) {
        transport.addVehicleType(vehicleType);
        System.out.println("Vehicle type added: " + vehicleType);
    }

    public void removeVehicleType(String vehicleType) {
        transport.removeVehicleType(vehicleType);
        System.out.println("Vehicle type removed: " + vehicleType);
    }
}

class Company extends User {
    private String companyName;
    private List<Vehicle> vehicles;
    private List<Trip> trips;

    public Company(String username, String password, String companyName) {
        super(username, password);
        this.companyName = companyName;
        this.vehicles = new ArrayList<>(); // Liste başlangıçta oluşturuluyor
        this.trips = new ArrayList<>(); // trips listesini başlangıçta oluştur

    }
    public void addVehicle(Vehicle vehicle) {

        vehicles.add(vehicle);
    }
    public List<Trip> getTrips() {
        return new ArrayList<>(trips);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
    @Override
    public void displayInfo() {
        System.out.println("Company Information:");
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
        System.out.println("Company Name: " + companyName);
    }
    // Firma işlevlerini ekleyebilirsiniz.
}

abstract class Person {
    private String firstName;
    private String lastName;
    private String tcNumber;
    private Date birthDate;

    public Person(String firstName, String lastName, String tcNumber, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcNumber = tcNumber;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTcNumber() {
        return tcNumber;
    }

    public void setTcNumber(String tcNumber) {
        this.tcNumber = tcNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    // İhtiyaca göre diğer özel metotlar eklenebilir
}

class Personnel extends Person {
    private String position;
    private double salary;

    public Personnel(String firstName, String lastName, String tcNumber, Date birthDate, String position, double salary) {
        super(firstName, lastName, tcNumber, birthDate);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // İhtiyaca göre diğer gerekli getter ve setter metotları eklenebilir

    // İhtiyaca göre diğer özel metotlar eklenebilir
}

class Passenger extends Person {
    private String contactNumber;
    private String email;

    public Passenger(String firstName, String lastName, String tcNumber, Date birthDate, String contactNumber, String email) {
        super(firstName, lastName, tcNumber, birthDate);
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // İhtiyaca göre diğer gerekli getter ve setter metotları eklenebilir

    // İhtiyaca göre diğer özel metotlar eklenebilir
}

abstract class Vehicle {
    private int capacity;
    private int tripNumber;
    private String name;


    public Vehicle(String name ,int capacity, int tripNumber) {
        this.name = name;
        this.capacity = capacity;
        this.tripNumber = tripNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    // Soyut metotlar
    public abstract void displayInfo();

    public abstract double calculateFuelCost(double fuelPricePerLiter);
}

class Bus extends Vehicle {
    private List<Trip> trips;
    private String busType;

    public Bus(int capacity, String type, int tripNumber) {
        super("Bus", capacity, tripNumber); // Vehicle sınıfının kurucu metodunu çağır
        this.busType = type;
        this.trips = new ArrayList<>(); // trips listesini başlat
    }
    public void addTrip(Trip trip) {
        if (trips == null) {
            trips = new ArrayList<>();
        }
        trips.add(trip);
    }

    @Override
    public void displayInfo() {
        System.out.println("Bus Information:");
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Trip Number: " + getTripNumber());
    }

    @Override
    public double calculateFuelCost(double fuelPricePerLiter) {
        // Burada yakıt maliyeti hesaplaması yapılır
        // Örneğin: return distance * fuelConsumptionRate * fuelPricePerLiter;
        return 0.0; // Hesaplama mantığına göre değiştirilmeli
    }
}

class Train extends Vehicle {
    private List<Trip> trips;
    private String trainType;

    public Train(int capacity, String type, int tripNumber) {
        super("Train", capacity, tripNumber);
        this.trainType = type;
        this.trips = new ArrayList<>();
    }
    public void addTrip(Trip trip) {
        if (trips == null) {
            trips = new ArrayList<>();
        }
        trips.add(trip);
    }


    @Override
    public void displayInfo() {
        System.out.println("Train Information:");
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Trip Number: " + getTripNumber());
    }

    @Override
    public double calculateFuelCost(double fuelPricePerLiter) {
        // Burada yakıt maliyeti hesaplaması yapılır
        // Örneğin: return distance * fuelConsumptionRate * fuelPricePerLiter;
        return 0.0; // Hesaplama mantığına göre değiştirilmeli
    }
}

class Airplane extends Vehicle {
    private List<Trip> trips;
    private String airplaneType;

    public Airplane(int capacity, String type, int tripNumber) {
        super("Airplane", capacity, tripNumber);
        this.airplaneType = type;
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        // Airplane sınıfına özgü seyahat ekleme işlemleri burada yapılır.
        // Ardından seyahati ekleyebilirsiniz.
        if (trips == null) {
            trips = new ArrayList<>(); // trips liste başlatılmamışsa, yeni bir liste oluştur
        }
        trips.add(trip);

    }

    @Override
    public void displayInfo() {
        System.out.println("Airplane Information:");
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Trip Number: " + getTripNumber());
    }

    @Override
    public double calculateFuelCost(double fuelPricePerLiter) {
        // Burada yakıt maliyeti hesaplaması yapılır
        // Örneğin: return distance * fuelConsumptionRate * fuelPricePerLiter;
        return 0.0; // Hesaplama mantığına göre değiştirilmeli
    }
}

class Trip {
    private Vehicle vehicle;
    private String tripName;
    private Route route;
    private Date time;
    private double price;
    private int tripNumber; // Yeni eklenen özellik


    public Trip(Vehicle vehicle, Route route, Date time, double price,int tripNumber) {
        this.vehicle = vehicle;
        this.route = route;
        this.time = time;
        this.price = price;
        this.tripNumber = tripNumber;
    }
    public Trip(String tripName, int tripNumber, String destination) {
        this.tripName = tripName;
        this.tripNumber = tripNumber;
        this.route = new Route(destination);
    }
    public Trip(String tripName) {
        this.tripName = tripName;
    }
    public Trip(String tripName, int tripNumber) {
        this.tripName = tripName;
        this.tripNumber = tripNumber;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public Route getRoute() {
        return route;
    }
    public String getTripName() {
        return tripName;
    }
    public Date getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }
    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }
    public void reserveSeat(int seatNumber) {
        System.out.println("Seat " + seatNumber + " reserved for the trip.");
    }

    @Override
    public String toString() {
        return "Trip Name: " + tripName + ", Trip Number: " + tripNumber;
    }

    // Diğer gerekli getter ve setter metotları eklenebilir

}

class Route {
    private List<String> cities;
    private String departureLocation;
    private String destinationLocation;

    // Şehirlerin listesi ile oluşturulan constructor eklendi
    public Route(String destination) {
        this.destinationLocation = destination;
    }

    // Eski constructor, departman ve varış lokasyonlarından oluşuyor
    public Route(String departureLocation, String destinationLocation) {
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
    }

    // Diğer gerekli getter ve setter metotları eklenebilir

    public List<String> getCities() {
        return cities;
    }

    public String getDepartureLocation() {
        if (cities != null && !cities.isEmpty()) {
            return cities.get(0); // İlk şehir departure olarak kullanılıyor
        } else {
            return departureLocation;
        }
    }

    public String getDestinationLocation() {
        if (cities != null && cities.size() > 1) {
            return cities.get(cities.size() - 1); // Son şehir arrival olarak kullanılıyor
        } else {
            return destinationLocation;
        }
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }
}


class Reservation {
    private Passenger passenger;
    private Trip trip;
    private int seatNumber;

    public Reservation(Passenger passenger, Trip trip, int seatNumber) {
        this.passenger = passenger;
        this.trip = trip;
        this.seatNumber = seatNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void reserveSeat() {
        if (trip != null) {
            trip.reserveSeat(seatNumber);
        }
    }

    // Diğer gerekli getter ve setter metotları eklenebilir

    // İhtiyaca göre diğer özel metotlar eklenebilir
}

class Transport {
    private List<Company> companies;
    private List<Vehicle> vehicles;
    private List<Trip> trips;
    private List<Reservation> reservations;
    private List<Passenger> passengers;
    private List<Personnel> personnelList;
    private List<Route> routes;
    private List<String> vehicleTypes;

    public Transport() {
        this.companies = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.trips = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.personnelList = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.vehicleTypes = new ArrayList<>();
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public List<Personnel> getPersonnelList() {
        return personnelList;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<String> getVehicleTypes() {
        return vehicleTypes;
    }

    public void addCompany(Company company) {
        companies.add(company);
        System.out.println("Company added: " + company.getCompanyName() +
                " (Username: " + company.getUsername() + ", Password: " + company.getPassword() + ")");
    }

    public void removeCompany(Company company) {
        companies.remove(company);
        System.out.println("Company removed: " + company.getCompanyName());
    }

    public void addVehicleType(String vehicleType) {
        vehicleTypes.add(vehicleType);
        System.out.println("Vehicle type added: " + vehicleType);
    }

    public void removeVehicleType(String vehicleType) {
        vehicleTypes.remove(vehicleType);
        System.out.println("Vehicle type removed: " + vehicleType);
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
        System.out.println("Trip added");
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        System.out.println("Trip removed");
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Reservation made for " + reservation.getPassenger().getFullName());
    }



    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        System.out.println("Passenger added: " + passenger.getFullName());
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        System.out.println("Passenger removed: " + passenger.getFullName());
    }

    public void displayPassengers() {
        System.out.println("List of Passengers:");
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getFullName());
            // Diğer özellikleri ekleyebilirsiniz.
        }
    }

    public void addPersonnel(Personnel personnel) {
        personnelList.add(personnel);
        System.out.println("Personnel added: " + personnel.getFullName());
    }

    public void removePersonnel(Personnel personnel) {
        personnelList.remove(personnel);
        System.out.println("Personnel removed: " + personnel.getFullName());
    }

    public void displayPersonnel() {
        System.out.println("List of Personnel:");
        for (Personnel personnel : personnelList) {
            System.out.println("Personnel Name: " + personnel.getFullName());
            // Diğer özellikleri ekleyebilirsiniz.
        }
    }


}


public class Main extends JFrame {
    
    private Transport transport;
    private List<Company> companies;

    public Main() {
        transport = new Transport();
        initializeCompanies();
        initializeUI();
    }
    
    private void initializeCompanies() {

        // Şirket A oluşturuluyor
        Company companyA = new Company("A","1","A");
        Bus bus1A = new Bus(40, "Type1", 3);
        bus1A.addTrip(new Trip("3. Sefer", 3));

        companyA.addVehicle(bus1A);

        Bus bus2A = new Bus(30, "Type2", 3);
        bus2A.addTrip(new Trip("3. Sefer Karayolu", 3, "Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul (Gidis-Donus)"));
        companyA.addVehicle(bus2A);

        transport.addCompany(companyA);

        // Şirket B oluşturuluyor
        Company companyB = new Company("B", "1", "B");
        Bus bus1B = new Bus(30, "Type1", 3);
        bus1B.addTrip(new Trip("3. Sefer Demiryolu", 3, "Istanbul - Kocaeli - Bilecik - Eskisehir - Ankara - Eskisehir - Bilecik - Kocaeli - Istanbul (Gidis-Donus)"));
        companyB.addVehicle(bus1B);

        Bus bus2B = new Bus(40, "Type2", 4);
        bus2B.addTrip(new Trip("4. Sefer Demiryolu", 4, "Istanbul - Kocaeli - Bilecik - Eskisehir - Konya - Eskisehir - Bilecik - Kocaeli - Istanbul (Gidis-Donus)"));
        companyB.addVehicle(bus2B);
        transport.addCompany(companyB);


        // Şirket C oluşturuluyor
        Company companyC = new Company("C", "1", "C");
        Bus bus1C = new Bus(40, "Type1", 4);
        bus1C.addTrip(new Trip("5. Sefer Karayolu", 5, "Istanbul - Kocaeli - Ankara - Kocaeli - Istanbul (Gidis-Donus)"));
        companyC.addVehicle(bus1C);

        Airplane airplane1C = new Airplane(60, "Type1", 5);
        airplane1C.addTrip(new Trip("6. Sefer Havayolu", 6, "Istanbul - Konya - Istanbul (Gidis-Donus)"));
        companyC.addVehicle(airplane1C);

        Airplane airplane2C = new Airplane(60, "Type2", 5);
        airplane2C.addTrip(new Trip("7. Sefer Havayolu", 7, "Istanbul - Ankara - Istanbul (Gidis-Donus)"));
        companyC.addVehicle(airplane2C);
        transport.addCompany(companyC);

        // Şirket D oluşturuluyor
        Company companyD = new Company("D", "1", "D");
        Train train1D = new Train(50, "Type1", 1);
        train1D.addTrip(new Trip("1. Sefer Demiryolu", 1, "Istanbul - Kocaeli - Bilecik - Eskisehir - Ankara - Eskisehir - Bilecik - Kocaeli - Istanbul (Gidis-Donus)"));
        companyD.addVehicle(train1D);

        Train train2D = new Train(50, "Type2", 2);
        train2D.addTrip(new Trip("2. Sefer Demiryolu", 2, "Istanbul - Kocaeli - Bilecik - Eskisehir - Konya - Eskisehir - Bilecik - Kocaeli - Istanbul (Gidis-Donus)"));
        companyD.addVehicle(train2D);

        Train train3D = new Train(50, "Type3", 2);
        train3D.addTrip(new Trip("8. Sefer Demiryolu", 8, "Istanbul - Kocaeli - Eskişehir - Konya - Eskişehir - Kocaeli - Istanbul (Gidis-Donus)"));
        companyD.addVehicle(train3D);
        transport.addCompany(companyD);

        // Şirket E oluşturuluyor
        Company companyE = new Company("E", "1", "E");
        Airplane airplane1E = new Airplane(60, "Type1", 6);
        airplane1E.addTrip(new Trip("9. Sefer Havayolu", 9, "Istanbul - Konya - Istanbul (Gidis-Donus)"));
        companyE.addVehicle(airplane1E);

        Airplane airplane2E = new Airplane(60, "Type2", 6);
        airplane2E.addTrip(new Trip("10. Sefer Havayolu", 10, "Istanbul - Ankara - Istanbul (Gidis-Donus)"));
        companyE.addVehicle(airplane2E);
        transport.addCompany(companyE);
    }

    private void initializeUI() {
        setTitle("Transport Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton loginButton = new JButton("Login");
        JButton buyTicketButton = new JButton("Buy Ticket");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginDialog();
            }
        });

        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  new BiletRezervasyonSistemi();
            }
        });
        setLayout(new FlowLayout());
        add(loginButton);
        add(buyTicketButton);
    }

    private void showLoginDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        String[] userTypeOptions = {"Admin", "Company"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypeOptions);

        Object[] message = {
                "Kullanıcı Türü:", userTypeComboBox,
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION || option == JOptionPane.YES_OPTION) {
            String userType = (String) userTypeComboBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if ("Admin".equals(userType)) {
                // Admin kontrolü
                if ("admin".equals(username) && "1".equals(password)) {
                    openAdminPanel();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
                }
            } else if ("Company".equals(userType)) {
                // Firma kontrolü
                Company company = findCompanyByUsername(username);
                if (company != null && company.getPassword().equals(password)) {
                    openCompanyPanel(company);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
                }
            }
        }else {
            JOptionPane.showMessageDialog(this, "Login canceled. No changes made.");
        }
    }

    private void openAdminPanel() {
        class AdminPanel extends JFrame {
            private Transport transport;

            public AdminPanel(Transport transport) {
                this.transport = transport;
                initializeUI();
            }

            private void initializeUI() {
                setTitle("Admin Panel");
                setSize(600, 400);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JButton displayCompaniesButton = new JButton("Display Companies");
                JButton addCompanyButton = new JButton("Add Company");
                JButton removeCompanyButton = new JButton("Remove Company");

                displayCompaniesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayCompanies();
                    }
                });

                addCompanyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addCompanyInAdminPanel();
                    }
                });

                removeCompanyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeCompany();
                    }
                });

                setLayout(new FlowLayout());
                add(displayCompaniesButton);
                add(addCompanyButton);
                add(removeCompanyButton);
            }

            private void displayCompanies() {
                List<Company> companies = transport.getCompanies();
                StringBuilder message = new StringBuilder("List of Companies:\n");
                for (Company company : companies) {
                    message.append("Company Name: ").append(company.getCompanyName()).append("\n");
                    // Diğer özellikleri ekleyebilirsiniz.
                }
                JOptionPane.showMessageDialog(null, message.toString());
            }

            private void addCompany(String username, String password, String companyName) {
                Company company = new Company(username, password, companyName);
                transport.addCompany(company);
                JOptionPane.showMessageDialog(this, "Company added: " + companyName);
            }
            private void addCompanyInAdminPanel() {
                String username = JOptionPane.showInputDialog(this, "Enter Company Username:");
                String password = JOptionPane.showInputDialog(this, "Enter Company Password:");
                String companyName = JOptionPane.showInputDialog(this, "Enter Company Name:");

                if (username != null && password != null && companyName != null) {
                    addCompany(username, password, companyName);
                    showMessage("Company added: " + companyName);
                } else {
                    showMessage("Invalid company information. Please try again.");
                }
            }
            private void showMessage(String message) {
                JOptionPane.showMessageDialog(null, message);
            }

            private void removeCompany() {
                String companyName = JOptionPane.showInputDialog(this, "Enter Company Name to Remove:");
                if (companyName != null && !companyName.isEmpty()) {
                    Company companyToRemove = findCompanyByName(companyName);
                    if (companyToRemove != null) {
                        transport.removeCompany(companyToRemove);
                        JOptionPane.showMessageDialog(this, "Company removed: " + companyName);
                    } else {
                        JOptionPane.showMessageDialog(this, "Company not found. Please try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid company name. Please try again.");
                }
            }

            private Company findCompanyByName(String companyName) {
                return transport.getCompanies().stream()
                        .filter(company -> company.getCompanyName().equalsIgnoreCase(companyName))
                        .findFirst()
                        .orElse(null);
            }
        }

        AdminPanel adminPanel = new AdminPanel(transport);
        adminPanel.setVisible(true);
    }

    private Company findCompanyByUsername(String username) {
        for (Company company : transport.getCompanies()) {
            if (company.getUsername().equals(username)) {
                return company;
            }
        }
        return null;
    }

    private void openCompanyPanel(Company company) {
        class CompanyPanel extends JFrame {
            private Company company;
            private List<Vehicle> vehicles = new ArrayList<>();
            private List<Trip> trips;

            public CompanyPanel(Company company) {
                this.company = company;
                this.vehicles = company.getVehicles(); // Şirketin araçlarını al
                this.trips = company.getTrips();  // Sefer listesini al

                initializeUI();
            }

            private void initializeUI() {
                setTitle("Company Panel - " + company.getCompanyName());
                setSize(600, 400);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JButton displayVehiclesButton = new JButton("Display Vehicles");
                JButton addVehicleButton = new JButton("Add Vehicle");
                JButton removeVehicleButton = new JButton("Remove Vehicle");
                JButton addTripButton = new JButton("Add Trip");
                JButton removeTripButton = new JButton("Remove Trip");
                JButton calculateDailyProfitButton = new JButton("Calculate Daily Profit");

                displayVehiclesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayVehicles();
                    }
                });

                addVehicleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addVehicle();
                    }
                });

                removeVehicleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeVehicle();
                    }
                });
                addTripButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addTrip();
                    }
                });

                removeTripButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeTrip();
                    }
                });

                calculateDailyProfitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calculateDailyProfit();
                    }
                });

                setLayout(new FlowLayout());
                add(displayVehiclesButton);
                add(addVehicleButton);
                add(removeVehicleButton);
                add(addTripButton);
                add(removeTripButton);
                add(calculateDailyProfitButton);
            }

            private void calculateDailyProfit() {
                // Burada günlük kar hesaplama işlemleri yapılabilir
                // Yolcu ücretleri, hizmet bedeli, personel maliyeti ve yakıt maliyeti gibi faktörleri dikkate alarak kar hesaplanabilir.
            }
            private void addTrip() {
                String tripName = JOptionPane.showInputDialog(null, "Enter Trip Name:");
                if (tripName != null && !tripName.isEmpty()) {
                    Trip newTrip = new Trip(tripName);
                    company.addTrip(newTrip);
                    JOptionPane.showMessageDialog(null, "Trip added:\n" + newTrip);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid trip name. Please try again.");
                }
            }

            public void removeTrip() {
                if (company.getTrips().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No trips to remove.");
                    return;
                }

                String[] tripList = new String[company.getTrips().size()];
                for (int i = 0; i < company.getTrips().size(); i++) {
                    tripList[i] = company.getTrips().get(i).getTripName();
                }

                String selectedTrip = (String) JOptionPane.showInputDialog(
                        null,
                        "Select a trip to remove:",
                        "Remove Trip",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tripList,
                        tripList[0]
                );

                if (selectedTrip != null) {
                    Trip tripToRemove = findTripByName(selectedTrip, company.getTrips());
                    if (tripToRemove != null) {
                        company.removeTrip(tripToRemove);
                        JOptionPane.showMessageDialog(null, "Trip removed: " + selectedTrip);
                    } else {
                        JOptionPane.showMessageDialog(null, "Trip not found. Please try again.");
                    }
                }
            }
            private void displayTrips() {
                StringBuilder message = new StringBuilder("List of Trips:\n");

                if (trips.isEmpty()) {
                    message.append("No trips available.");
                } else {
                    for (Trip trip : trips) {
                        message.append("Trip Name: ").append(trip.getTripName()).append("\n");
                        // Diğer özellikleri ekleyebilirsiniz.
                    }
                }

                JOptionPane.showMessageDialog(null, message.toString());
            }

            private Trip findTripByName(String tripName, List<Trip> trips) {
                return trips.stream()
                        .filter(trip -> trip.getTripName().equalsIgnoreCase(tripName))
                        .findFirst()
                        .orElse(null);
            }
            public void displayVehicles() {
                List<Vehicle> companyVehicles = company.getVehicles(); // Şirketin araçlarını al
                StringBuilder message = new StringBuilder("List of Vehicles:\n");

                if (companyVehicles.isEmpty()) {
                    message.append("No vehicles available.");
                } else {
                    for (Vehicle vehicle : companyVehicles) {
                        message.append("Type: ").append(vehicle.getClass().getSimpleName()).append("\n");
                        // Diğer özellikleri ekleyebilirsiniz.
                    }
                }

                JOptionPane.showMessageDialog(null, message.toString());
            }

            public void addVehicle() {
                int capacity = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Capacity:"));
                int tripNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Trip Number:"));

                String[] vehicleTypes = {"Bus", "Train", "Airplane"};
                String vehicleType = (String) JOptionPane.showInputDialog(null, "Select Vehicle Type:", "Vehicle Type", JOptionPane.QUESTION_MESSAGE, null, vehicleTypes, vehicleTypes[0]);

                switch (vehicleType) {
                    case "Bus":
                        String busType = JOptionPane.showInputDialog(null, "Enter Bus Type:");
                        Bus bus = new Bus(capacity, busType, tripNumber);
                        vehicles.add(bus);
                        break;
                    case "Train":
                        String trainType = JOptionPane.showInputDialog(null, "Enter Train Type:");
                        Train train = new Train(capacity, trainType, tripNumber);
                        vehicles.add(train);
                        break;
                    case "Airplane":
                        String airplaneType = JOptionPane.showInputDialog(null, "Enter Airplane Type:");
                        Airplane airplane = new Airplane(capacity, airplaneType, tripNumber);
                        vehicles.add(airplane);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid vehicle type. Please try again.");
                        break;
                }

                JOptionPane.showMessageDialog(null, "Vehicle added:\nCapacity: " + capacity + "\nTrip Number: " + tripNumber);
            }

            public void removeVehicle() {
                List<Vehicle> companyVehicles = company.getVehicles(); // Şirketin araçlarını al

                if (companyVehicles.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No vehicles to remove.");
                    return;
                }

                String[] vehicleList = new String[companyVehicles.size()];
                for (int i = 0; i < companyVehicles.size(); i++) {
                    vehicleList[i] = companyVehicles.get(i).getClass().getSimpleName() + " - Trip Number: " + companyVehicles.get(i).getTripNumber();
                }

                String selectedVehicle = (String) JOptionPane.showInputDialog(null, "Select a vehicle to remove:", "Remove Vehicle", JOptionPane.QUESTION_MESSAGE, null, vehicleList, vehicleList[0]);

                if (selectedVehicle != null) {
                    // Remove the selected vehicle
                    for (Vehicle vehicle : new ArrayList<>(companyVehicles)) {
                        String vehicleInfo = vehicle.getClass().getSimpleName() + " - Trip Number: " + vehicle.getTripNumber();
                        if (vehicleInfo.equals(selectedVehicle)) {
                            company.removeVehicle(vehicle); // Şirketten aracı kaldır
                            JOptionPane.showMessageDialog(null, "Vehicle removed:\n" + selectedVehicle);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Selected vehicle not found.");
                }
            }
        }


        CompanyPanel companyPanel = new CompanyPanel(company);
        companyPanel.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}