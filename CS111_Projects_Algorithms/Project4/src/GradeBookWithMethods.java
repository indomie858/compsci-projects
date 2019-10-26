/*
 * Project #4
 * Source Code File: GradeBookWithMethods.java
 * Programmer: Gaven Grantz
 * Due: 11/2/17
 * Description: This is a Java program that stores grade scores through user
 * input. The program will take those scores and display the number of grades
 * and the average grade. In this type of development, a previously developed 
 * application is given another "go around” where improvements are added and, 
 * if necessary, corrections are made. 
 */

import java.util.Scanner;






public class GradeBookWithMethods {

    public static void main(String[] args) {
        //Prompts user for input.
        System.out.print("Hello. Please enter your grade scores: ");
        Scanner input = new Scanner(System.in);
        
        //Assigns double and int variables.
        int totalNumA, totalNumB, totalNumC, totalNumD, totalNumF, numOfScores;
        double sumA, sumB, sumC, sumD, sumF, sumOfScores, avgOfScores;
        double inputGrade; 
       
        //Set totalNum and sum variables to 0.
        totalNumA = 0;
        totalNumB = 0;
        totalNumC = 0;
        totalNumD = 0;
        totalNumF = 0;
        sumA = 0;
        sumB = 0;
        sumC = 0;
        sumD = 0;
        sumF = 0;
       
        //Loops and stores input variables.
        do {
            //Assigns user input to inputGrade.
            inputGrade = input.nextDouble();
            
            //Selection statements that store grade scores.
            //Totals up number of grades.
            if(inputGrade >= 0){
                if(inputGrade == 100 || inputGrade >= 90){
                    totalNumA++;
                    sumA += inputGrade;
                    System.out.println("The score of " + inputGrade + " is an "
                            + getGrade(inputGrade));
                    System.out.println("Enter a negative value to display results.");
                }
                else if(inputGrade >= 80){
                    totalNumB++;
                    sumB += inputGrade;
                    System.out.println("The score of " + inputGrade + " is a "
                            + getGrade(inputGrade));
                    System.out.println("Enter a negative value to display results.");
                }
                else if(inputGrade >= 70){
                    totalNumC++;
                    sumC += inputGrade;
                    System.out.println("The score of " + inputGrade + " is a " 
                            + getGrade(inputGrade));
                    System.out.println("Enter a negative value to display results.");
                }
                else if(inputGrade >= 60){
                    totalNumD++;
                    sumD += inputGrade;
                    System.out.println("The score of " + inputGrade + " is a " 
                            + getGrade(inputGrade));
                    System.out.println("Enter a negative value to display results.");
                }
                else {
                    totalNumF++;
                    sumF += inputGrade;
                    System.out.println("The score of " + inputGrade + " is a " 
                            + getGrade(inputGrade));
                    System.out.println("Enter a negative value to display results.");
                }                
            }
            else{
                System.out.println();
                System.out.println("Computing grade...");
            }

        //Exits loop when a negative value is entered.
        }while(inputGrade >= 0);
       
        //Assigns stored values to variables numOfScores, sumOfScores, and avgOfScores.
        numOfScores = totalNumA + totalNumB + totalNumC + totalNumD 
                    + totalNumF;
	sumOfScores = sumA + sumB + sumC + sumD + sumF;
        avgOfScores = getAverageScore(sumOfScores, numOfScores);
                     
        //Outputs total of grades, average grade, and letter score.
	printGradeReport(totalNumA, totalNumB, totalNumC, totalNumD, totalNumF,
		numOfScores, avgOfScores);
    }    


    //Method that determines letter grade using a double value.
    public static char getGrade(double score){
       
        char gradeLetter;

        if (score >=0){
        	if(score == 100 || score >= 90){
                    gradeLetter = 'A';
                }
                else if(score >= 80){
                    gradeLetter = 'B';
                }
                else if(score >= 70){
                    gradeLetter = 'C';
                }
                else if(score >= 60){
                    gradeLetter = 'D';
                }
                else{
                    gradeLetter = 'F';
                }
        }
        else{
                gradeLetter = 'F';
        }

        return gradeLetter;
    }


    //Method that computes average score using sumOfScores and numberOfScores.
    public static double getAverageScore(double sumOfScores, int numberOfScores){
        double avgScore;
        avgScore = sumOfScores / numberOfScores;
        return avgScore;
    }


    //Method that prints grade report.
    public static void printGradeReport(int As, int Bs, int Cs, int Ds,
        int Fs, int numberOfScores, double averageScore){	
        
	System.out.println();
        System.out.println("           Grade Report             ");
        System.out.println("------------------------------------");
        System.out.println("Total number of A's : " + As);
        System.out.println("Total number of B's : " + Bs);
        System.out.println("Total number of C's : " + Cs);
        System.out.println("Total number of D's : " + Ds);
        System.out.println("Total number of F's : " + Fs);
        System.out.println("Total number of scores input : " +
                    numberOfScores);
        System.out.println("Average class score: " + averageScore);
        System.out.println("Average class grade: " + getGrade(averageScore));
    }    
    
}
