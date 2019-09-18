/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snakegame_2019;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kelvin
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo da cobrinha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel jogo = new Panel();    
        frame.setSize(470, 490);
        frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(jogo);
    }
    
}
