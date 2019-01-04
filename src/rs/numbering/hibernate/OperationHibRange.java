/**
 * 
 */
package rs.numbering.hibernate;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import rs.numbering.format.Range;

/**
 * @author milosav.grubovic
 *
 */
public class OperationHibRange {
	public SessionFactory factoryHsql;
	public static Map<String, Long> sumMap;	
	public static Map<String, Long> operatorMap;


	
	/**
	 * @param factoryHsql
	 */
	public OperationHibRange(SessionFactory factoryHsql) {
		this.factoryHsql = factoryHsql;
	}

	/**
	 * 
	 */
	public OperationHibRange() {
	}

	public void intitHsql(List <Range> rangesMain){
		factoryHsql = getFactory("hibernate-hsqldb.cfg.xml");
		
		if(OperationHibRange.sumMap == null || OperationHibRange.sumMap.size() == 0){
				populateTable(rangesMain);
				OperationHibRange.sumMap = numberSummary();
				OperationHibRange.operatorMap = operatorSummary();
				//System.out.println("Populate tabele-in Hib class");
		}
	}
	
	public SessionFactory getFactory(String dbConfig){
		SessionFactory factory;	
		try{
				Configuration configuration = new Configuration();
			    configuration.configure(dbConfig); // "hibernate-hsqldb.cfg.xml"
			    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
			            configuration.getProperties()).buildServiceRegistry();
			    factory = configuration.buildSessionFactory(serviceRegistry);
			}catch(Throwable ex){
				throw new ExceptionInInitializerError(ex);
			}
	
		return factory;
	}
	
	public void populateTable(List<Range> inputRanges){
		//String dbConfig=  "hibernate-hsqldb.cfg.xml";
//		clearTable();
//		testAddRange();
		

		for(Range re: inputRanges){
			//System.out.println("db refernce "+ re.mg +"/" + re.startRange + " -  "  + re.getEndRange() + " -  "  + re.blockCount +
			//		" " +  re.fromDate + " -  "  + re.untilDate + " " + re.decisionNumber + " " + re.operator );
			addRange(re.mg, re.startRange, re.getEndRange(), re.operator);
		}
	}

	
	private Integer addRange(String mg, String startRange, String endRange, String operator) {
		Session session = factoryHsql.openSession();
		Transaction tx = null;
		Integer rangeId= null;
		try{
			tx = session.beginTransaction();
			Range re= new Range();
			re.setMg(mg);
			re.setStartRange(startRange);
			re.setEndRange(endRange);
			
			int amount = Integer.parseInt(endRange) - Integer.parseInt(startRange) + 1;
			re.setAmountRange(amount);
			
			re.setOperator(operator);
			rangeId = (Integer)session.save(re);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return rangeId;
	}
	public void testAddRange(){
		 addRange("15", "3222000", "222999", "telco");
		 addRange("25", "222000", "222999", "telco");
		 addRange("230", "3555000", "582999", "telco");
	}
	
	public void listRanges(){
		List <Range> rangesToList = getRanges();
		for(Range re: rangesToList){
			System.out.println("db rangte "+ re.mg +"/" + re.startRange + " -  "  + re.endRange + " -  "  + re.operator);
		}
	
	}
	public List <Range>  getRanges(){
		Session session = factoryHsql.openSession();
		Transaction tx = null;
		List <Range> ranges = null;
		try{
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Range.class);
//			cr.add(Restrictions.like("startRange", "3%"));
			@SuppressWarnings("unchecked")
			List <Range> rangesTaken = cr.list();
			ranges = rangesTaken;
			//List <Range> ranges = (List<Range>) session.createQuery("FROM Range").list();
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return ranges;
	}
	public void geoSummary(){
		Session session = factoryHsql.openSession();
		Transaction tx = null;
		String queryGropuByMG = "SELECT COUNT(R.mg), R.mg FROM Range R "
								+ " GROUP BY R.mg";
		String queryGropuByOperator = "SELECT COUNT(R.operator), R.operator FROM Range R "
				+ " GROUP BY R.operator";
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery(queryGropuByMG);

			@SuppressWarnings("unchecked")
			List<Object[]> resultList = query.list();
			for(Object [] result: resultList){
				System.out.println("summary in mg "+ result[1] + " there is " + result[0] + " ranges.");

			}
			query = session.createQuery(queryGropuByOperator);

			@SuppressWarnings("unchecked")
			List <Object[]> resultOperator = query.list();
			for(Object [] result: resultOperator){
				System.out.println("summary at operator "+ result[1] + " there is " + result[0] + " ranges.");
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	public Map<String, Long> numberSummary(){
		Map<String, Long> mgAmount = new TreeMap<>();
		Session session = factoryHsql.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
/*			
			String queryNumberMg = "SELECT R.mg, R.startRange, R.endRange FROM Range R ";
			Query query = session.createQuery(queryNumberMg);
			List<Range> resultList = (List<Range>)query.list();
*/
			@SuppressWarnings("unchecked")
			List <Range> resultList = (List<Range>) session.createQuery("FROM Range").list();

			for(Range result: resultList){
				//System.out.println("summary in mg "+ result.mg + " / " + result.startRange +
				//		" - " + result.endRange);
				long amount = Integer.parseInt(result.endRange) - Integer.parseInt(result.startRange) + 1;
				if(mgAmount.containsKey(result.mg)){
					long sum = mgAmount.get(result.mg);
					sum = sum + amount;
					mgAmount.put(result.mg, sum);
				}else{
					mgAmount.put(result.mg, amount);
				}
			}
			System.out.println(mgAmount);
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return mgAmount;
	}
	
	public Map<String, Long> operatorSummary(){
		Map<String, Long> mgAmount = new TreeMap<>();
		Session session = factoryHsql.openSession();
		Transaction tx = null;
		String queryGropuByOperator = "SELECT SUM(R.amountRange), R.operator FROM Range R "
				+ " GROUP BY R.operator ORDER BY SUM(R.amountRange) DESC";
		
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery(queryGropuByOperator);

			@SuppressWarnings("unchecked")
			List <Object[]> resultOperator = query.list();
			for(Object [] result: resultOperator){
				System.out.println("Operator "+ result[1] + " has " + result[0] + " numbers.");
				mgAmount.put(result[1]+"", Long.parseLong(result[0]+""));
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}

		return mgAmount;
	}
	
	@SuppressWarnings("unchecked")
	public void clearTable(){
		Session session = factoryHsql.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List <Range> ranges = (List<Range>) session.createQuery("FROM Range").list();

			for(Range re: ranges){
				session.delete(re);
				System.out.println("db rangte "+ re.mg +"/" + re.startRange + " -  "  + re.endRange + " -  "  + re.operator);
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		
	}
}
