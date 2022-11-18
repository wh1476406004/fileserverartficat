import com.google.common.base.CaseFormat;
import com.pojo.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: GooGoo
 * @time: 2021/10/30 17:20
 */
public class functionTest {

    @Test
    public void testPredicate1(){
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("1233"));
    }

    @Test
    public void testPredicate2(){
        List<String> list = Arrays.asList("1234", "12345", "123122412");
        List<String> collect = list.stream().filter(str -> str.length() == 4).collect(Collectors.toList());
        System.out.println(collect);
    }
    @Test
    public void testFunction3(){
        UnaryOperator<Integer> test = x->x+1;
        System.out.println(test.apply(12));
    }

    @Test
    public  void testFunction4(){
        BinaryOperator<Integer> operator = BinaryOperator.maxBy((x, y) -> (x > y) ? 1 : ((x == y) ? 0 : -1));
        Integer apply = operator.apply(1, 2);
        System.out.println(apply);
    }


    @Test
    public  void testStream5(){
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        System.out.println(integerList.indexOf(3));
        Integer reduce = integerList.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

    }


    @Test
    public void Convert(){
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "HAPPYLIFE"));
    }



    @Test
    public void GuavaTest() {
        BigDecimal bigDecimal = new BigDecimal("100.01");
        BigDecimal bigDecimal2 = new BigDecimal("10");
        System.out.println(bigDecimal.compareTo(bigDecimal2));


    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    public void tets1213(){
        Function<Integer, Integer> first=x ->x*x;
        Function<Integer, Integer> after=y->y*2;

        System.out.println(first.apply(3));
        System.out.println(after.apply(3));
        int res=first.andThen(after).apply(4);
        System.out.println(res);
    }
    @Test
    public void tets12135(){
        System.out.println(Integer.parseInt("2111001000065"));
    }

    public static List<Map> ListTrim(List<Map> list) {
        for (Map map : list) {
            Iterator i = map.entrySet().iterator();
            while (i.hasNext()) {
                Map.Entry entry = (Map.Entry) i.next();
                Object value = entry.getValue();
                if (value instanceof String){
                    entry.setValue(((String) value).trim());
                }
            }
        }
        return list;
    }

    @Test
    void okhttp(){

    }


}
