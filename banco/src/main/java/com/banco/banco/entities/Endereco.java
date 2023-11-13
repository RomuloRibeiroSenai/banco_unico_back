package com.banco.banco.entities;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import com.google.gson.Gson;

@Data
@Entity
@Table(name ="enderecos")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @OneToMany(mappedBy = "endereco")
    private List<Cliente> clientes;

    public static Endereco getEnderecoByCep(String cep){

        Endereco endereco = new Endereco();

        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+ cep +"/json/");

        try(
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(request);
        ){
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println(result);

            Gson gson = new Gson();
            endereco = gson.fromJson(result, Endereco.class);
           
            System.out.println(endereco);

        } catch (Exception e){
            e.printStackTrace();
        }

        return endereco;
    }
}
