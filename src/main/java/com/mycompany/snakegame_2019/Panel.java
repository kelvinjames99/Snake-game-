/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snakegame_2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import static javafx.application.Platform.exit;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author kelvin
 */
public class Panel extends JPanel implements ActionListener {
    Timer timer;
    Random random = new Random();
    int xComida;
    int yComida;
    Boolean vivo = true;
    Boolean indicaFim = true;
    private final int TAM = 15;
    public int WIDTH = 450;
    public int HEIGHT = 450;
    boolean up, right, left, down;
    ArrayList <Body> snake = new ArrayList(); 
    public Panel(){
        xComida = WIDTH;
        yComida = random.nextInt((int)Math.floor((HEIGHT - 50) / 15)) * 15;
        addKeyListener(new LeitorDoTeclado());
        setFocusable(true);
        inicia();
        timer = new Timer(65, this);
        timer.start();
    }
    public void inicia(){
        snake.clear();
        right = true;
        left = false;
        up = false;
        down = false;
        snake.add(new Body(30, 0));
        snake.add(new Body(15, 0));
        snake.add(new Body(0, 0));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillRect(xComida, yComida, TAM, TAM);
        g.setColor(Color.green);
        for(int i = 0; i < snake.size(); i++){
            g.fillRect(snake.get(i).x, snake.get(i).y, TAM, TAM);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    public void Comida(){
        if(snake.get(0).x == xComida && snake.get(0).y == yComida){
            xComida = random.nextInt((int)Math.floor(WIDTH / 15)) * 15;
            yComida = random.nextInt((int)Math.floor(HEIGHT / 15)) * 15;
            snake.add(new Body());
        }
    }
    public void verificaCantos(){
        if(snake.get(0).x > WIDTH){
            snake.get(0).x = 0;
        }
        else if(snake.get(0).x < 0){
            snake.get(0).x = WIDTH;
        }
        if(snake.get(0).y > HEIGHT){
            snake.get(0).y = 0;
        }
        else if(snake.get(0).y < 0){
            snake.get(0).y = HEIGHT;
        }
    }
    public void colisão(){
        for(int i = 1; i < snake.size(); i++){
            if(snake.get(0).equals(snake.get(i))){
                System.out.println("Morreu!");
                vivo = false;
            }
        }
    }
    public void movimento(){
        if(vivo){
            for(int i = snake.size() - 1; i > 0; i--){
                snake.get(i).x = snake.get(i - 1).x;
                snake.get(i).y = snake.get(i - 1).y;
            }
            if(left){
                snake.get(0).x -= TAM; 
            }
            else if(right){
                snake.get(0).x += TAM;
            }
            else if(up){
                snake.get(0).y -= TAM;
            }
            else if(down){
                snake.get(0).y += TAM;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(vivo){
            colisão();
            verificaCantos();
            Comida();
            movimento();
            repaint();
            indicaFim = true;
        }
        else{
            System.out.println("Aperte enter para continuar");
        }
    }
    public class LeitorDoTeclado extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode() == KeyEvent.VK_LEFT && !right && indicaFim){
                indicaFim = false;
                left = true;
                up = false;
                down = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !left && indicaFim){
                indicaFim = false;
                right = true;
                up = false;
                down = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP && !down && indicaFim){
                indicaFim = false;
                up = true;
                right = false;
                left = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN && !up && indicaFim){
                indicaFim = false;
                down = true;
                left = false;
                right = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER && indicaFim){
                indicaFim = false;
                vivo = true;
                inicia();
            }
        }
    }
}
