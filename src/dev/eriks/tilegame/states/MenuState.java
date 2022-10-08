/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.states;

import dev.eriks.tilegame.Game;
import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.display.Display;
import dev.eriks.tilegame.entities.creatures.Player;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.sound.Music;
import dev.eriks.tilegame.sound.MusicManager;
import dev.eriks.tilegame.ui.ClickListener;
import dev.eriks.tilegame.ui.UIImage;
import dev.eriks.tilegame.ui.UIImageButton;
import dev.eriks.tilegame.ui.uiMan;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author eriks
 */
public class MenuState extends State {

    Container con;
    JFrame window;
    JPanel table, header, incMusicPanel, decMusicPanel, cancelPanel, soundPanel;
    JCheckBox show1, show2, show3, show4, show5, show6, show1a, show2a, show3a, show4a, show5a, show6a, show1b, show2b, show3b, show4b, show5b, show6b, cancel;
    JButton button1, button2, button3, button4;
    JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label0;
    JLabel toplabel;
    Canvas can;

    private uiMan uiMan;
    private Handler handler;
    private MusicManager mm;
    private Music m;

    public MenuState(Handler handler) throws IOException, FileNotFoundException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        super(handler);
        this.handler = handler;
        uiMan = new uiMan(handler);
        handler.getMouseManager().setuiMan(uiMan);
        //uiMan.addObject();

        uiMan.addObject(new UIImage(0, 0, 384, 256, Assets.titleScreen));

        mm = new MusicManager();
        mm.playMenu();

        uiMan.addObject(new UIImageButton(300 - 128 - 25, 328, 128, 128, Assets.btn_mainGame, new ClickListener() {
            @Override
            public void onClick() {
                mm.stopMusic();
                handler.getMouseManager().setuiMan(null);
                mainGameState gui = new mainGameState();

            }
        }));
        uiMan.addObject(new UIImageButton(300 - 25, 328, 128, 128, Assets.btn_customGame, new ClickListener() {
            @Override
            public void onClick() {

                handler.getMouseManager().setuiMan(null);

                customState gui = new customState();
            }

        }));

