package guestbook;

import java.util.List;


public interface BlogDao {

	Topic store(Topic topic);

	List<Topic> getAll();
}