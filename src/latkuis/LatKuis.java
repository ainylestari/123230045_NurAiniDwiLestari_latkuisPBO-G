import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LatKuis {
    private static final String FIXED_USERNAME = "123230045";
    private static final String FIXED_PASSWORD = "ifkelasg";
    private static Object formPanel;
    
    public static void main(String[] args) {
        showLoginScreen();
    }

    private static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        frame.add(lblUsername);
        frame.add(txtUsername);
        frame.add(lblPassword);
        frame.add(txtPassword);
        frame.add(new JLabel(""));
        frame.add(btnLogin);
        
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            if (username.equals(FIXED_USERNAME) && password.equals(FIXED_PASSWORD)) {
                JOptionPane.showMessageDialog(frame, "Login Berhasil!");
                frame.dispose();
                showLandingPage();
            } else {
                JOptionPane.showMessageDialog(frame, "Username atau Password Salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setVisible(true);
    }

    private static void showLandingPage() {
        JFrame frame = new JFrame("Input Data Mahasiswa");
        frame.setSize(500, 300);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setLayout(new GridLayout(7, 2, 5, 5));

        JTextField txtNama = new JTextField();
        JTextField txtNIM = new JTextField();
        JTextField txtTugas = new JTextField();
        JTextField txtKuis = new JTextField();
        
        JRadioButton rbTeori = new JRadioButton("Kelas Teori");
        JRadioButton rbPraktikum = new JRadioButton("Kelas Praktikum");
        ButtonGroup group = new ButtonGroup();
        group.add(rbTeori);
        group.add(rbPraktikum);

        String[] mataKuliah = {"PBO", "SCPK", "Algo Lanjut"};
        JComboBox<String> cbMataKuliah = new JComboBox<>(mataKuliah);
        
        JButton btnSubmit = new JButton("Submit");
        JButton btnLogout = new JButton("Logout");
        
        frame.add(new JLabel("Nama:"));
        frame.add(txtNama);
        frame.add(new JLabel("NIM:"));
        frame.add(txtNIM);
        frame.add(new JLabel("Nilai Tugas:"));
        frame.add(txtTugas);
        frame.add(new JLabel("Nilai Quis:"));
        frame.add(txtKuis);
        frame.add(new JLabel("Tipe Kelas:"));
        JPanel kelasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        kelasPanel.add(rbTeori);
        kelasPanel.add(rbPraktikum);
        frame.add(kelasPanel);
        frame.add(new JLabel("Mata Kuliah:"));
        frame.add(cbMataKuliah);
        frame.add(btnSubmit);
        frame.add(btnLogout); 
        
        btnSubmit.addActionListener(e -> {
            try {
                double nilaiTugas = Double.parseDouble(txtTugas.getText());
                double nilaiKuis = Double.parseDouble(txtKuis.getText());
                double totalNilai;
                if (rbTeori.isSelected()) {
                    totalNilai = (0.3 * nilaiTugas) + (0.7 * nilaiKuis);
                } else {
                    totalNilai = (0.7 * nilaiTugas) + (0.3 * nilaiKuis);
                }
                String hasil = totalNilai > 85 ? "PASS" : "NOT PASS";
                JOptionPane.showMessageDialog(frame, 
                        "Nama: " + txtNama.getText() + 
                        "\nNIM: " + txtNIM.getText() +
                        "\nTipe Kelas: " + (rbTeori.isSelected() ? "Teori" : "Praktikum") +
                        "\nMata Kuliah: " + cbMataKuliah.getSelectedItem() +
                        "\nHasil: " + hasil +
                        "\nTotal Nilai: " + totalNilai);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Masukkan nilai yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnLogout.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(frame, "Apakah Anda yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                frame.dispose();
                showLoginScreen();
            }
        });
        
        frame.setVisible(true);
    }
}
