package teste;

public class Aviao extends Aeronave{
	public Passageiro[][] lugares;
	int Fileiras, Assentos;
	
    public Aviao(String modelo, int row, int col) {
        super(modelo);
        this.lugares = new Passageiro[row][col];
    }
    
    public void setPassageiro(Passageiro p, int row, int col) {
        lugares[row][col] = p;
    }
    
    public Passageiro getPassageiro(int row, int col) {
    	if(lugares != null)
    		return lugares[row][col];
    	else
    		return null;
    }

    public boolean isLugarOcupado(int row, int col) {
    	if(lugares[row][col] != null)
    		return true;
    	else
    		return false;
    }
}
    
