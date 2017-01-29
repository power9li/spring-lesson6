import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.services.MyDemoService;

public class Test5 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MySpringConfig.class);
		MyDemoService mybean = (MyDemoService) ctx.getBean("myDemoService");
		mybean.doBusinessBBB(true);
		
//		MyDemoServiceIntf mybean = (MyDemoServiceIntf) ctx.getBean("myDemoService");
//		mybean.doBusinessBBB(true);
		ctx.close();
	}

}
