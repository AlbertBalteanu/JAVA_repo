package lab1;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); 
        String[] string = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int m = (int) (Math.random() * 1_000_000);
        int n=m*3;
        int n2=n+Integer.parseInt("10101",2);
        int n3=n2+Integer.parseInt("FF",16);
        int n4=n3*6;
        System.out.println(n4);
        int cc=0;
        if(n4%9==0) cc=9;
        else cc=n4%9;
        System.out.println(cc);
        System.out.println("Willy-nilly, this semester I will learn "+ string[cc]+"!");
    }
}
