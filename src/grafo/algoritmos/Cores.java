package grafo.algoritmos;

public enum Cores {
    PRETO {
        @Override
        String cor() {
            return Cores.PRETO.toString().toLowerCase();
        }
    },
    CINZA {
        @Override
        String cor() {
            return Cores.CINZA.toString().toLowerCase();
        }
    },
    BRANCO {
        @Override
        String cor() {
            return Cores.BRANCO.toString().toLowerCase();
        }
    };

    abstract String cor();


}
