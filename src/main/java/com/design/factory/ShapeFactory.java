package com.design.factory;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/9/2 9:19
 */
public class ShapeFactory {

    public static Shape getShape(String shapeType){

        if (null == shapeType){
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")){
            return new Circle();
        }else if(shapeType.equalsIgnoreCase("rectangle")){
            return new Rectangle();
        }else if(shapeType.equalsIgnoreCase("square")){
            return new Square();
        }

        return null;

    }

}
