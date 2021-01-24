package top.dpdaidai.algorithm.elementary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 比较器
 *
 * @Author chenpantao
 * @Date 1/24/21 9:19 PM
 * @Version 1.0
 */
public class Note16_comparator {

    public static class Student extends Object {

        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        public String toString() {
            return "Name : " + this.name + ", Id : " + this.id + ", Age : " + this.age;
        }
    }

    public static void printStudents(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("===========================");
    }

    public static class IdAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class IdDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    public static class AgeAscendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    public static class AgeDescendingComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o2.age - o1.age;
        }
    }

    public static void main(String[] args) {
        Student student1 = new Student("A", 1, 23);
        Student student2 = new Student("B", 2, 21);
        Student student3 = new Student("C", 3, 22);

        Student[] students = {student3, student2, student1};
        printStudents(students);

        Arrays.sort(students, new IdAscendingComparator());
        printStudents(students);

        Arrays.sort(students, new IdDescendingComparator());
        printStudents(students);

        Arrays.sort(students, new AgeAscendingComparator());
        printStudents(students);

        Arrays.sort(students, new AgeDescendingComparator());
        printStudents(students);

        //优先级队列, 逻辑堆
        PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(new IdDescendingComparator());
        studentPriorityQueue.add(student3);
        studentPriorityQueue.add(student1);
        studentPriorityQueue.add(student2);

        while (!studentPriorityQueue.isEmpty()) {
            Student student = studentPriorityQueue.poll();
            System.out.println(student);
        }
        System.out.println("===========================");


        //红黑树
        TreeSet<Student> studentTreeSet = new TreeSet(new IdAscendingComparator());

        studentTreeSet.add(student3);
        studentTreeSet.add(student1);
        studentTreeSet.add(student2);

        while (!studentTreeSet.isEmpty()) {
            Student student = studentTreeSet.pollFirst();
            System.out.println(student);
        }
        System.out.println("===========================");


    }

}
