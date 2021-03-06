package com.example.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	public static SqlSessionFactory factory = null;

	public static void init() {
		String resource = "Configuration.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
		factory.getConfiguration().addMapper(GestionaleMapper.class);
	}
}
