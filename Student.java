 class Student {
     private double marks1, marks2;
     private double maxMarksc = 100, avg = 0;

     public double getAverage() {
         avg = 0;
         avg = ((marks1 + marks2) / (maxMarksc * 3)) * 100;
         return avg;
     }

     public void setAverage(double val) {
         avg = val;
     }


     public static void main(String[] args) {
         Student s = new Student();
         s.marks1 = 35;
         s.marks2 = 35;
         System.out.println(s.getAverage());
         s.setAverage(35);
     }
 }