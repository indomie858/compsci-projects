

/*
 * Project #6
 * Source Code File: Student.java
 * Programmer: Gaven Grantz
 * Due: 12/7/17
 * Description: For this last project, I will implement a Java class which
 * “simulates” a Student object. Student objects created from this class will 
 * represent grade records for students in a CMPSCI 111 lecture class. 
 */
public class Student {
    
    private String name, sid;
            
    private double[] homework = new double[NUM_HOMEWORK];
    
    private double[] quizzes = new double[NUM_QUIZZES];
    
    private double[] exams = new double[NUM_EXAMS];
        
    final static int NUM_HOMEWORK = 4;
    
    final static int NUM_QUIZZES = 4;
    
    final static int NUM_EXAMS = 2;
    
    final static double HOMEWORK_MAX_POINTS = 5.0;
    
    final static double QUIZ_MAX_POINTS = 20.0;
    
    final static double MIDTERM_MAX_POINTS = 40.0;
    
    final static double FINAL_MAX_POINTS = 60.0;
    
    Student(){
        
        name = "Newstudent, A";
        sid = "0000000";
        
        double[] homework = new double[NUM_HOMEWORK];
        
        double[] quizzes = new double[NUM_QUIZZES];
        
        double[] exams = new double[NUM_EXAMS];
               
    }
    
    Student(String newName){
        
        name = newName;
        sid = "0000000";
        
        double[] homework = new double[NUM_HOMEWORK];
        
        double[] quizzes = new double[NUM_QUIZZES];
        
        double[] exams = new double[NUM_EXAMS];
    }
    
    Student(String newName, String newSid){
        
        name = newName;
        sid = newSid;
        
        double[] homework = new double[NUM_HOMEWORK];
        
        double[] quizzes = new double[NUM_QUIZZES];
        
        double[] exams = new double[NUM_EXAMS];
    }
    
    void setName(String newName){
        name = newName;
    }
    
    String getName(){
        return name;
    }
    
    void setSid(String newSid){
        sid = newSid;
    }
    
    void setHomework(int homeworkNumber, double score){
        
        if (homeworkNumber >= 1 && homeworkNumber <= NUM_HOMEWORK
                && score >=0 && score <= HOMEWORK_MAX_POINTS){
            
            homework[homeworkNumber - 1] = score;
            
        }
    }
    
    double getHomework(int homeworkNumber) {
	if (homeworkNumber >= 1 && homeworkNumber <= NUM_HOMEWORK) {
		return homework[homeworkNumber - 1];
	}
	else {
	return 0;
	}
}


    void setQuiz(int quizNumber, double score) {
            if (quizNumber >=1 && quizNumber <= NUM_QUIZZES 
                    && score >= 0 && score <= QUIZ_MAX_POINTS){
                    quizzes[quizNumber - 1] = score;
            }
    }


    double getQuiz(int quizNumber) {
            if (quizNumber >= 1 && quizNumber <= NUM_QUIZZES) {
                    return quizzes[quizNumber - 1];
            }
            else {
                    return 0;
            }
    }


    void setMidtermExam(double score) {
            if (score >= 0 && score <= MIDTERM_MAX_POINTS) {
                    exams[0] = score;
            }
    }


    double getMidtermExam() {
            return exams[0];
    }


    void setFinalExam(double score) {
            if (score >= 0 && score <= FINAL_MAX_POINTS) {
                    exams[1] = score;
            }
    }


    double getFinalExam() {
            return exams[1];
    }

    public String toString() {
            
        return name + sid + homework + quizzes + exams;
    }
}
