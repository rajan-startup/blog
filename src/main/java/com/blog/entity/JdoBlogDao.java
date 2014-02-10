package com.blog.entity;


import java.util.List;

import javax.jdo.Query;

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

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public Topic getTopic(long id) {
		
		Query q = getPersistenceManager().newQuery(Topic.class);
		q.setFilter("id == idParameter");
		q.declareParameters("long idParameter");
		
		return (Topic) getPersistenceManager()
				.newQuery(Topic.class).execute(id);
		
	}
}