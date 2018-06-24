package UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainForm extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JPanel centerPanel;

    private JLabel songsLabel;
    private JList songList;
    private DefaultListModel songListModel;
    private JScrollPane scrollPaneSongs;

    private JLabel plLabel;
    private JList plList;
    private DefaultListModel plListModel;
    private JScrollPane scrollPanePl;

    public MainForm() {
        initUI();
    }

    private void initUI() {
        this.setTitle("MP3 Player");
        this.setLayout(new BorderLayout());

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        fileMenu.setName("Aplicação");
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        songListModel = new DefaultListModel();
        songsLabel = new JLabel("Músicas");
        songList = new JList(songListModel);
        //songList.addListSelectionListener(this);
        songList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        songList.setVisibleRowCount(10);
        scrollPaneSongs = new JScrollPane(songList);

        plListModel = new DefaultListModel();
        plLabel = new JLabel("Playlist");
        plList = new JList(plListModel);
        //plList.addListSelectionListener(this);
        plList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        plList.setVisibleRowCount(10);
        scrollPanePl = new JScrollPane(plList);


        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.VERTICAL;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        centerPanel.add(songsLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        centerPanel.add(plLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 5;
        centerPanel.add(scrollPaneSongs, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 5;
        centerPanel.add(scrollPanePl, c);

        this.add(centerPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void valueChanged(ListSelectionEvent e) {
        //TODO
    }


}
