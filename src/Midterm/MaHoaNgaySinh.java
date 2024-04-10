package Midterm;


import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class MaHoaNgaySinh implements Runnable {
    private BlockingQueue<Student> inputQueue ;
    private BlockingQueue<Student> outputQueue; 
    
	public MaHoaNgaySinh(BlockingQueue<Student> inputQueue, BlockingQueue<Student> outputQueue) {
		super();
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
	}
	@Override
	public void run() {
		try {
			while(true) {
				Student student = inputQueue.take() ;
//			    tính toán tuổi 
				int age = tinhToanTuoi(student.getDateOfBirth()) ;
//				check xem có phải số nguyên tố
				boolean isPrime = isPrime(tongChuSo(student.getDateOfBirth())) ;
				student.setAge(age);
				student.setIPrime(isPrime);
				outputQueue.put(student);
				
			}
		} catch (Exception e) {
			  Thread.currentThread().interrupt();
		}
		
	}
	private int tinhToanTuoi(String dateOfBirth) {
	    int age = 0;
	    try {
	        Calendar now = Calendar.getInstance();
	        Date dob = Student.dateFormat.parse(dateOfBirth);
	        Calendar dobCalendar = Calendar.getInstance();
	        dobCalendar.setTime(dob);
	        
	        int kcNam = now.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);
	        int kcThang = now.get(Calendar.MONTH) - dobCalendar.get(Calendar.MONTH);
	        int kcNgay = now.get(Calendar.DAY_OF_MONTH) - dobCalendar.get(Calendar.DAY_OF_MONTH);

	        if (kcThang < 0 || (kcThang == 0 && kcNgay < 0)) {
	            age = kcNam - 1;
	        } else {
	            age = kcNam;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return age;
	}

private int tongChuSo(String input) {
	int sum = 0 ;
	for (int i = 0; i < input.length() ; i++) {
		if (Character.isDigit(input.charAt(i))) {
			sum+= Character.getNumericValue(input.charAt(i)) ;
		}
	}
	return sum ;
}
public static boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    int i = 5;
    while (i * i <= n) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
        i += 6;
    }
    return true;
}
}
