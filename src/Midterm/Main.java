package Midterm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
public static void main(String[] args) {
//   XMLCreater xmlCreater = new XMLCreater();
   BlockingQueue<Student> studentQueue = new LinkedBlockingDeque<>()	;
   BlockingQueue<Student> ageQueue = new LinkedBlockingDeque<>() ;
   List<Student> resultStudents = new ArrayList<>() ;
   Thread docFileStudent = new Thread(new ReadStudentFile("D:/JavaOPP/Mid-Term/students.xml", studentQueue)) ;
   Thread tinhToanTuoi = new Thread(new MaHoaNgaySinh(studentQueue, ageQueue));
   Thread primeCheckerThread = new Thread(new PrimeChecker(ageQueue, resultStudents)) ;
   docFileStudent.start(); 
   tinhToanTuoi.start(); 
   primeCheckerThread.start(); 
   try {
	docFileStudent.join(); 
	tinhToanTuoi.join();
	primeCheckerThread.join();
	WriteResultFile.writeResultFile(resultStudents, "kq.xml");
} catch (Exception e) {
	e.printStackTrace(); 
}
} 
}
