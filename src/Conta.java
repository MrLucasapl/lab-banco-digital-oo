import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	private List<RegistroDeTransferencia> listRegistro;

	public Conta(Cliente cliente) {
		this.listRegistro = new ArrayList<RegistroDeTransferencia>();
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		if (verificadorDeSaldo(valor)) {
			saldo -= valor;
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, Conta contaDestino) {
		if (verificadorDeSaldo(valor)) {
			this.sacar(valor);
			contaDestino.depositar(valor);
			adicionarRegistroDeTranferencia(
					contaDestino.cliente.getNome(), contaDestino.agencia, contaDestino.numero, valor);
		}
	}

	private boolean verificadorDeSaldo(double valor) {
		if (this.saldo < valor) {
			System.out.println("Valor insuficiente!");
			return false;
		}
		return true;
	}

	private void adicionarRegistroDeTranferencia(String titular, int agencia, int conta, double valor) {
		listRegistro.add(new RegistroDeTransferencia(this.cliente.getNome(), this.agencia, this.numero, valor));
	}

	public void imprimirDadosTranferidos() {
		System.out.println("=== Historico De Tranferencias ===");
		listRegistro.forEach((registro) -> {
			System.out.println("======================================================");
			System.out.println(String.format("Titular: %s", registro.getTitular()));
			System.out.println(String.format("Agencia: %d", registro.getAgencia()));
			System.out.println(String.format("Numero: %d", registro.getConta()));
			System.out.println(String.format("Saldo: %.2f", registro.getSaldo()));
		});

	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}
}
