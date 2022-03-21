package com.example.robottest;

import com.example.robottest.model.Category;
import com.example.robottest.model.Part;
import com.example.robottest.model.Stock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class RobotTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotTestApplication.class, args);

       Stock.setParts(initStock());
    }

    /**
     * initialising the stock
     * @return list of stock parts
     */
    public static ArrayList<Part> initStock(){
        ArrayList<Part> stock = new ArrayList<>();
        stock.add(new Part("A" , 10.28f, 9 , "Humanoid Face"         , Category.FACE));
        stock.add(new Part("B" , 24.07f, 7 , "LCD Face"              , Category.FACE));
        stock.add(new Part("C" , 13.30f, 0 , "Steampunk Face"        , Category.FACE));
        stock.add(new Part("D" , 28.94f, 1 , "Arms with Hands"       , Category.ARMS));
        stock.add(new Part("E" , 12.39f, 3 , "Arms with Grippers"    , Category.ARMS));
        stock.add(new Part("F" , 30.77f, 2 , "Mobility with Wheels"  , Category.MOBILITY));
        stock.add(new Part("G" , 55.13f, 15, " Mobility with Legs"  , Category.MOBILITY));
        stock.add(new Part("H" , 50.00f, 7 , "Mobility with Tracks"  , Category.MOBILITY));
        stock.add(new Part("I" , 90.12f, 92, " Material Bioplastic" , Category.MATERIAL));
        stock.add(new Part("J" , 82.31f, 15, "Material Metallic"    , Category.MATERIAL));
        return stock;
    }

}
