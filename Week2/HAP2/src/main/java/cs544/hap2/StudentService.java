package cs544.hap2;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentService {
	private StudentDAO studentdao;
	private SessionFactory sf = HibernateUtil.getSessionFactory();


	public StudentService() {
		studentdao = new StudentDAO();
	}

	public Student getStudent(long studentid) {
		return studentdao.load(studentid);
	}
}
