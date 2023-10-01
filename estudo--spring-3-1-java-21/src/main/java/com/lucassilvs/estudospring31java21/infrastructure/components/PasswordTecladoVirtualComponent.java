package com.lucassilvs.estudospring31java21.infrastructure.components;

import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordComponent;
import com.lucassilvs.estudospring31java21.domain.ports.repositories.password.PasswordKeyboardComponent;
import com.lucassilvs.estudospring31java21.exceptions.InfrastructureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.function.Function;

@Component
public class PasswordTecladoVirtualComponent implements PasswordKeyboardComponent {

    public static final String ERROR_INTERNO_TECLADO = "Erro durante geração de senha teclado virtual: ";
    private final PasswordComponent passwordComponents;

    @Autowired
    public PasswordTecladoVirtualComponent( PasswordComponent passwordComponents1) {
        this.passwordComponents = passwordComponents1;
    }

    public  boolean isSenhaCorreta(String senha, char[][] tecladoVirtual, int[] teclasPresionadas){

        char[] senhadecodificada = decode(tecladoVirtual, teclasPresionadas, (String candidate) -> {
                    try {
                        return passwordComponents.validarSenha(candidate, senha);
                    } catch (Exception e){
                        throw new InfrastructureException(ERROR_INTERNO_TECLADO, e.getMessage(), this.getClass().getEnclosingMethod().getName(), 500);
                    }
                }
        );
        return senhadecodificada != null;
    }

    public  boolean isSenhaCorretaBinario(String senha, char[][] tecladoVirtual, int[] teclasPresionadas) {

        String senhadecodificada = decodeBinarySearch(tecladoVirtual, teclasPresionadas, (String candidate) -> {
                    try {
                        return passwordComponents.validarSenha(candidate, senha);
                    } catch (Exception e){
                        throw new InfrastructureException(ERROR_INTERNO_TECLADO,e.getMessage(),"decodeParallel()", 500);
                    }
                }
        );
        return senhadecodificada != null;
    }

    public boolean validarSenha(char[][] teclado, int[] teclas, String hash) {
        char[] senhadecodificada = decodeParallel(teclado, teclas, (String candidate) -> {
                    try {
                        return passwordComponents.validarSenha(candidate, hash);
                    } catch (Exception e){
                        throw new InfrastructureException(ERROR_INTERNO_TECLADO, e.getMessage(), this.getClass().getEnclosingMethod().getName(), 500);
                    }
                }
        );
        return senhadecodificada != null;
    }


    private String decodeBinarySearch(char[][] tecladoVirtual, int[] teclasPresionadas, Function<String, Boolean> visitor) {
        int groupSize = tecladoVirtual[0].length;
        int numeroDeCombinacoes = pow(groupSize, teclasPresionadas.length);

        char[] candidate = new char[teclasPresionadas.length];
        int left = 0;
        int right = numeroDeCombinacoes - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int auxiliar = mid;
            for (int j = (teclasPresionadas.length - 1); j >= 0; j--) {
                int idx = auxiliar % groupSize;
                candidate[j] = tecladoVirtual[teclasPresionadas[j]][idx];
                auxiliar = auxiliar / groupSize;
            }

            boolean isCorrect = visitor.apply(new String(candidate));
            if (isCorrect) {
                return new String(candidate);
            }

            if (isCorrect) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }


    private char[] decodeParallel(char[][] tecladoVirtual, int[] teclasPresionadas, Function<String, Boolean> visitor) {
        int groupSize = tecladoVirtual[0].length;
        int numeroDeCombinacoes = (int) Math.pow(groupSize, teclasPresionadas.length);
        char[] candidate = new char[teclasPresionadas.length];
        int numThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CompletionService<Boolean> completionService = new ExecutorCompletionService<>(executor);

        int batchSize = numeroDeCombinacoes / numThreads;
        for (int i = 0; i < numThreads; i++) {
            final int start = i * batchSize;
            final int end = (i == numThreads - 1) ? numeroDeCombinacoes : (i + 1) * batchSize;

            completionService.submit(() -> {
                for (int j = start; j < end; j++) {
                    int auxiliar = j;

                    for (int k = teclasPresionadas.length - 1; k >= 0; k--) {
                        int idx = auxiliar % groupSize;
                        candidate[k] = tecladoVirtual[teclasPresionadas[k]][idx];
                        auxiliar = auxiliar / groupSize;
                    }

                    if (Boolean.TRUE.equals(visitor.apply(new String(candidate)))) {
                        return true;
                    }
                }

                return false;
            });
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                Future<Boolean> future = completionService.take();
                if (future.get()) {
                    return candidate;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new InfrastructureException("Erro durante geração de senha teclado virtual: ",e.getMessage(),"decodeParallel()", 500);
            }
        }

        return null;
    }




    private static char[] decode(char[][] tecladoVirtual, int[] teclasPresionadas, Function<String, Boolean> visitor) {

        int groupSize = tecladoVirtual[0].length;

        //determina o número de combinações que iremos usar, assumindo que o tamanho do teclado virtual possui o mesmo numero de simbolos por tecla
        int numeroDeCombinacoes = pow(groupSize, teclasPresionadas.length);

        //inicia o tamanho da cadeia de caracteres que representa o candidato a senha
        char[] candidate = new char[teclasPresionadas.length];

        for (int i = 0; i < numeroDeCombinacoes; i++){
            int auxiliar = i;

            //prepara uma nova combinação
            for (int j = (teclasPresionadas.length - 1); j>= 0; j--) {
                int idx = auxiliar % groupSize;
                candidate[j] = tecladoVirtual[teclasPresionadas[j]][idx];
                auxiliar = auxiliar / groupSize;
            }
            if(Boolean.TRUE.equals(visitor.apply(new String(candidate)))){
                return candidate;
            }
        }
        return null;
    }

    //Operação de potencia simples para numeros pequenos

    protected static int pow(int base, int expoente){

        //caso expecial onde a base é 2
        if(base == 2){
            return  1 << expoente;
        }

        if(base == 1){
            return 1;
        }

        int resultado = base;
        for(int i = 1; i < expoente; i++){
            resultado = resultado * base;
        }
        return  resultado;
    }

}
