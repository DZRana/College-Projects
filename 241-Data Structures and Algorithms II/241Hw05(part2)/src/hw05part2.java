//Written by Danzel Rana
//
//Solves CS 241, Summer 2014, Homework 05 Part 2.
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
public class hw05part2
{
//-----------------------------------------------------------------------
	public static void main(String[] args) throws IOException
	{
		String[] names = {"Abe", "Bill","Bob","Ken","Dan","Kel","Ben","Gio",
								"Roy","Del","Sen","Ren","Hen","Jil","Ger","Tes",
								"Tib","Wes","Ven","Stu"};
		String[] ssn = {"111111111","111111112","111111113","111111114","111111115","111111116",
				        	 "111111121","111111122","111111123","111111124","111111125","111111126",
				        	 "111111131","111111132","111111133","111111134","111111135","111111136",
				          "111111141","111111142"};
		float[] salary = {23456,12345,43215,52134,24156,32467,73254,63452,43521,
								76462,89156,81591,94871,32158,41226,84712,55516,97001,
								98104,78144};
		int[] age =	{21,42,23,63,24,54,64,36,34,42,51,56,21,42,43,67,32,54,32,42};

		RandomAccessFile raf = new RandomAccessFile("employees.txt", "rw");
		HashMap hm = new HashMap();
		for (int i = 0; i < 20; i++)
		{
			Employee e = new Employee (ssn[i], names[i], salary[i], age[i]);
			raf.writeUTF(e.getSSN());
			raf.writeUTF(e.getName());
			raf.writeFloat(e.getSalary());
			raf.writeInt(e.getAge());
			hm.put(e.getName(), e.getSSN().hashCode());
		}
		for (int i = 0; i <= 7680; i+=14)
		{
			raf.seek(0);
			System.out.println("SSN: " + raf.readUTF());
			System.out.println("Name: " + raf.readUTF());
			System.out.println("Salary: " + raf.readFloat());
			System.out.println("Age: " + raf.readInt());
			System.out.println();
		}
		raf.close();
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter SSN: ");
		String k = kb.next();
		System.out.println("Enter new salary: ");
		float s = kb.nextFloat();
		System.out.println("Old Salary: " + hm.get(k));
		hm.put(k, hm.get(k));
		System.out.println("New Salary: "+ hm.get(k));
		
	}
//-----------------------------------------------------------------------
} // end class hw05part2
/////////////////////////////////////////////////////////////////////////
class Employee
{
	private String ssn;
	private String name;
	private float salary;
	private int age;
//-----------------------------------------------------------------------
	public Employee (String ssn, String name, float salary, int age)
	{
		this.ssn = ssn;
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
//-----------------------------------------------------------------------
	public String getSSN() {return ssn;};
//-----------------------------------------------------------------------
	public String getName() {return name;};
//-----------------------------------------------------------------------
	public float getSalary() {return salary;};
//-----------------------------------------------------------------------
	public void setSalary(float newS) {salary = newS;};
//-----------------------------------------------------------------------
	public int getAge() {return age;};
//-----------------------------------------------------------------------
} // end class Employee
/////////////////////////////////////////////////////////////////////////