
import java.util.Observable;

public abstract class Account extends Observable implements java.io.Serializable{

	/**
	 * An observable object can have one or more observers. An observer may be
	 * any object that implements interface Observer. After an observable
	 * instance changes, an application calling the Observable's notifyObservers
	 * method causes all of its observers to be notified of the change by a call
	 * to their update method.
	 * 
	 * When an observable object is newly created, its set of observers is
	 * empty. Two observers are considered the same if and only if the equals
	 * method returns true for them.
	 */
	private static final long serialVersionUID = 9218352974432L;
	private long iban;
	private Person holder;// titular cont
	private double currentSum;// suma curenta
	private String type;


	public Account(long iban, Person holder, double currentSum, String type) {
		//super();
		this.iban = iban;
		this.holder = holder;
		this.currentSum = currentSum;
		this.type = type;
	}

	public Account(Person holder, String type) {
		//super();
		this.holder = holder;
		this.type = type;
	}

	// private double balance;// dobanda doar pt Saving
	public abstract void depositSum(double sum);// depune suma

	public abstract void withdrawSum(double sum);// retrage suma

	public long getIban() {
		return iban;
	}

	public void setIban(long iban) {
		this.iban = iban;
	}

	public Person getHolder() {
		return holder;
	}

	public void setHolder(Person holder) {
		this.holder = holder;
	}

	public double getCurrentSum() {
		return currentSum;
	}

	public void setCurrentSum(double currentSum) {
		this.currentSum = currentSum;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "CAccount [iban=" + iban + ", holder=" + holder
				+ ", currentSum=" + currentSum + ", type=" + type + "]";
	}
	
}
