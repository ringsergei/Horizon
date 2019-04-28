import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class View extends JFrame {

	private JPanel contentPane;
	public JButton[] btns = new JButton[30];
	public int disposeY, disposeX;
	public File maps_folder = new File("maps");
	public File music_folder = new File("music");
	public Sound audio = new Sound();
	ArrayList<String> musicList = new ArrayList<String>();

	public void btnClick(JButton btn){
		Main.CreateGameWindow(btn.getText());
	}

	public View() {
		audio.playSound("mainTheme", "Hardware");
		String[] maps = maps_folder.list();
		String[] music = music_folder.list();
		for( String track : music ){
			musicList.add(track.replaceAll(".wav", ""));
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(35,35,35));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("LetterOMatic!", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setBounds(21, 2, 141, 24);
		
		JPanel panel = new JPanel();
		panel.setBounds(448, 244, 346, 632);
		panel.setBackground(new Color(41,44,43));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSlider slider = new JSlider(-80, 6, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
			  int value = slider.getValue();
			  audio.control.setValue((float)value);
			}
		});
		slider.setBackground(new Color(41,44,43));
		slider.setBounds(265, 25, 200, 23);
		slider.setForeground(Color.WHITE);
		
		JLabel lblMusic = new JLabel("Music");
		lblMusic.setForeground(Color.WHITE);
		lblMusic.setFont(new Font("LetterOMatic!", Font.PLAIN, 20));
		lblMusic.setBounds(26, 25, 77, 23);

		JLabel lblMusicList = new JLabel("MusicList");
		lblMusicList.setForeground(Color.WHITE);
		lblMusicList.setFont(new Font("LetterOMatic!", Font.PLAIN, 20));
		lblMusicList.setBounds(26, 100, 200, 23);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener (new ActionListener () {
				public void actionPerformed(ActionEvent e) {
						audio.clip.close();
						audio.playSound("mainTheme", "" + comboBox.getSelectedItem());
				}
		});
		comboBox.setModel(new DefaultComboBoxModel(musicList.toArray()));
		comboBox.setBounds(270, 100, 200, 20);
        
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(834, 244, 696, 624);
		contentPane.add(scrollPane);

		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBackground(new Color(41,44,43));
		panel_1.setPreferredSize(new Dimension(696, 620));
		panel_1.setLayout(null);
 
		JButton btnNewButton = new JButton("play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.removeAll();
				panel_1.setPreferredSize(new Dimension(696, 10000));
				disposeX=0;
				disposeY=0;
				for( int i = 0; i<maps.length; i++ ) {
					btns[i] = new JButton();
					btns[i].setBorder(new LineBorder(Color.WHITE, 2, true));
					btns[i].setFont(new Font("LetterOMatic!", Font.PLAIN, 30));
					btns[i].setBackground(new Color(41,44,43));
					btns[i].setBounds(15+disposeX, 27+disposeY, 151, 119);
					btns[i].setPreferredSize(new Dimension(151, 119));
					btns[i].setLabel(maps[i].replaceAll(".txt", ""));
					btns[i].setForeground(Color.WHITE);
					btns[i].setFont(new Font("LetterOMatic!", Font.PLAIN, 20));
					btns[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btnClick((JButton) e.getSource());
						}
					});
					disposeX+=170;
					if( (i+1)%4==0 ) {
						disposeY+=130;
						disposeX=0;
					}
					
					panel_1.add(btns[i]);
				}
				scrollPane.revalidate();
				
				panel_1.repaint();		     	        
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(41,44,43));
		btnNewButton.setFont(new Font("LetterOMatic!", Font.PLAIN, 30));
		btnNewButton.setBounds(10, 26, 326, 50);
		btnNewButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		panel.add(btnNewButton);
		
		JButton btnSettings = new JButton("settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.setPreferredSize(new Dimension(696, 620));
				panel_1.add(slider);
				panel_1.add(lblMusic);
				panel_1.add(lblMusicList);
				panel_1.add(comboBox);
				panel_1.repaint();
				scrollPane.revalidate();
			}
		});
		btnSettings.setForeground(Color.WHITE);
		btnSettings.setFont(new Font("LetterOMatic!", Font.PLAIN, 30));
		btnSettings.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnSettings.setBackground(new Color(41, 44, 43));
		btnSettings.setBounds(10, 100, 326, 50);
		panel.add(btnSettings);
		
		JButton btnDevelopers = new JButton("developers");
		btnDevelopers.setForeground(Color.WHITE);
		btnDevelopers.setFont(new Font("LetterOMatic!", Font.PLAIN, 30));
		btnDevelopers.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnDevelopers.setBackground(new Color(41, 44, 43));
		btnDevelopers.setBounds(10, 550, 326, 50);
		panel.add(btnDevelopers);
		
		JLabel lblAlpha = new JLabel("alpha 1.0");
		lblAlpha.setForeground(Color.LIGHT_GRAY);
		lblAlpha.setBackground(Color.LIGHT_GRAY);
		lblAlpha.setFont(new Font("LetterOMatic!", Font.PLAIN, 15));
		lblAlpha.setBounds(10, 11, 105, 18);
		contentPane.add(lblAlpha);
		
	}
}
