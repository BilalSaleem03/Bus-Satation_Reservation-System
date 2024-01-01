import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusReservationGUI extends JFrame {
    private JTextField nameField, cnicField, phoneField, seatsField;
    private JComboBox<String> destinationComboBox, timeComboBox;
    private JTextArea resultArea;

    private String[] destinations = {"Faisalabad", "Multan", "Peshawar", "Lahore", "Sahiwal"};
    private String[] times = {"2:30am", "7:30am", "11:00am", "4:00pm", "7:00pm", "10:00pm"};

    private String[] seats = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                              "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    private String[] reservedSeats = new String[20];

    private int nextReservation = 0;
    private int res = 0;

    public BusReservationGUI() {
        super("Bus Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("CNIC #:"));
        cnicField = new JTextField();
        inputPanel.add(cnicField);

        inputPanel.add(new JLabel("Phone #:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Destination:"));
        destinationComboBox = new JComboBox<>(destinations);
        inputPanel.add(destinationComboBox);

        inputPanel.add(new JLabel("Time Slot:"));
        timeComboBox = new JComboBox<>(times);
        inputPanel.add(timeComboBox);

        inputPanel.add(new JLabel("Seats:"));
        seatsField = new JTextField();
        inputPanel.add(seatsField);

        JButton reserveButton = new JButton("Reserve Seats");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleReservation();
            }
        });

        inputPanel.add(reserveButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleReservation() {
        String name = nameField.getText();
        String cnic = cnicField.getText();
        String phone = phoneField.getText();
        String destination = (String) destinationComboBox.getSelectedItem();
        String time = (String) timeComboBox.getSelectedItem();
        String seatCount = seatsField.getText();

        if (name.isEmpty() || cnic.isEmpty() || phone.isEmpty() || seatCount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int seatCountInt;
        try {
            seatCountInt = Integer.parseInt(seatCount);
            if (seatCountInt <= 0 || seatCountInt > 20) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of seats. Please enter a number between 1 and 20.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        res = 0;
        reserveSeats(seatCountInt);
        displayReservation(name, cnic, phone, destination, time, seatCountInt);

        nextReservation++;
    }

    private void reserveSeats(int seatCount) {
        for (int i = 0; i < seatCount; i++) {
            boolean check = true;
            while (true) {
                String selectedSeat = (String) JOptionPane.showInputDialog(this,
                        "Select your seat:",
                        "Seat Selection",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        seats,
                        seats[0]);

                if (selectedSeat == null) {
                    // User canceled seat selection
                    return;
                }

                for (int j = 0; j < 20; j++) {
                    if (seats[j].equals(selectedSeat)) {
                        seats[j] = "B";
                        check = false;
                    }
                }

                if (check) {
                    JOptionPane.showMessageDialog(this, "Seat not available! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    reservedSeats[res] = selectedSeat;
                    res++;
                    break;
                }
            }
        }
    }

    private void displayReservation(String name, String cnic, String phone, String destination, String time, int seatCount) {
        resultArea.append("\nName: " + name +
                "\nCNIC #: " + cnic +
                "\nPhone #: " + phone +
                "\nReserved Seats: " + String.join(", ", reservedSeats) +
                "\nDestination: " + destination +
                "\nTime Slot: " + time +
                "\nReservation successful....\n\n\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BusReservationGUI();
            }
        });
    }
}
