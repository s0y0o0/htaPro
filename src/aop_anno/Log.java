package aop_anno;

import org.aspectj.lang.annotation.Pointcut;

public interface Log {
	
	public String log(String name);
	
	
	public void log();
	public void log2();

}