        uiMan.addObject(new UIImageButton(300 + 128 - 25, 328, 128, 128, Assets.btn_settings, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setuiMan(null);

                settingsState gui = new settingsState();

            }

        }));
        uiMan.addObject(new UIImageButton(300 + 128 * 2 - 25, 328, 128, 128, Assets.btn_quit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }

        }));

    }

    @Override
    public void tick() {
        uiMan.tick();
        /*
        handler.getMouseManager().setuiMan(null);
        State.setState(handler.getGame().gameState);
         */
    }

    @Override
    public void render(Graphics g) {
        uiMan.render(g);
    }

    public class mainGameState {

        ChoiceHandler choiceHandler = new ChoiceHandler();

        public mainGameState() {
            window = new JFrame("MainGame Difficulty Select");
            window.setSize(1000, 500);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBackground(Color.black);
            window.setVisible(true);
            window.setLayout(null);
            window.setResizable(false);

            con = window.getContentPane();
            table = new JPanel();
            table.setBounds(0, 0, 1000, 500);
            table.setBackground(Color.black);
            table.setLayout(new GridLayout(3, 1, 0, 0));

            con.add(table);

            button1 = new JButton("easy");
            button1.setBackground(Color.BLACK);
            button1.setForeground(Color.white);
            button1.setFocusPainted(false);
            table.add(button1);

            button2 = new JButton("hard");
            button2.setBackground(Color.BLACK);
            button2.setForeground(Color.white);
            button2.setFocusPainted(false);
            table.add(button2);

            button3 = new JButton("cancel");
            button3.setBackground(Color.getHSBColor(150, 600, 100));
            button3.setForeground(Color.BLACK);
            button3.setFocusPainted(false);
            table.add(button3);

            button1.setActionCommand("a1");
            button1.addActionListener(choiceHandler);
            button2.setActionCommand("a2");
            button2.addActionListener(choiceHandler);
            button3.setActionCommand("a3");
            button3.addActionListener(choiceHandler);
        }

        public class ChoiceHandler implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                String yourChoice = e.getActionCommand();

                if (yourChoice.equals("a1")) {
                    boolean difficulty = true;
                    //edit hp

                    State.setState(handler.getGame().gameState);
                    try {
                        mm.playMainHall();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    window.dispose();
                    //easy, change hp to higher.
                } else if (yourChoice.equals("a2")) {
                    boolean difficulty = false;
                    //edit hp
                    //Player.setHealth(1);
                    State.setState(handler.getGame().gameState);
                    window.dispose();
                    try {
                        mm.playMainHall();
                        //hard, change hp to lower.
                    } catch (IOException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (yourChoice.equals("a3")) {
                    window.dispose();
                    handler.getMouseManager().revertuiMan();
                }
            }
        }
    }

    public class settingsState {

        int sound = 50;
        int brightness = 50;
        ChoiceHandler choiceHandler = new ChoiceHandler();

        public settingsState() {
            window = new JFrame("Settings");
            window.setSize(1000, 500);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBackground(Color.black);
            window.setVisible(true);
            window.setLayout(null);
            window.setResizable(false);

            con = window.getContentPane();

            soundPanel = new JPanel();
            soundPanel.setBounds(450, 10, 50, 50);
            con.add(soundPanel);

            label1 = new JLabel("Sound");
            label1.setForeground(Color.BLACK);
            soundPanel.add(label1);

            incMusicPanel = new JPanel();
            incMusicPanel.setBounds(400, 70, 100, 100);
            incMusicPanel.setBackground(Color.red);
            incMusicPanel.setLayout(null);

            con.add(incMusicPanel);

            decMusicPanel = new JPanel();
            decMusicPanel.setBounds(600, 70, 100, 100);
            decMusicPanel.setBackground(Color.blue);
            decMusicPanel.setLayout(null);
            con.add(decMusicPanel);

            cancelPanel = new JPanel();
            cancelPanel.setBounds(400, 300, 100, 100);
            cancelPanel.setLayout(null);
            con.add(cancelPanel);

            button1 = new JButton("+");
            button1.setSize(100, 100);
            button1.setBackground(Color.green);
            button1.setForeground(Color.black);
            button1.setFocusPainted(false);

            //label1
            //table.add(label1);
            //button1.setBackground(Color.black);
            incMusicPanel.add(button1);

            button2 = new JButton("-");
            button2.setSize(100, 100);
            button2.setBackground(Color.green);
            button2.setForeground(Color.black);
            button2.setFocusPainted(false);

            decMusicPanel.add(button2);

            button3 = new JButton("Close");
            button3.setSize(100, 100);
            button3.setBackground(Color.blue);
            button3.setForeground(Color.black);
            button3.setFocusPainted(false);

            cancelPanel.add(button3);

            button1.setActionCommand("b1");
            button1.addActionListener(choiceHandler);

            button2.setActionCommand("b2");
            button2.addActionListener(choiceHandler);
            button3.setActionCommand("b3");
            button3.addActionListener(choiceHandler);

        }

        public class ChoiceHandler implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                String yourChoice = e.getActionCommand();

                if (yourChoice.equals("b1")) {
                    sound = sound + 5;
                    System.out.println("SOUND: " + sound);

                    //updateVolume
                } else if (yourChoice.equals("b2")) {
                    sound = sound - 5;
                    System.out.println("SOUND: " + sound);
                    //UpdateVolume
                } else if (yourChoice.equals("b3")) {
                    window.dispose();
                    handler.getMouseManager().revertuiMan();
                }
            }
        }
    }

    public class customState {

        //CustomGame
        boolean difficulty = false; //hard difficulty
        boolean monster1 = false; //stay in position
        boolean monster2 = false;
        boolean monster3 = false;
        boolean rItem = false; //dont't randomize items
        boolean rLayout = false; //don't randomize layout
        boolean rStart = false; //don't randomize Start

        public customState() {
            window = new JFrame("CustomGameSelector");
            window.setSize(1000, 500);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setBackground(Color.black);
            window.setVisible(true);
            window.setLayout(null);
            window.setResizable(false);

            con = window.getContentPane();
            table = new JPanel();
            table.setBounds(0, 0, 1000, 500);
            table.setBackground(Color.black);
            table.setLayout(new GridLayout(3, 6, 0, 0));
            con.add(table);

            label1 = new JLabel("Difficulty");
            label1.setBackground(Color.black);
            label1.setForeground(Color.white);
            table.add(label1);

            label2 = new JLabel("Enemy Randomizer");
            label2.setForeground(Color.white);
            table.add(label2);

            label3 = new JLabel("Item Randomizer");
            label3.setBackground(Color.gray);
            label3.setForeground(Color.white);
            table.add(label3);

            label4 = new JLabel("Layout Randomizer");
            label4.setBackground(Color.gray);
            label4.setForeground(Color.white);
            table.add(label4);

            label5 = new JLabel("Randomize Start Point");
            label5.setForeground(Color.white);
            label5.setBackground(Color.gray);
            table.add(label5);

            label6 = new JLabel("Generate Seed");
            label6.setBackground(Color.gray);
            label6.setForeground(Color.white);
            table.add(label6);

            label7 = new JLabel("");
            label7.setBackground(Color.gray);
            table.add(label7);

            show1 = new JCheckBox("Easy");
            show1.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show1);

            show2 = new JCheckBox("Monster 1");
            show2.setBackground(Color.gray);
            //show2.setBounds(0, 0, 0, 0);
            table.add(show2);

            show3 = new JCheckBox("Yes"); //Randomize Items Yes
            show3.setBackground(Color.gray);
            //show3.setBounds(0, 0, 0, 0);
            table.add(show3);

            show4 = new JCheckBox("Yes"); //Randomize Layout Yes
            show4.setBackground(Color.gray);
            //show4.setBounds(0, 0, 0, 0);
            table.add(show4);

            show5 = new JCheckBox("Yes"); //Randomize Start Yes
            show5.setBackground(Color.gray);
            //show4.setBounds(0, 0, 0, 0);
            table.add(show5);

            show6 = new JCheckBox("Generate"); //Generate Seed Yes
            show6.setBackground(Color.gray);
            //show4.setBounds(0, 0, 0, 0);
            table.add(show6);

            //where start button Should be written
            cancel = new JCheckBox("Cancel");
            cancel.setBackground(Color.gray);
            table.add(cancel);

            show1a = new JCheckBox("Hard");
            show1a.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show1a);

            show2a = new JCheckBox("Monster 2");
            show2a.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show2a);

            show3a = new JCheckBox("No");
            show3a.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show3a);

            show4a = new JCheckBox("No");
            show4a.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show4a);

            show5a = new JCheckBox("No");
            show5a.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show5a);

            show6a = new JCheckBox("Start Game"); //Appears when seed is generated
            show6a.setBackground(Color.white);
            show6a.setVisible(false);

            //show1.setBounds(0, 0, 100, 100);
            table.add(show6a);

            show2b = new JCheckBox("Monster 3");
            show2b.setBackground(Color.gray);
            //show1.setBounds(0, 0, 100, 100);
            table.add(show2b);
            event e = new event();
            show1.addItemListener(e);
            show2.addItemListener(e);
            show3.addItemListener(e);
            show4.addItemListener(e);
            show5.addItemListener(e);
            show6.addItemListener(e);
            cancel.addItemListener(e);
            show1a.addItemListener(e);
            show2a.addItemListener(e);
            show3a.addItemListener(e);
            show4a.addItemListener(e);
            show5a.addItemListener(e);
            show6a.addItemListener(e);
            show2b.addItemListener(e);

        }

        //accessed when selecting main game
        public customState(File file) {

        }

        public class event implements ItemListener {
            File file;
            public void itemStateChanged(ItemEvent e) {
                if (show1.isSelected()) {
                    show1a.setSelected(false);
                    difficulty = true;
                }
                if (show1a.isSelected()) {
                    show1.setSelected(false);
                    difficulty = false;
                }

                if (show2.isSelected()) {
                    monster1 = true;
                } else if (!show2.isSelected()) {
                    monster1 = false;
                }

                if (show2a.isSelected()) {
                    monster2 = true;
                } else if (!show2a.isSelected()) {
                    monster2 = false;
                }

                if (show2b.isSelected()) {
                    monster3 = true;
                } else if (!show2b.isSelected()) {
                    monster3 = false;
                }

                if (show3.isSelected()) {
                    show3a.setSelected(false);
                    rItem = true;
                }

                if (show3a.isSelected()) {
                    show3.setSelected(false);
                    rItem = false;
                }

                if (show4.isSelected()) {
                    show4a.setSelected(false);
                    rLayout = true;
                }

                if (show4a.isSelected()) {
                    show4.setSelected(false);
                    rLayout = false;
                }
                if (show5.isSelected()) {
                    show5a.setSelected(false);
                    rStart = true;
                }

                if (show5a.isSelected()) {
                    show5.setSelected(false);
                    rStart = true;
                }

                if (show6.isSelected()) {
                    writeToFile(createTextFile());

                    show6a.setVisible(true);
                }
                if (cancel.isSelected()) {
                    window.dispose();
                    handler.getMouseManager().revertuiMan();
                }
                if (show6a.isSelected()) {
                    //read file,start game
                    CustomGameState cg = new CustomGameState(handler,difficulty,monster1,monster2,monster3
                    ,rItem,rLayout,rStart);
                    
                    State.setState(handler.getGame().gameState = new GameState(handler,cg));
                    window.dispose();
                }
            }

            public File createTextFile() {
                String fileName = "Seed.txt";
                file = new File(fileName);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return file;
            }

            public void writeToFile(File file) {
                try {
                    FileWriter w = new FileWriter(file.getName());

                    w.write(difficulty + "\n"); //hard difficulty
                    w.write(monster1 + " " + monster2 + " " + monster3 + "\n");
                    w.write(rItem + "\n");
                    w.write(rLayout + "\n");
                    w.write(rStart + "\n");
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
