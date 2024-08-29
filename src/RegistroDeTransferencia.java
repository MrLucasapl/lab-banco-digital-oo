public class RegistroDeTransferencia {
    private String titular;
    private int agencia;
    private int conta;
    private double saldo;

    public RegistroDeTransferencia(String titular, int agencia, int conta, double saldo) {
        this.titular = titular;
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
    }

    public String getTitular() {
        return this.titular;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public int getConta() {
        return this.conta;
    }

    public double getSaldo() {
        return this.saldo;
    }
}
