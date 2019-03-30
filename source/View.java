import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Panel;
import java.awt.List;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.io.File;

public class View extends JFrame {

    private JPanel contentPane;
    public File maps_folder = new File("maps");
    String[] ComboArray;

	public View() {
        String[] maps = maps_folder.list();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(new Color(69,75,83));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        
        JComboBox comboBox = new JComboBox();
        ComboArray = new String[maps.length];
        for( int i = 0; i < maps.length; i++ ){
            ComboArray[i] = maps[i].replaceAll(".txt", "");
        }
		comboBox.setModel(new DefaultComboBoxModel(ComboArray));
		comboBox.setName("");
		comboBox.setBounds(856, 409, 279, 49);
		contentPane.add(comboBox);        

		JButton btnNewButton = new JButton("Play");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Bauhaus 93", Font.PLAIN, 44));
        btnNewButton.setBounds(856, 505, 279, 59);
        btnNewButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
              Main.CreateGameWindow(comboBox.getSelectedItem().toString());
            } 
          } );
		contentPane.add(btnNewButton);
		
		JLabel lblMaps = new JLabel("maps");
		lblMaps.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		lblMaps.setBounds(943, 356, 103, 42);
		contentPane.add(lblMaps);
	}
}