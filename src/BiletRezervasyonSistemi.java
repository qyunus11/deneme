import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BiletRezervasyonSistemi extends JFrame {

    private JComboBox<String> ulasimAraciComboBox;
    private JDateChooser tarihChooser;
    private JList<String> firmaList;
    private JButton biletAlButton;

    private Map<String, String[]> firmalar;

    public BiletRezervasyonSistemi() {
        // Set up the JFrame
        setTitle("Ulaşım Bilet Sistemi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Ulaşım Aracı Seçimi
        add(new JLabel("Ulaşım Aracı:"));
        ulasimAraciComboBox = new JComboBox<>(new String[]{"Havayolu", "Otobüs", "Tren"});
        add(ulasimAraciComboBox);

        // Tarih Seçimi
        add(new JLabel("Tarih:"));
        tarihChooser = new JDateChooser();
        add(tarihChooser);

        // Firma Listesi
        add(new JLabel("Firma:"));
        firmalar = new HashMap<>();
        firmalar.put("Havayolu", new String[]{"THY", "Pegasus", "AnadoluJet"});
        firmalar.put("Otobüs", new String[]{"Metro Turizm", "Ulusoy", "Varan"});
        firmalar.put("Tren", new String[]{"TCDD", "YHT", "Doğu Ekspresi"});

        firmaList = new JList<>();
        add(new JScrollPane(firmaList));

        // Bilet Al Butonu
        biletAlButton = new JButton("Bilet Al");
        biletAlButton.addActionListener(e -> showUserInfoPanel());
        add(biletAlButton);

        // Ulaşım Aracı Seçimine Göre Firma Listesini Güncelle
        ulasimAraciComboBox.addActionListener(e -> updateFirmaList());

        // İlk açılışta firma listesini güncelle
        updateFirmaList();

        setVisible(true);
    }

    private void updateFirmaList() {
        String selectedUlasimAraci = ulasimAraciComboBox.getSelectedItem().toString();
        String[] firmaArray = firmalar.get(selectedUlasimAraci);
        firmaList.setListData(firmaArray);
    }

    private void showUserInfoPanel() {
        // Create a new panel for user information
        JPanel userInfoPanel = new JPanel(new GridLayout(4, 2));
        userInfoPanel.add(new JLabel("Adı:"));
        JTextField adField = new JTextField();
        userInfoPanel.add(adField);

        userInfoPanel.add(new JLabel("Soyadı:"));
        JTextField soyadField = new JTextField();
        userInfoPanel.add(soyadField);

        userInfoPanel.add(new JLabel("Kimlik Numarası:"));
        JTextField kimlikField = new JTextField();
        userInfoPanel.add(kimlikField);

        userInfoPanel.add(new JLabel("Doğum Tarihi:"));
        JDateChooser dogumTarihiChooser = new JDateChooser();
        userInfoPanel.add(dogumTarihiChooser);

        // Show the user information panel
        int result = JOptionPane.showConfirmDialog(this, userInfoPanel, "Kullanıcı Bilgileri",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // User clicked OK, process the information
            String ad = adField.getText();
            String soyad = soyadField.getText();
            String kimlikNumarasi = kimlikField.getText();
            Date dogumTarihi = dogumTarihiChooser.getDate();

            // Perform further processing with the user information if needed
            // For now, just display the information
            displayUserInfo(ad, soyad, kimlikNumarasi, dogumTarihi);
        }
    }

    private void displayUserInfo(String ad, String soyad, String kimlikNumarasi, Date dogumTarihi) {
        String bilgi = "Kullanıcı Bilgileri\n" +
                "Adı: " + ad + "\n" +
                "Soyadı: " + soyad + "\n" +
                "Kimlik Numarası: " + kimlikNumarasi + "\n" +
                "Doğum Tarihi: " + new SimpleDateFormat("dd/MM/yyyy").format(dogumTarihi);

        JOptionPane.showMessageDialog(this, bilgi, "Kullanıcı Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BiletRezervasyonSistemi::new);
    }
}
