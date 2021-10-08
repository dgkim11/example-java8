package example.java8.functions;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * BiFunction 예제이다. 일부 functional interface는 andThen이란 메쏘드를 제공한다. 이것의 사용예를 들겠다.
 * 이것은 apply() 메쏘드가 적용된 후에 후처리를 위한 추가적인 function을 andThen() 메쏘드에 계속 넣을 수 있도록 되어 있다.
 */
public class BiFunctionExMain {
    public static void main(String[] args)  {
        BiFunctionExMain theApp = new BiFunctionExMain();
        theApp.executeApply((s1, s2) -> Integer.valueOf(s1 + s2));
        theApp.executeAndThen((s1, s2) -> Integer.valueOf(s1 + s2));
    }

    private void executeApply(BiFunction<String,String,Integer> merge) {
        Integer result = merge.apply("123", "456");
        System.out.println("apply() result:" + result);
    }
    private void executeAndThen(BiFunction<String,String,Integer> merge) {
        String result = merge.andThen(r -> r * 1000).andThen(r -> "Here it is. $" + r.toString()).apply("123", "456");
        System.out.println("andThen().apply() result:" + result);
    }
}
