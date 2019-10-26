package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Programmer: Benjamin Riveira
 * Source Code File: GradeBook.java
 * Description: This is an object-oriented implementation of a Grade Book.
 * This program allows a user to "track" the grades for a class of 35 students
 * using the grading items and scale shown on our CMPSCI 111 (Lecture) class
 * syllabus.
 */
public class GradeBook {
    /* Declaration of the global constant and variable for this GradeBook.
     * The constant describes the size of the array which will hold
     * Student object references, and the global variable is a reference
     * to an array of Student-type objects.  The reference variable is
     * named "myClass".  This array is declared globally because it is
     * "used" by several methods in this class.
     */
    final int CLASS_MAX_SIZE = 35;
    private Student[] myClass = new Student[CLASS_MAX_SIZE];
    
    /* The main() method prompts the user for the name of the input file,
     * passes the name of the input file to the loadClass() method, displays
     * an empty line, then invokes the showMenu() method.
     */
    public static void main(String[] args) {
        GradeBook myGradeBook = new GradeBook();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the class data file name: ");
        myGradeBook.loadClass(input.nextLine());
        System.out.println();
        myGradeBook.showMenu();
    }
    /**
     * @param filename a String which represents the name of the file containing 
     * the data to be loaded into the Student-type objects.
     * 
     * Precondition: filename must be the name of a file in the root folder of
     * this Netbeans Project.
     * 
     * Postcondition: If filename is the name of a file in the root folder of
     * this Netbeans Project, the data in the file will be "loaded" into 
     * CLASS_MAX_SIZE Student-type objects and each Student-type object 
     * will be "stored" in each of the CLASS_MAX_SIZE elements of the myClass 
     * array.
     */
    public void loadClass(String filename) {
        File inputFile = new File(filename);
        int i = 0;
        try{
            Scanner input = new Scanner(inputFile);
            /* Create and load each Student-type object with data read 
               from the text file */
            while(input.hasNext()) {
                // Declare new Student object with a name
                Student newStudent = new Student(input.nextLine());
                // Set SID
                newStudent.setSid(input.nextLine());
                // Set Homework scores
                newStudent.setHomework(1, input.nextDouble());
                newStudent.setHomework(2, input.nextDouble());
                newStudent.setHomework(3, input.nextDouble());
                newStudent.setHomework(4, input.nextDouble());
                // Set Quiz scores
                newStudent.setQuiz(1, input.nextDouble());
                newStudent.setQuiz(2, input.nextDouble());
                newStudent.setQuiz(3, input.nextDouble());
                newStudent.setQuiz(4, input.nextDouble());
                // Set Exam scores
                newStudent.setMidtermExam(input.nextDouble());
                newStudent.setFinalExam(input.nextDouble());
                input.nextLine();
                // Store a reference to this Student-type object in the array
                myClass[i] = newStudent;
                i++;
            }
        }
        /* If the filename cannot be found, an Exception is thrown and this 
         * catch block is executed.  This catch block displays an error
         * message and exits the program with an exit code of 1 (to indicate 
         * something went wrong!).
         */
        catch(FileNotFoundException e) {
            System.out.println("Error: " + filename + " could not be loaded.");
            System.exit(1);
        }   
    }
    /**
     * @param filename is a String which represents the name of the data file 
     * into which the Student-type object data will be written.
     * 
     * Precondition: None
     * 
     * Postcondition: The data contained in each of the MAX_CLASS_SIZE
     * Student-type objects in myClass are written to a text file in the
     * root folder of this project in the following format:
     * 
     * NewStudent, A.
     * 0000000
     * q1 q2 q3 q4 q5 m f
     * 
     * Where NewStudent, A. is the name of the student, 0000000 is the
     * student's SID number, q1 - q4 are the student's score for quizzes
     * 1 through 4, m is the student's Midterm Exam score and f is the student's
     * Final Exam score.
     */
    public void saveClass(String filename) {
        File outputFilename = new File(filename);
        
        try {
            PrintWriter outputFile = new PrintWriter(outputFilename);
            /* Use a for-next loop to iterate over myClass, invoking the
               Student-type object's accessor methods to write the data
               to outputFile.
             */
            for(Student aStudent : myClass) {
                outputFile.println(aStudent.getName());
                outputFile.println(aStudent.getSid());
                outputFile.println(
                        aStudent.getHomework(1) + " "
                        + aStudent.getHomework(2) + " "
                        + aStudent.getHomework(3) + " "
                        + aStudent.getHomework(4) + " "
                        + aStudent.getQuiz(1) + " " 
                        + aStudent.getQuiz(2) + " " 
                        + aStudent.getQuiz(3) + " "
                        + aStudent.getQuiz(4) + " "
                        + aStudent.getMidtermExam() + " " 
                        + aStudent.getFinalExam());
            }
            outputFile.close();
        }
        /* If the filename cannot be opened for output, an Exception is thrown
         * and this catch block is executed.  This catch block displays an error
         * message and exits the program with an exit code of 1 (to indicate 
         * that something went wrong!).
         */
        catch(FileNotFoundException e) {
            System.out.println("Error: " + filename + " could not be loaded.");
            System.exit(1);            
        }
    }
    /**
     * Displays a menu of choices, accepts the user's input and invokes the
     * method which performs the operation of the user's choice.  If additional
     * data are required for a method's arguments, this method collects that
     * data prior to invoking the method.
     */
    public void showMenu() {
        Scanner input = new Scanner(System.in);
        int userChoice = 0;
        int recordNumber;
        final int MAX_CHOICES = 7;
        
        while(userChoice != MAX_CHOICES) {
            System.out.println("GradeBook");
            System.out.println("=========");
            System.out.println("1. View grades for entire class.");
            System.out.println("2. View grades for one student.");
            System.out.println("3. Change a student's homework score.");
            System.out.println("4. Change a student's quiz score.");
            System.out.println("5. Change a student's exam score.");
            System.out.println("6. Save current grade book to disk.");
            System.out.println("7. Exit.");
            System.out.print("Your choice?> ");
            userChoice = input.nextInt();
            
            switch(userChoice) {
                case 1: // The user has chosen option 1.
                    // Invoke displayTitles() and displayClassGrades()
                    displayTitles();
                    displayClassGrades();
                    break;
                case 2: // The user has chosen option 2.
                    /* The program needs to know the record number of the
                       student whose grades will be viewed, so it prompts
                       the user for a record number.
                     */
                    recordNumber = 0;
                    System.out.print("Record number?> ");
                    recordNumber = input.nextInt();
                    /* If the user provides a valid record number, invoke
                       displayTitles() and displayStudentGrades(), passing 
                       the Student-type object and the user-specified 
                       recordNumber.
                     */
                    if(recordNumber >= 1 && recordNumber <= CLASS_MAX_SIZE) {
                        System.out.println();
                        displayTitles();
                        displayStudentGrades(myClass[recordNumber - 1], recordNumber);
                        System.out.println();
                    }
                    /* If the user provides an invalid record number, print
                       an error message.
                    */
                    else {
                        System.out.println("Error: record " + recordNumber 
                                + " does not exist.");
                    }
                    break;
                case 3: // The user has chosen option 3.
                    /* The program needs to know the record number of the
                       student whose homework score will be changed, so it
                       prompts the user for a record number.
                    */
                    recordNumber = 0;
                    System.out.print("Record number?>");
                    recordNumber = input.nextInt();
                    /* If the user provides a valid record number, invoke
                       displayTitles() and setHomework(), passing the
                       Student-type object and the user-specified recordNumber,
                       and finally displaying an empty line after returning
                       from setHomework().
                    */
                    if(recordNumber >= 1 && recordNumber <= CLASS_MAX_SIZE) {
                        System.out.println();
                        displayTitles();
                        setHomework(myClass[recordNumber - 1], recordNumber);
                        System.out.println();
                    }
                    else {
                        System.out.println("Error: record " + recordNumber 
                                + " does not exist.");
                    }                    
                    break;
                case 4: // The user has chosen option 4.
                    /* The program needs to know the record number of the
                       student whose quiz score will be changed, so it prompts
                       the user for a record number.
                     */                    
                    recordNumber = 0;
                    System.out.print("Record number?> ");
                    recordNumber = input.nextInt();
                    /* If the user provides a valid record number, invoke
                       displayTitles() and setQuiz(), passing 
                       the Student-type object and the user-specified 
                       recordNumber, and finally display an empty line
                       after returning from setQuiz().
                     */                    
                    if(recordNumber >= 1 && recordNumber <= CLASS_MAX_SIZE) {
                        System.out.println();
                        displayTitles();
                        setQuiz(myClass[recordNumber - 1], recordNumber);
                        System.out.println();
                    }
                    /* If the user provides an invalid record number, print
                       an error message.
                    */                    
                    else {
                        System.out.println("Error: record " + recordNumber 
                                + " does not exist.");
                    }                    
                    break;
                case 5: // The user has chosen option 5.
                    /* The program needs to know the record number of the
                       student whose exam score will be changed, so it prompts
                       the user for a record number.
                     */                      
                    recordNumber = 0;
                    System.out.print("Record number?> ");
                    recordNumber = input.nextInt();
                    /* If the user provides a valid record number, invoke
                       displayTitles() and setExam(), passing 
                       the Student-type object and the user-specified 
                       recordNumber, and finally display an empty line
                       after returning from setExam().
                     */                    
                    if(recordNumber >= 1 && recordNumber <= CLASS_MAX_SIZE) {
                        System.out.println();
                        displayTitles();
                        setExam(myClass[recordNumber - 1], recordNumber);
                        System.out.println();
                    }
                    /* If the user provides an invalid record number, print
                       an error message.
                    */                    
                    else {
                        System.out.println("Error: record " + recordNumber 
                                + " does not exist.");
                    }                    
                    break;
                case 6: // The user has chosen option 6.
                    String filename;
                    input.nextLine(); // Get rid of the newline character in the input buffer.
                    /* The program needs to know the name of the data file
                       into which the data will be written, so it prompts
                       the user for the name of the output file then invokes
                       saveClass(), passing filename as the argument.
                     */
                    System.out.print("Enter the the name for the output file: ");
                    filename = input.nextLine();
                    saveClass(filename);
                    break;
                case 7: // User has chosen option 7.
                    /* The program exits, returning a value of 0 to indicate
                       that the program has ended normally 
                       (nothing went wrong!).
                    */
                    System.exit(0);
                    break;
                default:
                    System.out.println(userChoice 
                            + " is not a valid menu option.");
            }
        }
            
    }
    /**
     * @param student, a Student-type object reference variable.
     * @return a char value representing the Student-type object's 
     * final letter grade.
     * 
     * Precondition: student must be an object reference to a Student-type
     * object.
     * 
     * Postcondition: the char, representing the Student-type object's final
     * letter grade, according to the CMPSCI 111 grading scale shown in the
     * syllabus, is returned.
     */
    private char getFinalGrade(Student student) {
        double finalScore = 0;
        // Compute the sum of the student's quiz, homework and exam scores
        for(int i = 1; i <= Student.NUM_QUIZZES; i++) {
            finalScore += student.getQuiz(i);
        }
        for(int i = 1; i <= Student.NUM_HOMEWORK; i++) {
            finalScore += student.getHomework(i);
        }
        finalScore += student.getMidtermExam();
        finalScore += student.getFinalExam();
        // Determine the letter grade for the Student's finalScore.
        if(finalScore >= 175) {
            return 'A';
        }
        else if(finalScore >= 150) {
            return 'B';
        }
        else if(finalScore >= 120) {
            return 'C';
        }
        else if(finalScore >= 100) {
            return 'D';
        }
        else {
            return 'F';
        }
    }
    /** @param aStudent, a Student-type object reference.
     *  @ recordNumber, an integer representing the record number of the student
     *  whose homework to set.
     * 
     *  Precondition: aStudent must be a reference to a Student-type object.
     * 
     *  PostCondition: After this method executes one a aStudents homework
     *  scores will be changed.
     */
    private void setHomework(Student aStudent, int recordNumber) {
        int homeworkNumber = 0;
        double homeworkScore = 0;
        Scanner input = new Scanner(System.in);
        /* Invoke the displayStudentGrades() method, passing the reference to
           the Student-type object and the record number to 
           displayStudentGrades().
        */
        displayStudentGrades(aStudent, recordNumber);
        System.out.println();
        /* The program needs to know which homework to change, so it prompts the
           homework number.
        */
        System.out.print("Which Homework number do you wish to change?> ");
        homeworkNumber = input.nextInt();
        if(homeworkNumber > 0 && homeworkNumber <= Student.NUM_HOMEWORK) {
            /* If the user inputs a valid homework number, the program needs to
               know the Student's score on that homework, so it prompts the
               user for the homework score.
            */
            System.out.print("Enter the new score for this homework (0-" 
                    + Student.HOMEWORK_MAX_POINTS + "): ");
            homeworkScore = input.nextDouble();
            /* Invoke the setQuiz method on the Student-type object, passing
               quizNumber and quizScore as arguments, display an empty line,
               then invoke displayTitles() and displayStudentGrades(), passing 
               aStudent and recordNumber as arguments.
            */
            aStudent.setHomework(homeworkNumber, homeworkScore);
            System.out.println();
            displayTitles();
            displayStudentGrades(aStudent, recordNumber);
        }
        /* If the user provides an invalid quiz number, print
           an error message.
        */    
        else {
            System.out.println("Error: could not set homework " + homeworkNumber 
                    + " score.");
        }
    }
    /**
     * @param aStudent, a Student-type object reference.
     * @param recordNumber, an integer representing the record number of the 
     * student whose quiz to set.
     * 
     * Precondition: aStudent must be a reference to a Student-type object.
     * 
     * PostCondition: After this method executes one of aStudent's quiz scores
     * will be changed.
     */
    private void setQuiz(Student aStudent, int recordNumber) {
        int quizNumber = 0;
        double quizScore = 0;
        Scanner input = new Scanner(System.in);
        /* Invoke the displayStudentGrades() method, passing the reference to 
           the Student-type object and the record number to 
           displayStudentGrades().
        */
        displayStudentGrades(aStudent, recordNumber);
        System.out.println();
        /* The program needs to know which quiz to change, so it prompts the
           user for the quiz number.
        */
        System.out.print("Which Quiz number do you wish to change?> ");
        quizNumber = input.nextInt();
        if(quizNumber > 0 && quizNumber <= Student.NUM_QUIZZES){
            /* If the user inputs a valid quiz number, the program needs to know
               the Student's score on that quiz, so it prompts the user
               for the quiz score.
            */            
            System.out.print("Enter the new score for this quiz (0-" 
                    + Student.QUIZ_MAX_POINTS + "): ");
            quizScore = input.nextDouble();
            /* Invoke the setQuiz method on the Student-type object, passing
               quizNumber and quizScore as arguments, display an empty line,
               then invoke displayTitles() and displayStudentGrades(), passing 
               aStudent and recordNumber as arguments.
            */
            aStudent.setQuiz(quizNumber, quizScore);
            System.out.println();
            displayTitles();
            displayStudentGrades(aStudent, recordNumber);
        }
        /* If the user provides an invalid quiz number, print
           an error message.
        */    
        else {
            System.out.println("Error: could not set quiz " + quizNumber 
                    + " score.");
        }
    }
    /**
     * @param aStudent, a Student-type object reference.
     * @param recordNumber, an integer representing the record number of the 
     * student whose exam to set.
     * 
     * Precondition: aStudent must be a reference to a Student-type object.
     * 
     * PostCondition: After this method executes one of aStudent's exam scores
     * will be changed.
     */    
    private void setExam(Student aStudent, int recordNumber) {
        int examNumber = 0;
        double examScore = 0;
        Scanner input = new Scanner(System.in);
        /* Invoke the displayStudentGrades() method, passing the reference to 
           the Student-type object and the record number to 
           displayStudentGrades().
        */        
        displayStudentGrades(aStudent, recordNumber);
        System.out.println();
        /* The program needs to know which of the Student-type object's exams
           to change, so it prompts the user for the exam number.
        */
        System.out.print("Enter 1 to change Midterm Exam and 2 "
                + "to change Final Exam> ");
        examNumber = input.nextInt();

        if(examNumber == 1) {
            /* If the user has chosen 1, change Midterm Exam, the program
               needs to know the Student's score on the Midterm Exam, so it
               prompts the user for a Midterm Exam score.
            */
            System.out.print("Enter the new Midterm Exam score(0-" 
                    + Student.MIDTERM_MAX_POINTS + "): ");
            examScore = input.nextDouble();
            /* If the user has entered a Midterm Exam score which is less than
               or equal to Student.MIDTERM_MAX_POINTS), invoke
               setMidtermExam() on the Student-type object, passing examScore,
               display an empty line, invoke displayTitles() and invoke
               displayStudentGrades, passing aStudent and recordNumber.
            */
            if(examScore <= Student.MIDTERM_MAX_POINTS) {
                aStudent.setMidtermExam(examScore);
                System.out.println();
                displayTitles();
                displayStudentGrades(aStudent, recordNumber);
            }
            /* If the user has entered an invalid examScore, display an
               error message.
            */
            else {
                System.out.println("Error: could not set Midterm Exam score.");
            }
        }
        else if(examNumber == 2) {
            /* If the user has chosen 2, change Final Exam, the program
               needs to know the Student's score on the Final Exam, so it
               prompts the user for a Final Exam score.
            */            
            System.out.print("Enter the new Final Exam score (0-" 
                    + Student.FINAL_MAX_POINTS + "): ");
            examScore = input.nextDouble();
            /* If the user has entered a Final Exam score which is less than
               or equal to Student.Final_MAX_POINTS), invoke
               setFinalExam() on the Student-type object, passing examScore,
               display an empty line, invoke displayTitles() and invoke
               displayStudentGrades, passing aStudent and recordNumber.
            */            
            if(examScore <= Student.FINAL_MAX_POINTS) {
                aStudent.setFinalExam(examScore);
                System.out.println();
                displayTitles();
                displayStudentGrades(aStudent, recordNumber);
            }
            /* If the user has entered an invalid examScore, display an
               error message.
            */
            else {
                System.out.println("Error: could not set Final Exam score.");
            }
        }
        /* If the user has entered an invalid Exam number, display an
           error message.
        */
        else {
            System.out.println("Error: could not set exam score.");
        }
    }
    /**
     * Displays the titles for the grade table.
     */
    public void displayTitles() {
        System.out.println("   \tName\t\tSID\t  H1\tH2\tH3\tH4\tQ1\tQ2\tQ3\tQ4"
                + "\tMidTerm\t   Final\tGrade");        
    }
    /**
     * Displays the grades for all Student-type objects in myClass.
     */
    public void displayClassGrades() {
        int recordNumber = 1;
        /* Use a for-next loop to iterate over the Student-type objects in
           myClass and for each one, invoke displayStudentGrades(), passing
           aStudent and recordNumber as arguments.
        */
        for(Student aStudent : myClass) {
            displayStudentGrades(aStudent, recordNumber);
            recordNumber++;
        }
        System.out.println();
    }
    /** 
     * @param aStudent a Student-type reference variable.
     * @param recordNumber an int representing a Student-type object's record 
     * number.
     * 
     * Precondition: aStudent must be a reference to a Student-type object, 
     * recordNumber must be the record number of the Student-type object.
     * 
     * Postcondition: The data contained in this student object will be
     * displayed in table form.
     */
    private void displayStudentGrades(Student aStudent, int recordNumber) {
            String studentInfo = aStudent.getName();
            
            if(studentInfo.length() < 10) {
                for(int i = 0; i < 10 - studentInfo.length(); i++) {
                    studentInfo += " ";
                }    
            }            
            
            System.out.print(recordNumber + "\t");
            
            studentInfo += "\t" + aStudent.getSid() + "\t  ";
            for(int i = 1; i <= Student.NUM_HOMEWORK; i++) {
                studentInfo += aStudent.getHomework(i) + "\t";
            }
            for(int i = 1; i <= Student.NUM_QUIZZES; i++) {
                studentInfo += aStudent.getQuiz(i) + "\t";
            }
            //studentInfo += "\t";
            studentInfo += aStudent.getMidtermExam() + "\t   ";
            studentInfo += aStudent.getFinalExam() + "\t\t";
            System.out.print(studentInfo);
            System.out.println(getFinalGrade(aStudent));
    }    
}