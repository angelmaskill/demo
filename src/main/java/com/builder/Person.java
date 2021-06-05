package com.builder;

/**
 * <pre>
 *  Builder模式:
 *  限制了参数,保证必选参数肯定有.
 *  可读性好,传入每个可选参数单独调用方法,可以明确的知道每个参数的意义.
 *  链式调用看起来好看.
 * </pre>
 *
 * @ClassName Person
 * @Description builder 模式构建对象
 * @Author yanlu.myl
 * @Date 2021-04-29 10:23
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    private Person(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        age = builder.age;
    }


    //将Person类的构造方法私有化,所以想要新建Student必须使用Builder.
    private static final class Builder {
        private String firstName;
        private String lastName;
        private int age;

        /**
         * Builder只有一个构造方法,传入必选的参数,这样可以保证每个Student都会有必选参数.
         */
        private Builder() {
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        //Builder提供build方法,调用Person私有的构造方法,返回对象.
        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person build = new Person.Builder().firstName("张").lastName("三丰").build();
        System.out.println(build);
    }
}
