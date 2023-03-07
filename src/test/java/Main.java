import com.kamo.amai.*;
import com.kamo.amai.control.Option;
import com.kamo.amai.control.Try;

public class Main {

    public static void main(String[] args) {
//        opt();
//        updateTuple();
//        liftAndTuple();
//        curried();
        tryResult();
    }

    private static void tryResult() {
        Try.of(() -> 1/0)
                .andThen(System.out::println)
                .onErr(ArithmeticException.class, e -> {
                    System.out.println(e+"+++++++++");
                    throw new RuntimeException("haha");
                })
                .andThen(System.out::println)
                .onErr((CheckConsumer<Throwable>) e ->
                    System.out.println(e +"--------"))
                .andThen(System.out::println)
                .onFinal(v -> {
                    System.out.println(v);
                    throw new RuntimeException("");
                })
                .run().unwrap();

//        Result<String> t = Try.of(() -> {
//            Main main = Main.class.newInstance();
//            Method tryTest = Main.class.getMethod("tryTest");
//            return (String) tryTest.invoke(main);
//        }).run();
//        String result = t.unwrapElse(err-> "unwrapElse");
//        System.out.println(result);
//        t.unwrap();
    }

    public String tryTest() {
        return "Hello Word";
    }

    private static void opt() {
        Option.of("Hello").let(s -> s + " Word").also(System.out::println);
    }

    private static void updateTuple() {
        Tuple3<String, Integer, Boolean> kamo = Tuple.of("kamo", 18, true);
        kamo.update1("AKINO").with(t -> System.out.println(t));
    }

    private static void liftAndTuple() {
        Tuple3<String, Integer, Boolean> kamo = Tuple.of("kamo", 18, true);
        ((Func3<String, Integer, Boolean, Tuple3>) Main::test)
                .lift()
                .tuple()
                .invoke(kamo)
                .get().forEach(System.out::println);
    }

    private static void curried() {
        Func3<String, Integer, Boolean, Tuple3> test = Main::test;
        Tuple3 kamo = test.curried().invoke("kamo").invoke(17).invoke(true);
        System.out.println(kamo);
        Func2<Integer, Integer, Integer> fn = (v1, v2) -> v1 + v2;
        System.out.println(fn.curried().invoke(1).apply(1));
    }

    public static Tuple3<String, Integer, Boolean> test(String name, int age, boolean isStudent) {
        System.out.println(name);
        System.out.println(age);
        System.out.println(isStudent);
        return Tuple.of(name, age, isStudent);
    }


}