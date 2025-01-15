package com.example.urbanstep;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanstep.adapters.ProdutoAdapter;
import com.example.urbanstep.modelo.Produto;
import com.example.urbanstep.services.ApiService;
import com.example.urbanstep.utils.ProdutoJsonParser;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private ProdutoAdapter produtoAdapter;
    private ArrayList<Produto> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_catalog);

        // Inicializar RecyclerView
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar a lista de produtos e o adaptador
        listaProdutos = new ArrayList<>();
        produtoAdapter = new ProdutoAdapter(listaProdutos);
        recyclerViewProdutos.setAdapter(produtoAdapter);

        // Carregar dados
        carregarProdutos();
    }

    private void carregarProdutos() {
        ApiService apiService = new ApiService(this);  // Passando o contexto (this)
        apiService.getRequest("http://localhost/backend/web/api/produtos", new ApiService.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                // Parse a resposta e atualize a lista de produtos
                List<Produto> produtos = ProdutoJsonParser.parserJsonProdutos(response);  // Use o parser para converter JSON para objetos Produto
                listaProdutos.clear();
                listaProdutos.addAll(produtos);
                produtoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                // Exibe a mensagem de erro
                Toast.makeText(ProductCatalogActivity.this, "Erro ao carregar produtos: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }
}
