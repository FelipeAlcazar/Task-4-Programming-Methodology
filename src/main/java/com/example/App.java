package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*
        Ejemplo del PDF (descomentar para ver)

        String[] digitsOperandsEx={"3","2","8","5","0","4","7","/","*"};
        int resEx=75;
        String sol[]=new String[5];
        Nerdle(0, digitsOperandsEx, sol, resEx);
        */

        int N=generateRandomResult(99,-9);
        String sol[]=new String[5];
        String[] digitsOperands=genDigitsAndOperands();
        printInstructions(N, digitsOperands);

        Nerdle(0, digitsOperands, sol, N);
    }

    public static void Nerdle(int stage, String[] digitsOperands, String sol[], int N){
        if(stage==sol.length){
            if(isSolution(stage, digitsOperands, sol, N)){
                printSol(sol,N);
            }
        }else{
            if(stage!=2){
                for (String operand : subArray(digitsOperands, 0, 6)) {
                    sol[stage]=operand;
                    Nerdle(stage+1, digitsOperands, sol, N);
                }
            }else{
                for (String operand : subArray(digitsOperands, 7, 8)) {
                    sol[stage]=operand;
                    Nerdle(stage+1, digitsOperands, sol, N);
                }
            }
        }
    }

    public static<T> T[] subArray(T[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end + 1);
    }

    public static boolean isSolution(int stage, String[] numeros, String sol[],int N){
        int num1=Integer.parseInt(sol[0])*10+Integer.parseInt(sol[1]);
        int num2=Integer.parseInt(sol[3])*10+Integer.parseInt(sol[4]);
        double resOP=0;
        boolean res=false;
        switch(sol[2]) {
            case "+":
                resOP=num1+num2;
                break;
            case "-":
                resOP=num1-num2;
                break; 
            case "*":
                resOP=num1*num2;
                break; 
            case "/":
                if(num1 != 0 && num2 !=0)
                    resOP=num1/num2;
                break; 
            }
        
            if(resOP==N)
                res=true;

        return res;
    }

    public static void printSol(String[] Sol, int N){
        System.out.println("Expected Result: "+N+" has been found");
        System.out.println("Nerdle expresion:");
        for (String str : Sol) {
            System.out.print(str);
        }
        System.out.println("\n");
    }

    public static void printInstructions(int N, String [] digitsOperands){
        System.out.println("The result must be: "+ N);
        System.out.println("Digits and operands for this execution: ");
        System.out.println(Arrays.toString(digitsOperands)+"\n");

    }
    
    public static String[] genDigitsAndOperands(){
        int MAX_VALUE=9;
        String[] operands = {"+", "-", "*", "/"};

        Random rand = new Random();
        ArrayList<String> res=new ArrayList<>();

        for (int i = 0; i <= MAX_VALUE; ++i)
            res.add(String.valueOf(i));
        
        Collections.shuffle(res, rand);
        res.subList(0, 3).clear();

        for(int j=0;j<operands.length;j++)
            res.add(String.valueOf(operands[j]));

        Collections.shuffle(res.subList(7, 11), rand);
        res.subList(9, 11).clear();
    
        return res.toArray(new String[res.size()]);
    }

    public static int generateRandomResult(int maxValue, int minValue){
        return (int)Math.floor(Math.random()*(maxValue-minValue+1)+minValue);
    }
}
