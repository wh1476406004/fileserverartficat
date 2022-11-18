package com.design.factory;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/9/2 9:23
 */
public class FactoryDemo {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();
    }
}
