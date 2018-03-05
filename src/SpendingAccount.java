import javax.swing.JOptionPane;


public class SpendingAccount extends Account implements java.io.Serializable{
	/**
	 * The spending account allows several deposits and withdrawals, but does
	 * not compute any interest
	 * 
	 * @param iban
	 * @param holder
	 * @param sum
	 */
	private static final long serialVersionUID = 987465100032L;
	public static final int MAXSUM = 500;// SUMA MAXIMA CE SE POATE DEPUNE,
											// RETRAGE
	public int noDeposits = 0, noWithdrawals = 0;

	public SpendingAccount(long iban, Person holder, double sum, String type) {
		// TODO Auto-generated constructor stub
		super(iban, holder, sum, type);
	}

	public SpendingAccount(Person holder, String type) {
		super(holder, type);
	}

	@Override
	public void depositSum(double sum) {
		// TODO Auto-generated method stub

		if (sum > MAXSUM) {
			System.out.println("Nu se pot depune sume mai mari decat" +MAXSUM);
			JOptionPane.showMessageDialog(null, "Nu se pot depune sume mai mari decat" +MAXSUM);
		} else {
			double currentSum = getCurrentSum();
			currentSum = currentSum + sum;
			this.setCurrentSum(currentSum);
			noDeposits += 1;
			System.out.println("O noua depunere in cont!Nr depuneri:" +noDeposits +" Suma curenta " + currentSum);
			JOptionPane.showMessageDialog(null,"O noua depunere in cont!Nr depuneri:" +noDeposits +" Suma curenta " + currentSum);
			this.setChanged();
			this.notifyObservers(currentSum);
		}
	}

	@Override
	public void withdrawSum(double sum) {
		// TODO Auto-generated method stub
		double currentSum = getCurrentSum();
		if (sum > MAXSUM) {
			System.out.println("Nu se pot retrage sume mai mari decat "+MAXSUM);
			JOptionPane.showMessageDialog(null, "Nu se pot retrage sume mai mari decat "+MAXSUM);
		}else if(currentSum<sum){
			System.out.println("Nu se poate retrage aceasta suma! Suma existenta este: " + currentSum);
			JOptionPane.showMessageDialog(null, "Nu se poate retrage aceasta suma! Suma existenta este: " + currentSum);
		}
		else {
			
			currentSum = currentSum - sum;
			this.setCurrentSum(currentSum);
			noWithdrawals += 1;
			System.out.println("O noua retragere in cont!Nr retrageri:" +noWithdrawals +" Suma curenta: " + currentSum);
			JOptionPane.showMessageDialog(null, "O noua retragere in cont!Nr retrageri:" +noWithdrawals +" Suma curenta: " + currentSum);
			this.setChanged();
			this.notifyObservers(currentSum);
		}
	}

}
