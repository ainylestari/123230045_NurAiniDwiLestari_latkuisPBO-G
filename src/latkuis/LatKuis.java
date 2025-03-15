import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LatKuis {
    private static final String FIXED_USERNAME = "123220064";
    private static final String FIXED_PASSWORD = "ifkelasg";
    
    public static void main(String[] args) {
        showLoginScreen();
    }

    private static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
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
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        JTextField txtNama = new JTextField();
        JTextField txtNIM = new JTextField();
        JTextField txtTugas = new JTextField();
        JTextField txtKuis = new JTextField();
        
        JRadioButton rbTeori = new JRadioButton("Kelas Teori", true);
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
        frame.add(new JLabel("Nilai Kuis:"));
        frame.add(txtKuis);
        frame.add(rbTeori);
        frame.add(rbPraktikum);
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
                JOptionPane.showMessageDialog(frame, "Nama: " + txtNama.getText() + "\nNIM: " + txtNIM.getText() +
                        "\nMata Kuliah: " + cbMataKuliah.getSelectedItem() +
                        "\nTotal Nilai: " + totalNilai + "\nHasil: " + hasil);
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
