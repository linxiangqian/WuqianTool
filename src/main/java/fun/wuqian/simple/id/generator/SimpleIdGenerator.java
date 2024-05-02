package fun.wuqian.simple.id.generator;

import fun.wuqian.simple.id.generator.snowflake.SnowflakeKeyGenerator;

/**
 * 生成id
 * @author linxiangxian
 *
 */
public class SimpleIdGenerator {
	public static SnowflakeKeyGenerator snowflakeKeyGenerator =  new SnowflakeKeyGenerator();
	public static long nextId(){
		return snowflakeKeyGenerator.generateKey();
	}
	public static String nextIdStr(){
		return String.valueOf(nextId());
	}
	
	public static void main(String[] args) {
		System.out.println(SimpleIdGenerator.nextId());
	}
}
