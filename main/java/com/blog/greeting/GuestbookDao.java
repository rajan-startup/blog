package com.blog.greeting;

import java.util.List;

public interface GuestbookDao {

	Greeting store(Greeting greeting);

	List<Greeting> getAll();
}