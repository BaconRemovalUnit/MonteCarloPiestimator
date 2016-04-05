package estimator;

import graphics.BooleanPoint;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * University of Rochester
 * Author: Shengqi Suizhu
 * Date: 2016/3/21
 * estimator/Estimator
 */
public class Estimator {
    private final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950288419716939937" +
            "510582097494459230781640628620899862803482534211706");
    private final BigDecimal TWO = new BigDecimal("2.0");
    private final BigDecimal FOUR = new BigDecimal("4.0");
    private BigDecimal  hit;
    private BigDecimal  miss;
    private BigDecimal  total;
    private BigDecimal  error;
    private BigDecimal  Pi;

    private BigDecimal  radius;
    private BigDecimal  r2;

    public Estimator(){
        radius = new BigDecimal(1);
        r2 = new BigDecimal(1);
        initialize();
    }

    public Estimator(int r){
        radius = new BigDecimal(r);
        r2 = radius.pow(2);
        initialize();
    }

    private void initialize(){
        hit = BigDecimal.ZERO;
        miss = BigDecimal.ZERO;
        total = BigDecimal.ZERO;
        Pi = BigDecimal.ZERO;
        error = BigDecimal.ZERO;
    }

    private boolean inRange(BigDecimal x,BigDecimal y){
        BigDecimal xpow = x.subtract(radius).pow(2);
        BigDecimal ypow = y.subtract(radius).pow(2);
        BigDecimal result = xpow.add(ypow);
        boolean in =  result.compareTo(r2)<=0;
        return in;

    }

    public BooleanPoint addPoint(){
        BigDecimal centerX = getRandomBigDecimal(radius);
        BigDecimal centerY = getRandomBigDecimal(radius);
        BooleanPoint temp = new BooleanPoint();
        if(inRange(centerX,centerY)){
            hit();
            temp.setHit(true);
        }else{
            miss();
            temp.setHit(false);
        }

        temp.setLocation(centerX.doubleValue(),centerY.doubleValue());
        return temp;
    }

    private void hit() {
        total = total.add(BigDecimal.ONE);
        hit = hit.add(BigDecimal.ONE);
        Pi = hit.divide(total,50, RoundingMode.HALF_UP).multiply(FOUR);
        BigDecimal accuracy = Pi.divide(PI,50,BigDecimal.ROUND_HALF_UP);
        error = BigDecimal.ONE.subtract(accuracy);
    }

    private void miss() {
        total = total.add(BigDecimal.ONE);
        miss = miss.add(BigDecimal.ONE);
        Pi = hit.divide(total,50, RoundingMode.HALF_UP).multiply(FOUR);

        BigDecimal accuracy = Pi.divide(PI,50,BigDecimal.ROUND_HALF_UP);
        error = BigDecimal.ONE.subtract(accuracy);
    }

    private BigDecimal getRandomBigDecimal(BigDecimal radius) {
        return new BigDecimal(Math.random()).multiply(radius).multiply(TWO);
    }

    public BigDecimal getPi(){
        return this.Pi;
    }

    public BigDecimal getError(){
        return this.error;
    }

    public synchronized BigDecimal getTotal(){
        return this.total;
    }
    
    public void printResults(){
        System.out.println("-----------------------");
        System.out.println("hit = " + hit);
        System.out.println("miss = " + miss);
        System.out.println("total = " + total);
        System.out.println("estimate = " +Pi);
        System.out.println("error = " + error);
    }

}
