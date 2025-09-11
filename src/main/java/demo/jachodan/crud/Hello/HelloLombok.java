package demo.jachodan.crud.Hello;

import lombok.Getter;

@Getter
//@Setter
//@RequiredArgsConstructor
public class HelloLombok {
//  private String hello;
//  private int lombok;
  private final String hello;
  private final int lombok;

  public HelloLombok(String hello, int lombok) {
    this.hello = hello;
    this.lombok = lombok;
  }

  public static void main(String[] args) {
//    HelloLombok helloLombok = new HelloLombok();
//    helloLombok.setHello("Hello, Lombok!!");
//    helloLombok.setLombok(100);

    HelloLombok helloLombok = new HelloLombok("헬로", 5);

    System.out.println(helloLombok.getHello());
    System.out.println(helloLombok.getLombok());
  }
}
