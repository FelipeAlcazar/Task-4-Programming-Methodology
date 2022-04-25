package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/*********************************************************************
*
* Class Name: App
* Author/s name: Felipe Alcázar Gómez and Elena Megía Cañaveras.
* Class description: In this class we have the whole program
**********************************************************************
*/

public class App 
{

    /*********************************************************************
    *
    * Method name: main
    *
    * Description of the Method:In the main method we call other methods to execute the nerdle.
    *
    *********************************************************************/
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


    /*********************************************************************
    *
    * Method name: Nerdle
    *
    * Description of the Method: In this method we execute de nerdle, we calculate the operation.
    *
    * Calling arguments: int, stage, it's the phase
    *                    String[], digitsOperands, here we store the operand uses
    *                    String, sol[], we store the solution
    *                    int, N
    *********************************************************************/

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


    /*********************************************************************
    *
    * Method name: subArray
    *
    * Description of the Method: Copies the specified range of the specified array into a new array.
    *
    * Calling arguments: T[], array
    *                    int, beg, he initial index of the range.
    *                    int, end, The final index of the range
    *
    * Return value: T[], return the copy of the array.
    *
    *********************************************************************/

    public static<T> T[] subArray(T[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end + 1);
    }

    /*********************************************************************
    *
    * Method name: isSolution
    *
    * Description of the Method: Checks if the result of the mathematical operation is equal to the number that we have as a result.
    *
    * Calling arguments: int, stage, it's the phase or the stage
    *                    String[], numeros, the numbers that we have
    *                    String, sol[], the solution
    *                    int N, the number that we have as a solution
    *
    * Return value: boolean, If it's the solution return a true value.
    *
    *********************************************************************/

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

    /*********************************************************************
    *
    * Method name: printSol
    *
    * Description of the Method: Print the rigth solution of the problem.
    *
    * Calling arguments: String[], Sol, the solution
    *                    int N, the number that the problem give us
    *
    *********************************************************************/

    public static void printSol(String[] Sol, int N){
        System.out.println("Expected Result "+N+" has been found");
        System.out.println("Nerdle expresion:");
        for (String str : Sol) {
            System.out.print(str);
        }
        System.out.println("\n");
    }

    /*********************************************************************
    *
    * Method name: printInstructions
    *
    * Description of the Method: We print the instruction and the data of the Nerdle.
    *
    * Calling arguments: int, N, the number that the problem give us, that is the solution.
    *                    String[], digitsOperands, the numbers and operands that we have             
    *
    *********************************************************************/

    public static void printInstructions(int N, String [] digitsOperands){
        System.out.println("The result must be: "+ N);
        System.out.println("Digits and operands for this execution: ");
        System.out.println(Arrays.toString(digitsOperands));
        System.out.println("NOTE: there may not be possible combinations for a certain result since we only use 7 different numbers."+"\n");
    }
    
    /*********************************************************************
    *
    * Method name: genDigistAndOperands
    *
    * Description of the Method: Chose in a random way the data we are going to use to find a solution of the problem.
    *
    * Return value: String[], it's a vector with the chosen data.
    *
    *********************************************************************/

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

    /*********************************************************************
    *
    * Method name: generateRandomResult
    *
    * Description of the Method: Generate a random number which is the result for which we are looking for a mathematical operation.
    *
    * Calling arguments: int, maxValue, maximun value
    *                    int, minValue, minimun value
    *
    * Return value: int, return a number.
    *
    *********************************************************************/

    public static int generateRandomResult(int maxValue, int minValue){
        return (int)Math.floor(Math.random()*(maxValue-minValue+1)+minValue);
    }
}
