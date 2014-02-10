package guestbook;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class JdoBlogDao extends AbstractJdoDao implements BlogDao {
	@Autowired
	public JdoBlogDao(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		super(pmf);
	}

	@Override
	@Transactional
	public Topic store(final Topic topic) {
		return getPersistenceManager().makePersistent(topic);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Topic> getAll() {
		return (List<Topic>) getPersistenceManager()
				.newQuery(Topic.class).execute();
	}
}