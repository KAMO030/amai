package func;

import com.kamo.amai.Func0;
import com.kamo.amai.Func1;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

public class FuncTest {

    @Test
    public void func0Test(){
        Func0<String> targetFunc0 = this::targetFunc0;
        String result = targetFunc0.invoke();
        assert result.equals("Hello");
    }

    public String targetFunc0(){
        return "Hello";
    }

    @Test
    public void func1Test(){
        Func1<String, String> targetFunc1 = this::targetFunc1;
        Function<String, String> c = s -> {
            System.out.println(s);
            return "3";
        };
        Consumer<String> c1 = s -> {
            System.out.println(s);

        };
        String result = targetFunc1.andThen(c1).invoke("1").toString();
        System.out.println(result);
//        String result = targetFunc1.invoke("Hello");
//        assert result.equals("Hello");
    }
    public String targetFunc1(String val){
        System.out.println(val);
        return "2";
    }
}
