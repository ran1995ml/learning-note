<<<<<<< HEAD
package com.ran.pattern.adapter;

/**
 * Main
 * 用适配器将Turkey转换成Duck
 *
 * @author rwei
 * @since 2024/8/15 14:35
 */
public class Main {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck turkeyDuck = new TurkeyAdapter(turkey);
        turkeyDuck.fly();
        turkeyDuck.quack();
    }
=======
package com.ran.pattern.adapter;/** 
 * Main
 * 
 * @author rwei
 * @since 2024/8/15 14:35
 */public class Main {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
