package com.example.urbanstep.utils;

import com.example.urbanstep.modelo.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProdutoJsonParser {
    public static List<Produto> parserJsonProdutos(String response) {
        List<Produto> produtos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonProduto = jsonArray.getJSONObject(i);
                int id = jsonProduto.getInt("id");
                String nome = jsonProduto.getString("Nome");
                String descricao = jsonProduto.getString("Descricao");
                int preco = jsonProduto.getInt("Preco");
                int stock = jsonProduto.getInt("Stock");
                int referencia = jsonProduto.getInt("Referencia");
                int coresId = jsonProduto.getInt("cores_id");
                int categoriaId = jsonProduto.getInt("categoria_id");
                int imagem = jsonProduto.getInt("imagem");

                produtos.add(new Produto(id, nome, descricao, preco, stock, referencia, coresId, categoriaId, imagem));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao parsear JSON", e);
        }
        return produtos;
    }
}
