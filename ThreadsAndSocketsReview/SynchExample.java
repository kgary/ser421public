
class Account {
	int     numTransactions = 0;
	int     balance = 0;

	public synchronized  void deposit(int amount) {
		balance += amount;
		// inc trans
		try {
			Thread.sleep(1000);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		numTransactions++;
	}

	public synchronized int getBalance() {
		return balance;
	}
	public synchronized int getNumberOfTransactions() {
		return numTransactions;
	}
}

class SynchExample extends Thread {
	Account account;
	int id;

	public SynchExample(int id, Account account) {
		super("Transaction #" + id);
		this.account = account;
		this.id = id;
	}

	public void run() {
		System.out.println("Transaction started #" + id);
		for (int i = 1; i <= 3; i++) {
			account.deposit(id*i);
			// deposit done
			System.out.println("Back from deposit in thread " + i);
		}
	}

	public static void main(String args[]) throws Exception {
		Account account = new Account();

		for (int i=1; i <= Integer.parseInt(args[0]); i++) {
			SynchExample trans = new SynchExample(i, account);
			trans.start();
		}
		// All trans done
		Thread.sleep(Long.parseLong(args[1]));
		System.out.println("Balance is " + account.getBalance() + " from " + account.getNumberOfTransactions() + " transactions");
	}
}
