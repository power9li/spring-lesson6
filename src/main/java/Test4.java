import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.services.MyTestService2;

public class Test4 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MySpringConfig.class);
		MyTestService2 mybean = (MyTestService2) ctx.getBean("myTestService2");
		mybean.doBusinessBBB(true);
		ctx.close();
	}

}
