public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("aha");
        String s = threadLocal.get();
        System.out.println(s);
    }
}
