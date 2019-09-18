/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.snakegame_2019;

/**
 *
 * @author kelvin
 */
public class Body {
    int x;
    int y;
    public Body(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Body(){
        this.x = 0;
        this.y = 0;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Body){
            return ((Body)obj).x == this.x && ((Body)obj).y == this.y;
        }
        else
            return false;
    }
}
